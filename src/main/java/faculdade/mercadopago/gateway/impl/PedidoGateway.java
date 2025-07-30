package faculdade.mercadopago.gateway.impl;

import faculdade.mercadopago.controller.mapper.PedidoMapper;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.gateway.persistence.jpa.PedidoRepository;

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

    @Override
    public Pedido save(Pedido entity) {
        return pedidoRepository.save(PedidoMapper.toEntityPersistence(entity)).toModel();
    }
}
