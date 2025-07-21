//package faculdade.mercadopago.usecase.impl;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import faculdade.mercadopago.AppConstants;
//import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
//import faculdade.mercadopago.entity.pagamento.QrCodeOrder;
//import faculdade.mercadopago.gateway.IPagamentoGateway;
//import faculdade.mercadopago.usecase.IWebHookUseCase;
//import org.springframework.http.HttpMethod;
//
//import java.math.BigDecimal;
//
//public class WebHookUseCase implements IWebHookUseCase {
//
//    @Override
//    public void confirmarPagamento(ConfirmacaoWebHookRequest request, IPagamentoGateway pagamentoGateway) {
//        var url = AppConstants.BASEURL_MERCADOPAGO + AppConstants.CONFIRMPAYMENT_MERCADOPAGO + "/" + request.id();
//        var response = pagamentoGateway.sendRequest(url, HttpMethod.GET, QrCodeOrder.class);
//        if (response.getStatusCode().is2xxSuccessful()) {
//            apiResponse.setData((PixPaymentResponse) response.getBody());
//
//            PixPaymentResponse body = (PixPaymentResponse) response.getBody();
//            String codigo = body.getExternal_reference();
//            String status = body.getStatus();
//            Double valorPago = 0.0;
//            if (body.getTransactionDetails() != null) {
//                valorPago = body.getTransactionDetails().getTotal_paid_amount();
//            }
//
//            if (status.equals("approved")) {
//                var resultado = CreatePagamento(Long.parseLong(codigo), BigDecimal.valueOf(valorPago), status);
//                if (resultado) {
//                    apiResponse.setData(null);
//                    _pedidoService.alterarPedido(Long.parseLong(codigo), StatusPedidoEnum.EM_PREPARACAO);
//                    _pedidoService.adicionarPedidoNaFila(Long.parseLong(codigo));
//                }
//            } else {
//                apiResponse.setSuccess(false);
//                apiResponse.addError("Pagamento","Pagamento não foi aprovado.");
//            }
//        } else {
//            apiResponse.setSuccess(false);
//            try {
//                var mapper = new ObjectMapper();
//                var errorResponse = mapper.readValue((String) response.getBody(), ApiResponse.Err.class);
//                apiResponse.addError("Erro na confirmação de pagamento: " + errorResponse.getError(), errorResponse.getMessage());
//            } catch (Exception ex) {
//                apiResponse.addError("Erro ao interpretar resposta: ",(String) response.getBody());
//            }
//        }
//        return apiResponse;
//    }
//}