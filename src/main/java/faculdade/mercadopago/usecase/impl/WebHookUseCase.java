package faculdade.mercadopago.usecase.impl;

import faculdade.mercadopago.AppConstants;
import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.entity.pagamento.ConfirmacaoPagamentoRes;
import faculdade.mercadopago.entity.pagamento.DadosPedidoPago;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.usecase.IFilaPedidosPreparacaoUseCase;
import faculdade.mercadopago.usecase.IPagamentoUseCase;
import faculdade.mercadopago.usecase.IPedidoUseCase;
import faculdade.mercadopago.usecase.IWebHookUseCase;
import org.springframework.http.HttpMethod;

import java.math.BigDecimal;

public class WebHookUseCase implements IWebHookUseCase {
    public final IPedidoUseCase pedidoUseCase;
    public final IPagamentoUseCase pagamentoUseCase;
    public final IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase;

    public WebHookUseCase(IPedidoUseCase pedidoUseCase, IPagamentoUseCase pagamentoUseCase, IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase) {
        this.pedidoUseCase = pedidoUseCase;
        this.pagamentoUseCase = pagamentoUseCase;
        this.filaPedidosPreparacaoUseCase = filaPedidosPreparacaoUseCase;
    }

    private String urlPagamento(String id) {
        return AppConstants.BASEURL_MERCADOPAGO + AppConstants.CONFIRMPAYMENT_MERCADOPAGO + "/" + id;
    }

    @Override
    public boolean confirmarPagamento(ConfirmacaoWebHookRequest request) {
        var url = urlPagamento(request.id());
        var response = pagamentoUseCase.buscarDados(url, HttpMethod.GET, ConfirmacaoPagamentoRes.class);
        if (response.getStatusCode().is2xxSuccessful()) {

            ConfirmacaoPagamentoRes body = (ConfirmacaoPagamentoRes) response.getBody();
            System.out.println(body);
            assert body != null;
            String status = body.status();
            return status.equals("approved");
        }
        return false;
    }

    @Override
    public DadosPedidoPago retornarPedidoPago(ConfirmacaoWebHookRequest request) {
        var url = urlPagamento(request.id());
        var response = pagamentoUseCase.buscarDados(url, HttpMethod.GET, ConfirmacaoPagamentoRes.class);
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