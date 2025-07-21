package faculdade.mercadopago.gateway;

import faculdade.mercadopago.entity.pagamento.QrCodeOrder;
import faculdade.mercadopago.gateway.entity.PagamentoEntity;
import org.springframework.http.HttpMethod;

import java.util.Map;

public interface IPagamentoGateway {

    <T, R> R sendRequest(String url,
                         HttpMethod method,
                         T request,
                         Class<R> responseType,
                         Map<String, String> extraHeaders);

    <R> QrCodeOrder sendRequest(
            String url,
            HttpMethod method,
            Class<R> responseType
    );

    PagamentoEntity save(PagamentoEntity pagamento);

}