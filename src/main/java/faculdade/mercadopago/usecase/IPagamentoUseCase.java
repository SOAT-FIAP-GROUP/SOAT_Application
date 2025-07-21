package faculdade.mercadopago.usecase;

import faculdade.mercadopago.controller.mapper.dto.request.QrCodeRequest;
import faculdade.mercadopago.entity.pagamento.QrCodeRes;
import faculdade.mercadopago.gateway.IPagamentoGateway;

public interface IPagamentoUseCase {
    QrCodeRes processarQrCode(QrCodeRequest request, IPagamentoGateway gateway);
}