package faculdade.mercadopago.gateway.impl;

import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.gateway.persistence.jpa.PedidoRepository;
import faculdade.mercadopago.gateway.persistence.jpa.ProdutoRepository;

import java.util.Optional;

public class PedidoGateway implements IPedidoGateway {

    private final PedidoRepository pedidoRepository;

    public PedidoGateway(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return Optional.empty();
    }
}
