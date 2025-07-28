//package faculdade.mercadopago.usecase.impl;
//
//import faculdade.mercadopago.AppConstants;
//import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
//import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
//import faculdade.mercadopago.entity.pagamento.ConfirmacaoPagamentoRes;
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
//        var response = pagamentoGateway.sendRequest(url, HttpMethod.GET, ConfirmacaoPagamentoRes.class);
//        if (response.getStatusCode().is2xxSuccessful()) {
//
//            ConfirmacaoPagamentoRes body = (ConfirmacaoPagamentoRes) response.getBody();
//            assert body != null;
//            String codigo = body.external_reference();
//            String status = body.status();
//            double valorPago = 0.0;
//            if (body.transaction_details() != null) {
//                valorPago = body.transaction_details().total_paid_amount();
//            }
//
//            if (status.equals("approved")) {
//                var resultado = pagamentoGateway.criarPagamento(Long.parseLong(codigo), BigDecimal.valueOf(valorPago), status);
//                if (resultado) {
//                    _pedidoService.alterarPedido(Long.parseLong(codigo), StatusPedidoEnum.EM_PREPARACAO);
//                    _pedidoService.adicionarPedidoNaFila(Long.parseLong(codigo));
//                }
//            }
//        }
//    }
//}