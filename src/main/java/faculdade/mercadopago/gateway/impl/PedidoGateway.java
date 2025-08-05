package faculdade.mercadopago.gateway.impl;

import faculdade.mercadopago.controller.mapper.PedidoMapper;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.gateway.entity.PedidoEntity;
import faculdade.mercadopago.gateway.entity.ProdutoEntity;
import faculdade.mercadopago.gateway.persistence.jpa.PedidoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PedidoGateway implements IPedidoGateway {

    private final PedidoRepository pedidoRepository;

    public PedidoGateway(PedidoRepository pedidoRepository) {

        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id).map(PedidoEntity::toModel);
    }

    @Override
    public Pedido save(Pedido entity) {
        return pedidoRepository.save(PedidoMapper.toEntityPersistence(entity)).toModel();
    }

    @Override
    public List<Pedido> findAllByStatus(StatusPedidoEnum status) {
        return pedidoRepository.findAllByStatus(status).stream()
                .map(PedidoEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Pedido> findAllOrdenado() {
        return pedidoRepository.findAllOrdenado().stream()
                .map(PedidoEntity::toModel)
                .collect(Collectors.toList());
    }
}
