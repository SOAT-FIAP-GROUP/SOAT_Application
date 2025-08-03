package faculdade.mercadopago.usecase.impl;

import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.entity.pagamento.ConfirmacaoPagamentoRes;
import faculdade.mercadopago.entity.pagamento.DadosPedidoPago;
import faculdade.mercadopago.gateway.IFilaPedidosPreparacaoGateway;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.usecase.IPagamentoUseCase;
import faculdade.mercadopago.usecase.IPedidoUseCase;
import faculdade.mercadopago.usecase.IWebHookUseCase;

import java.math.BigDecimal;

public class WebHookUseCase implements IWebHookUseCase {
    public final IPedidoUseCase pedidoUseCase;
    public final IPedidoGateway pedidoGateway;
    public final IPagamentoUseCase pagamentoUseCase;
    public final IPagamentoGateway pagamentoGateway;
    public final IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway;

    private static final String STATUS_APROVADO = "approved";


    public WebHookUseCase(IPedidoUseCase pedidoUseCase, IPedidoGateway pedidoGateway, IPagamentoUseCase pagamentoUseCase, IPagamentoGateway pagamentoGateway, IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway) {
        this.pedidoUseCase = pedidoUseCase;
        this.pedidoGateway = pedidoGateway;
        this.pagamentoUseCase = pagamentoUseCase;
        this.pagamentoGateway = pagamentoGateway;
        this.filaPedidosPreparacaoGateway = filaPedidosPreparacaoGateway;
    }

    @Override
    public boolean confirmarPagamento(ConfirmacaoWebHookRequest request) {
        var response = pagamentoUseCase.consultarPagamento(request.id());
        if (response.getStatusCode().is2xxSuccessful()) {

            ConfirmacaoPagamentoRes body = (ConfirmacaoPagamentoRes) response.getBody();
            System.out.println(body);
            if (body == null) {
                throw new RuntimeException("Mercado Pago retornou uma resposta vazia");
            }

            String status = body.status();
            //status = "approved";
            return status.equals(STATUS_APROVADO);
        }
        return false;
    }

    @Override
    public DadosPedidoPago retornarPedidoPago(ConfirmacaoWebHookRequest request) {
        var response = pagamentoUseCase.consultarPagamento(request.id());
        if (response.getStatusCode().is2xxSuccessful()) {
            ConfirmacaoPagamentoRes body = (ConfirmacaoPagamentoRes) response.getBody();
            if (body == null) {
                throw new RuntimeException("Mercado Pago retornou uma resposta vazia");
            }
            String codigo = body.external_reference();
            double valorPago = 0.0;
            if (body.transaction_details() != null) {
                valorPago = body.transaction_details().total_paid_amount();
            }
            return new DadosPedidoPago(
                    codigo,
                    valorPago
            );
        } else {
            throw new RuntimeException("Erro ao consultar Pagamento");
        }
    }

    @Override
    public void processarPagamento(ConfirmacaoWebHookRequest request) {
        if (!confirmarPagamento(request)) {
            throw new RuntimeException("Pagamento " + request.id() + " não confirmado");
        }

        DadosPedidoPago dados = retornarPedidoPago(request);
        Long id = Long.parseLong(dados.codigo());
        BigDecimal valor = BigDecimal.valueOf(dados.valorPago());
        var pedido = pedidoUseCase.buscarPedido(id);
        pagamentoUseCase.salvarPagamento(pedido, valor);
        pedidoUseCase.alterarPedido(id, StatusPedidoEnum.EM_PREPARACAO);
        pedidoUseCase.adicionarPedidoNaFila(id);
    }
}