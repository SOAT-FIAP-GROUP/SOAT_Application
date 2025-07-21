package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.usecase.IWebHookUseCase;
import org.springframework.http.ResponseEntity;

public class WebHookController {
    public static IWebHookUseCase webHookUseCase;
    public static IPagamentoGateway pagamentoGateway;

    public ResponseEntity<?> confirmarPagamento(ConfirmacaoWebHookRequest request) {
        webHookUseCase.confirmarPagamento(request, pagamentoGateway);
        return null;
    }
}
