package faculdade.mercadopago.gateway;

import faculdade.mercadopago.entity.Categoria;

import java.util.Optional;

public interface ICategoriaGateway {

    Optional<Categoria> findById(Long id);
}
