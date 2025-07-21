package faculdade.mercadopago.gateway.impl;

import faculdade.mercadopago.AppConstants;
import faculdade.mercadopago.entity.pagamento.QrCodeOrder;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.gateway.entity.PagamentoEntity;
import faculdade.mercadopago.gateway.persistence.jpa.PagamentoRepository;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class PagamentoGateway implements IPagamentoGateway {
    private final PagamentoRepository pagamentoRepository;
    private final RestTemplate _restTemplate = new RestTemplate();

    public PagamentoGateway(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @Override
    public <T, R> R sendRequest(String url, HttpMethod method, T request, Class<R> responseType, Map<String, String> extraHeaders) {
        try {
            var headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + AppConstants.ACCESS_TOKEN);
            headers.setContentType(MediaType.APPLICATION_JSON);

            if (extraHeaders != null) {
                extraHeaders.forEach(headers::set);
            }

            var entity = method == HttpMethod.GET || method == HttpMethod.DELETE
                    ? new HttpEntity<>(headers)
                    : new HttpEntity<>(request, headers);

            return _restTemplate.exchange(url, method, entity, responseType).getBody();

        } catch (HttpStatusCodeException ex)  {
            throw new Error(String.valueOf(ResponseEntity
                    .status(ex.getStatusCode())
                    .body(ex.getResponseBodyAsString())));
        } catch (Exception ex) {
            throw new Error(String.valueOf(ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno: " + ex.getMessage())));
        }
    }

    @Override
    public <R> QrCodeOrder sendRequest(String url, HttpMethod method, Class<R> responseType) {
        return null;
    }

    @Override
    public PagamentoEntity save(PagamentoEntity pagamento) {
        return null;
    }


}