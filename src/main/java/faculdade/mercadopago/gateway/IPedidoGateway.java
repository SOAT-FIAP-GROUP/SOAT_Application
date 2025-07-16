package faculdade.mercadopago.gateway;

import faculdade.mercadopago.entity.Pedido;

import java.util.Optional;

public interface IPedidoGateway {
    Optional<Pedido> findById(Long id);
}
