package faculdade.mercadopago.gateway;

import faculdade.mercadopago.entity.Produto;
import faculdade.mercadopago.gateway.entity.ProdutoEntity;

import java.util.List;
import java.util.Optional;

public interface IProdutoGateway {

    Optional<Produto> findById(Long id);

    List<Produto> findByCategoriaCodigo(Long id);

    void removerProduto(Produto entity);

    Produto save(Produto entity);

}
