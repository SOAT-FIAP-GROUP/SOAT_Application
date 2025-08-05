package faculdade.mercadopago.gateway;

import faculdade.mercadopago.entity.Usuario;

import java.util.Optional;

public interface IUsuarioGateway {

    Optional<Usuario> buscarUsuarioCpf(String cpf);

    Optional<Usuario> findById(Long id);

    Usuario save(Usuario entity);
}
