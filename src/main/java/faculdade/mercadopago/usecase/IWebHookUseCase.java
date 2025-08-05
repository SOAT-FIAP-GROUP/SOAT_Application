package faculdade.mercadopago.usecase;

import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
import faculdade.mercadopago.entity.pagamento.DadosPedidoPago;
import faculdade.mercadopago.gateway.IPagamentoGateway;

public interface IWebHookUseCase {
    boolean confirmarPagamento(ConfirmacaoWebHookRequest request);

    DadosPedidoPago retornarPedidoPago(ConfirmacaoWebHookRequest request);

    void processarPagamento(ConfirmacaoWebHookRequest request);
}