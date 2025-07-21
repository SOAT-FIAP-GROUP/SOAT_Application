//package faculdade.mercadopago.controller;
//
//import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
//import faculdade.mercadopago.gateway.IPagamentoGateway;
//import faculdade.mercadopago.usecase.IWebHookUseCase;
//
//public class WebhookController {
//
//    private final IWebHookUseCase webHookUseCase;
//    private final IPagamentoGateway pagamentoGateway;
//
//    public WebhookController(IWebHookUseCase webHookUseCase, IPagamentoGateway pagamentoGateway) {
//        this.webHookUseCase = webHookUseCase;
//        this.pagamentoGateway = pagamentoGateway;
//    }
//
//    public void confirmarPagamento(ConfirmacaoWebHookRequest request){
//        webHookUseCase.confirmarPagamento(request, pagamentoGateway);
//    }
//}