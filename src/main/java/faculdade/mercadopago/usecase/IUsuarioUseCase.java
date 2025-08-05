package faculdade.mercadopago.usecase;

import faculdade.mercadopago.controller.mapper.dto.request.UsuarioRequest;
import faculdade.mercadopago.entity.Usuario;

public interface IUsuarioUseCase {

    Usuario buscarUsuarioPorCpf(String cpf);

    Usuario processarUsuario(UsuarioRequest request);

    Usuario buscaUsuarioPorId(Long codigo);
}
