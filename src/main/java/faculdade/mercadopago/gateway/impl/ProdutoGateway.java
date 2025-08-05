package faculdade.mercadopago.gateway.impl;

import faculdade.mercadopago.controller.mapper.ProdutoMapper;
import faculdade.mercadopago.entity.Produto;
import faculdade.mercadopago.gateway.IProdutoGateway;
import faculdade.mercadopago.gateway.entity.ProdutoEntity;
import faculdade.mercadopago.gateway.persistence.jpa.ProdutoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProdutoGateway implements IProdutoGateway {

    private final ProdutoRepository produtoRepository;

    public ProdutoGateway(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Optional<Produto> findById(Long id) {
       return produtoRepository.findById(id).map(ProdutoEntity::toModel);
    }

    @Override
    public List<Produto> findByCategoriaCodigo(Long id) {
        return produtoRepository.findByCategoriaCodigo(id).stream()
                .map(ProdutoEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void removerProduto(Produto entity) {
        produtoRepository.delete(
                ProdutoMapper.toEntityPersistence(entity));
    }

    @Override
    public Produto save(Produto entity) {
        return produtoRepository.save(
                ProdutoMapper.toEntityPersistence(entity))
                .toModel();
    }
}
