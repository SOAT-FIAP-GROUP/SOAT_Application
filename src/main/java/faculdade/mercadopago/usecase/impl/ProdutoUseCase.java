package faculdade.mercadopago.usecase.impl;

import faculdade.mercadopago.entity.Categoria;
import faculdade.mercadopago.entity.Produto;
import faculdade.mercadopago.exception.EntityNotFoundException;
import faculdade.mercadopago.gateway.ICategoriaGateway;
import faculdade.mercadopago.gateway.IProdutoGateway;
import faculdade.mercadopago.usecase.IProdutoUseCase;

import java.util.List;

public class ProdutoUseCase implements IProdutoUseCase {

    private final IProdutoGateway produtoGateway;
    private final ICategoriaGateway categoriaGateway;

    public ProdutoUseCase(IProdutoGateway produtoGateway, ICategoriaGateway categoriaGateway) {
        this.produtoGateway = produtoGateway;
        this.categoriaGateway = categoriaGateway;
    }

    @Override
    public Produto cadastrarProduto(Produto entity) {
        return produtoGateway.save(entity);
    }

    @Override
    public Produto buscarProduto(Long id) {
        return produtoGateway.findById(id).orElseThrow(() -> new EntityNotFoundException(Produto.class, id));
    }

    @Override
    public Produto atualizarProduto(Long id, Produto entity) {
        this.buscarProduto(id);
        return produtoGateway.save(entity);
    }

    @Override
    public void removerProduto(Long id) {
        var produto = this.buscarProduto(id);
        produtoGateway.removerProduto(produto);
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(Long id) {
        return produtoGateway.findByCategoriaCodigo(id);
    }

    @Override
    public Categoria buscarCategoria(Long id) {
        return categoriaGateway.findById(id).orElseThrow(() -> new EntityNotFoundException(Categoria.class, id));
    }
}
