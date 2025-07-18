package faculdade.mercadopago.gateway;

import faculdade.mercadopago.entity.Entrega;

import java.util.Optional;

public interface IEntregaGateway {

    Optional<Entrega> findById (Long id);

    interface IUsuarioGateway {
    }
}
