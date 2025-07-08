package faculdade.mercadopago.usercase.impl;

import faculdade.mercadopago.entity.Produto;
import faculdade.mercadopago.gateway.IProdutoGateway;
import faculdade.mercadopago.usercase.IProdutoUseCase;

import java.util.List;

public class ProdutoUserCase implements IProdutoUseCase {
    @Override
    public Produto cadastrarProduto(Produto entity, IProdutoGateway gateway) {
        return null;
    }

    @Override
    public Produto buscarProduto(Long id, IProdutoGateway gateway) {
        return gateway.findById(id).orElseThrow();
    }

    @Override
    public Produto atualizarProduto(Produto entity, IProdutoGateway gateway) {
        return null;
    }

    @Override
    public void removerProduto(Long id, IProdutoGateway gateway) {

    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(Long id, IProdutoGateway gateway) {
        return List.of();
    }
}
