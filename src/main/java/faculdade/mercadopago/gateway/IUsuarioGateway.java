package faculdade.mercadopago.gateway;

import faculdade.mercadopago.entity.Usuario;

import java.util.Optional;

public interface IUsuarioGateway {

    Optional<Usuario> buscarUsuarioCpf(String cpf);

    Usuario save(Usuario entity);
}
