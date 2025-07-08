package faculdade.mercadopago.gateway;

import faculdade.mercadopago.entity.Produto;

import java.util.Optional;

public interface IProdutoGateway {

    Optional<Produto> findById(Long id);
}
