package faculdade.mercadopago.gateway.impl;

import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.gateway.IPedidoGateway;

import java.util.Optional;

public class PedidoGateway implements IPedidoGateway {

    @Override
    public Optional<Pedido> findById(Long id) {
        return Optional.empty();
    }
}
