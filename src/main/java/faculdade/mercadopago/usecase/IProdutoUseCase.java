package faculdade.mercadopago.usecase;

import faculdade.mercadopago.entity.Categoria;
import faculdade.mercadopago.entity.Produto;

import java.util.List;

public interface IProdutoUseCase {

    Produto cadastrarProduto(Produto entity);

    Produto buscarProduto(Long id);

    Produto atualizarProduto(Long id, Produto entity);

    void removerProduto(Long id);

    List<Produto> buscarProdutosPorCategoria(Long id);

    Categoria buscarCategoria(Long id);


}
