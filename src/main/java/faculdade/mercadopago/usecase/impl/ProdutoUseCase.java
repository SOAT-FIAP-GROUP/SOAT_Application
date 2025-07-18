package faculdade.mercadopago.usecase.impl;

import faculdade.mercadopago.entity.Categoria;
import faculdade.mercadopago.entity.Produto;
import faculdade.mercadopago.exception.EntityNotFoundException;
import faculdade.mercadopago.gateway.ICategoriaGateway;
import faculdade.mercadopago.gateway.IProdutoGateway;
import faculdade.mercadopago.usecase.IProdutoUseCase;


import java.util.List;

public class ProdutoUseCase implements IProdutoUseCase {
    @Override
    public Produto cadastrarProduto(Produto entity, IProdutoGateway gateway) {
        return gateway.save(entity);
    }

    @Override
    public Produto buscarProduto(Long id, IProdutoGateway gateway) {
        return gateway.findById(id).orElseThrow(() -> new EntityNotFoundException(Produto.class, id));
    }

    @Override
    public Produto atualizarProduto(Long id, Produto entity, IProdutoGateway gateway) {
        this.buscarProduto(id,gateway);
        return gateway.save(entity);
    }

    @Override
    public void removerProduto(Long id, IProdutoGateway gateway) {
        var produto = this.buscarProduto(id,gateway);
        gateway.removerProduto(produto);
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(Long id, IProdutoGateway gateway) {
        return gateway.findByCategoriaCodigo(id);
    }

    @Override
    public Categoria buscarCategoria(Long id, ICategoriaGateway gateway) {
        return gateway.findById(id).orElseThrow(() -> new EntityNotFoundException(Categoria.class, id));
    }
}
