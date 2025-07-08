package faculdade.mercadopago.gateway.impl;

import faculdade.mercadopago.entity.Produto;
import faculdade.mercadopago.gateway.IProdutoGateway;
import faculdade.mercadopago.gateway.entity.ProdutoEntity;
import faculdade.mercadopago.gateway.persistence.jpa.ProdutoRepository;

import java.util.Optional;

public class ProdutoGateway implements IProdutoGateway {

    private final ProdutoRepository produtoRepository;

    public ProdutoGateway(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Optional<Produto> findById(Long id) {
       return produtoRepository.findById(id).map(ProdutoEntity::toModel);
    }
}
