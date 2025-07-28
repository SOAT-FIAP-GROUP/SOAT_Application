package faculdade.mercadopago.usecase.impl;

import faculdade.mercadopago.AppConstants;
import faculdade.mercadopago.controller.mapper.dto.request.QrCodeRequest;
import faculdade.mercadopago.entity.pagamento.QrCodeOrder;
import faculdade.mercadopago.entity.pagamento.QrCodeRes;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.usecase.IPagamentoUseCase;
import org.springframework.http.HttpMethod;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PagamentoUseCase implements IPagamentoUseCase {
    @Override
    public QrCodeRes processarQrCode(QrCodeRequest request, IPagamentoGateway gateway) {
        var listDto = new ArrayList<QrCodeOrder.Item>();
        for (QrCodeRequest.ItemPedido item : request.Itens()) {
            var itemRequest = new QrCodeOrder.Item(
                    item.Codigo().toString(),
                    "lanche",
                    "titulo",
                    "",
                    item.quantidade(),
                    "unit",
                    item.Valor(),
                    item.Valor().multiply(BigDecimal.valueOf(item.quantidade())));
            listDto.add(itemRequest);
        }

        var qrcoderequest = new QrCodeOrder(
                String.valueOf(request.OrderId()),
                "Lanchonete",
                "",
                request.TotalAmount(),
                AppConstants.NOTIFICATION_URL,
                listDto
        );

        var url = AppConstants.BASEURL_MERCADOPAGO + AppConstants.GENERATEQRCODEURL_MERCADOPAGO;
        return gateway.sendRequest(url, HttpMethod.POST, qrcoderequest, QrCodeRes.class, null).getBody();

    }
}