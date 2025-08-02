package faculdade.mercadopago.usecase;

import faculdade.mercadopago.controller.mapper.dto.request.QrCodeRequest;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.pagamento.QrCodeRes;
import faculdade.mercadopago.gateway.IPagamentoGateway;

import java.math.BigDecimal;

public interface IPagamentoUseCase {
    QrCodeRes processarQrCode(QrCodeRequest request, IPagamentoGateway gateway);

    void salvarPagamento(Pedido pedido, BigDecimal valor, IPagamentoGateway gateway);
}