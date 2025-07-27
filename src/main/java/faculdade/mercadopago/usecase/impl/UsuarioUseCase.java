package faculdade.mercadopago.usecase.impl;

import faculdade.mercadopago.controller.mapper.dto.request.UsuarioRequest;
import faculdade.mercadopago.entity.Usuario;
import faculdade.mercadopago.exception.EntityNotFoundException;
import faculdade.mercadopago.gateway.IUsuarioGateway;
import faculdade.mercadopago.usecase.IUsuarioUseCase;

import java.util.Optional;

public class UsuarioUseCase implements IUsuarioUseCase {
    @Override
    public Usuario buscarUsuarioPorCpf(String cpf, IUsuarioGateway gateway) {
        return gateway.buscarUsuarioCpf(cpf).orElseThrow(
                () -> new EntityNotFoundException(Usuario.class, cpf)
        );
    }

    @Override
    public Usuario processarUsuario(UsuarioRequest request, IUsuarioGateway gateway) {
        if (!request.identificarUsuario()) {
            return carregarUsuarioPadrao(gateway);
        }

        Usuario usuario = new Usuario(
                null,
                request.nome(),
                request.cpf(),
                request.email()
        );
        return gateway.save(usuario);
    }

    private Usuario carregarUsuarioPadrao(IUsuarioGateway gateway) {
        Optional<Usuario> usuario = gateway.buscarUsuarioCpf("00000000000");

        if (usuario.isEmpty()) {
            Usuario novo = new Usuario(
                    null,
                    "USUARIO PADRAO",
                    "00000000000",
                    "padrao@email.com"
            );
            return gateway.save(novo);
        }

        return usuario.get();
    }
}
