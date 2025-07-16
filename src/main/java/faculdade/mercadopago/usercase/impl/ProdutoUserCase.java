package faculdade.mercadopago.usercase.impl;

import faculdade.mercadopago.entity.Categoria;
import faculdade.mercadopago.entity.Produto;
import faculdade.mercadopago.gateway.ICategoriaGateway;
import faculdade.mercadopago.gateway.IProdutoGateway;
import faculdade.mercadopago.usercase.IProdutoUseCase;

import java.util.List;

public class ProdutoUserCase implements IProdutoUseCase {
    @Override
    public Produto cadastrarProduto(Produto entity, IProdutoGateway gateway) {
        return gateway.save(entity);
    }

    @Override
    public Produto buscarProduto(Long id, IProdutoGateway gateway) {
        return gateway.findById(id).orElseThrow();
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
        return gateway.findById(id).orElseThrow();
    }
}
