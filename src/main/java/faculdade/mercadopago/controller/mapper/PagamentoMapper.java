package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.controller.mapper.dto.response.QrCodeResponse;
import faculdade.mercadopago.entity.pagamento.QrCodeRes;

public class PagamentoMapper {
    public static QrCodeResponse toResponse(QrCodeRes entity) {
        return new QrCodeResponse(
                entity.in_store_order_id(),
                entity.qr_data()
        );
    }
}