package faculdade.mercadopago.usercase;

import faculdade.mercadopago.entity.Produto;
import faculdade.mercadopago.gateway.IProdutoGateway;

import java.util.List;

public interface IProdutoUseCase {

    Produto cadastrarProduto(Produto entity, IProdutoGateway gateway);

    Produto buscarProduto(Long id, IProdutoGateway gateway);

    Produto atualizarProduto(Produto entity, IProdutoGateway gateway);

    void removerProduto(Long id, IProdutoGateway gateway);

    List<Produto> buscarProdutosPorCategoria(Long id, IProdutoGateway gateway);


}
