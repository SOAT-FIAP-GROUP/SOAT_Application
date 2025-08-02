package faculdade.mercadopago.usecase;

import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
import faculdade.mercadopago.entity.pagamento.DadosPedidoPago;
import faculdade.mercadopago.gateway.IPagamentoGateway;

public interface IWebHookUseCase {
    boolean confirmarPagamento(ConfirmacaoWebHookRequest request, IPagamentoGateway pagamentoGateway);

    DadosPedidoPago retornarPedidoPago(ConfirmacaoWebHookRequest request, IPagamentoGateway pagamentoGateway);

    void processarPagamento(ConfirmacaoWebHookRequest request, IPagamentoGateway pagamentoGateway);
}