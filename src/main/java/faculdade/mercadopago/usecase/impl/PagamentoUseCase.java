package faculdade.mercadopago.usecase.impl;

import faculdade.mercadopago.AppConstants;
import faculdade.mercadopago.controller.mapper.dto.request.QrCodeRequest;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.pagamento.ConfirmacaoPagamentoRes;
import faculdade.mercadopago.entity.pagamento.QrCodeOrder;
import faculdade.mercadopago.entity.pagamento.QrCodeRes;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.usecase.IPagamentoUseCase;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PagamentoUseCase implements IPagamentoUseCase {
    private final IPagamentoGateway pagamentoGateway;

    public PagamentoUseCase(IPagamentoGateway pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    @Override
    public QrCodeRes processarQrCode(QrCodeRequest request) {
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
        return pagamentoGateway.sendRequest(url, HttpMethod.POST, qrcoderequest, QrCodeRes.class, null).getBody();
    }

    @Override
    public void salvarPagamento(Pedido pedido, BigDecimal valor) {
        pagamentoGateway.save(pedido, valor);
    }

    @Override
    public ResponseEntity<?> buscarDados(String url, HttpMethod http, Class<ConfirmacaoPagamentoRes> confirmacaoPagamentoResClass) {
        return pagamentoGateway.sendRequest(url, http, confirmacaoPagamentoResClass);
    }
}