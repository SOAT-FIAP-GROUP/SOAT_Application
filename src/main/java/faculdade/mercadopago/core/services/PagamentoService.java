package faculdade.mercadopago.core.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import faculdade.mercadopago.AppConstants;
import faculdade.mercadopago.adapter.driven.entity.PagamentoEntity;
import faculdade.mercadopago.adapter.driven.repository.PagamentoRepository;
import faculdade.mercadopago.core.applications.ports.ApiResponse;
import faculdade.mercadopago.core.domain.dto.NewPaymentDto;
import faculdade.mercadopago.core.domain.enums.StatusPedidoEnum;
import faculdade.mercadopago.core.domain.model.PixPaymentResponse;
import faculdade.mercadopago.core.domain.model.QrOrderPagamentoResponse;
import faculdade.mercadopago.core.domain.model.QrOrderRequest;
import faculdade.mercadopago.core.domain.model.QrOrderResponse;
import faculdade.mercadopago.gateway.persistence.jpa.PedidoRepository;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class PagamentoService {
    private final PagamentoRepository _pagamentoRepository;
    private final MercadoPagoService _mercadoPagoService;
    private final PedidoRepository _pedidoRepository;
    private final PedidoService _pedidoService;

    public PagamentoService(PagamentoRepository pagamentoRepository, MercadoPagoService mercadoPagoService,
                            PedidoRepository pedidoRepository, PedidoService pedidoService) {
        _pagamentoRepository = pagamentoRepository;
        _mercadoPagoService = mercadoPagoService;
        _pedidoRepository = pedidoRepository;
        _pedidoService = pedidoService;
    }

    public ApiResponse<QrOrderResponse> GerarQrCode(NewPaymentDto paymentDto) {
        var listDto = new ArrayList<QrOrderRequest.ItemRequest>();
        for (NewPaymentDto.ItemPedidoDto item : paymentDto.getItens()){
            var itemRequest = new QrOrderRequest.ItemRequest(
                    item.getCodigo().toString(),
                    "lanche",
                    "titulo",
                    "",
                    item.quantidade,
                    "unit",
                    item.Valor,
                    item.Valor.multiply(BigDecimal.valueOf(item.quantidade)));
            listDto.add(itemRequest);
        }

        var request = new QrOrderRequest(
                String.valueOf(paymentDto.OrderId),
                "Lanchonete",
                "",
                paymentDto.TotalAmount,
                AppConstants.NOTIFICATION_URL,
                listDto
        );

        var url = AppConstants.BASEURL_MERCADOPAGO + AppConstants.GENERATEQRCODEURL_MERCADOPAGO;
        var response = _mercadoPagoService.SendRequest(url, HttpMethod.POST, request, QrOrderResponse.class, null);
        var apiResponse = new ApiResponse<QrOrderResponse>();

        if (response.getStatusCode().is2xxSuccessful()) {
            apiResponse.setSuccess(true);
            apiResponse.setData((QrOrderResponse) response.getBody());
        } else {
            apiResponse.setSuccess(false);
            try {
                var mapper = new ObjectMapper();
                var errorResponse = mapper.readValue((String) response.getBody(), ApiResponse.Err.class);
                apiResponse.addError("Erro ao gerar QR Code: " + errorResponse.getError(), errorResponse.getMessage());
            } catch (Exception ex) {
                apiResponse.addError("Erro ao interpretar resposta de erro: ",(String) response.getBody());
            }
        }

        return apiResponse;
    }

    public ApiResponse ConfirmaPagamento (QrOrderPagamentoResponse orderResponse) throws Exception {
        var url = AppConstants.BASEURL_MERCADOPAGO + AppConstants.CONFIRMPAYMENT_MERCADOPAGO + "/" + orderResponse.getId();
        var response = _mercadoPagoService.SendRequest(url, HttpMethod.GET, PixPaymentResponse.class);
        var apiResponse = new ApiResponse<>();

        if (response.getStatusCode().is2xxSuccessful()) {
            apiResponse.setSuccess(true);
            apiResponse.setData((PixPaymentResponse) response.getBody());

            PixPaymentResponse body = (PixPaymentResponse) response.getBody();
            String codigo = body.getExternal_reference();
            String status = body.getStatus();
            Double valorPago = 0.0;
            if (body.getTransactionDetails() != null) {
                valorPago = body.getTransactionDetails().getTotal_paid_amount();
            }

            if (status.equals("approved")) {
                var resultado = CreatePagamento(Long.parseLong(codigo), BigDecimal.valueOf(valorPago), status);
                if (resultado) {
                    apiResponse.setData(null);
                    _pedidoService.alterarPedido(Long.parseLong(codigo), StatusPedidoEnum.EM_PREPARACAO);
                    _pedidoService.adicionarPedidoNaFila(Long.parseLong(codigo));
                }
            } else {
                apiResponse.setSuccess(false);
                apiResponse.addError("Pagamento","Pagamento não foi aprovado.");
            }
        } else {
            apiResponse.setSuccess(false);
            try {
                var mapper = new ObjectMapper();
                var errorResponse = mapper.readValue((String) response.getBody(), ApiResponse.Err.class);
                apiResponse.addError("Erro na confirmação de pagamento: " + errorResponse.getError(), errorResponse.getMessage());
            } catch (Exception ex) {
                apiResponse.addError("Erro ao interpretar resposta: ",(String) response.getBody());
            }
        }
        return apiResponse;
    }

    public boolean CreatePagamento(long orderId, BigDecimal value, String status) throws Exception {
        var pedido = _pedidoRepository.findById(orderId).orElseThrow(() -> new Exception("Produto não encontrado"));

        PagamentoEntity pagamento = new PagamentoEntity(
                pedido,
                value,
                status
        );

        try {
            var obj = _pagamentoRepository.save(pagamento);
            return obj.getId() != null;
        } catch (Exception e) {
            ApiResponse<PagamentoEntity> response = new ApiResponse<>();
            response.setSuccess(false);
            response.addError("Erro ao salvar pagamento", e.getMessage());
            return false;
        }
    }
}
