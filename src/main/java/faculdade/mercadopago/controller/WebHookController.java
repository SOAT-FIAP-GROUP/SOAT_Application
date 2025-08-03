package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.usecase.IWebHookUseCase;

public class WebHookController {
    public final IWebHookUseCase webHookUseCase;
    public final IPagamentoGateway pagamentoGateway;

    public WebHookController(IWebHookUseCase webHookUseCase, IPagamentoGateway pagamentoGateway) {
        this.webHookUseCase = webHookUseCase;
        this.pagamentoGateway = pagamentoGateway;
    }


    public void confirmarPagamento(ConfirmacaoWebHookRequest request) {
        webHookUseCase.processarPagamento(request);
    }


}
