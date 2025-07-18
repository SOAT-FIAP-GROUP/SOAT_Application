package faculdade.mercadopago.usecase;

import faculdade.mercadopago.controller.mapper.dto.request.UsuarioRequest;
import faculdade.mercadopago.entity.Usuario;
import faculdade.mercadopago.gateway.IUsuarioGateway;

public interface IUsuarioUseCase {

    Usuario buscarUsuarioPorCpf(String cpf, IUsuarioGateway gateway);

    Usuario processarUsuario(UsuarioRequest request, IUsuarioGateway gateway);
}
