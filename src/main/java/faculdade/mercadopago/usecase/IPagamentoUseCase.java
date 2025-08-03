package faculdade.mercadopago.usecase;

import faculdade.mercadopago.controller.mapper.dto.request.QrCodeRequest;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.pagamento.ConfirmacaoPagamentoRes;
import faculdade.mercadopago.entity.pagamento.QrCodeRes;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface IPagamentoUseCase {
    QrCodeRes processarQrCode(QrCodeRequest request);

    void salvarPagamento(Pedido pedido, BigDecimal valor);

    ResponseEntity<?> buscarDados(String url, HttpMethod http, Class<ConfirmacaoPagamentoRes> confirmacaoPagamentoResClass);
}