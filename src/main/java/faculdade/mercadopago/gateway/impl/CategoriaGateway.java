package faculdade.mercadopago.gateway.impl;

import faculdade.mercadopago.entity.Categoria;
import faculdade.mercadopago.gateway.ICategoriaGateway;
import faculdade.mercadopago.gateway.entity.CategoriaEntity;
import faculdade.mercadopago.gateway.persistence.jpa.CategoriaRepository;

import java.util.Optional;

public class CategoriaGateway implements ICategoriaGateway {

    private final CategoriaRepository categoriaRepository;

    public CategoriaGateway(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id).map(CategoriaEntity::toModel);
    }
}
