package faculdade.mercadopago.gateway;

import faculdade.mercadopago.entity.pagamento.QrCodeOrder;
import faculdade.mercadopago.gateway.entity.PagamentoEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Map;

public interface IPagamentoGateway {

    <T, R> ResponseEntity<R> sendRequest(String url,
                         HttpMethod method,
                         T request,
                         Class<R> responseType,
                         Map<String, String> extraHeaders);

    <R> ResponseEntity<?> sendRequest(
            String url,
            HttpMethod method,
            Class<R> responseType
    );

//    boolean criarPagamento(long orderId, BigDecimal value, String status);

    PagamentoEntity save(PagamentoEntity pagamento);

}