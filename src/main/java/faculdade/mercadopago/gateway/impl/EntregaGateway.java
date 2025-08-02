package faculdade.mercadopago.gateway.impl;

import faculdade.mercadopago.controller.mapper.EntregaMapper;
import faculdade.mercadopago.entity.Entrega;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.gateway.entity.EntregaEntity;
import faculdade.mercadopago.gateway.persistence.jpa.EntregaRepository;

import java.util.Optional;

public class EntregaGateway implements IEntregaGateway {

    private final EntregaRepository entregaRepository;

    public EntregaGateway(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    @Override
    public Optional<Entrega> findById(Long id) {
        return this.entregaRepository.findById(id).map(EntregaEntity::toModel);
    }

    @Override
    public Entrega save(Entrega entity) {
        return this.entregaRepository.save(EntregaMapper.toEntityPersistence(entity)).toModel();
    }
}
