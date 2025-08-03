package faculdade.mercadopago.usecase.impl;

import faculdade.mercadopago.controller.mapper.dto.request.UsuarioRequest;
import faculdade.mercadopago.entity.Usuario;
import faculdade.mercadopago.exception.BusinessException;
import faculdade.mercadopago.exception.EntityNotFoundException;
import faculdade.mercadopago.gateway.IUsuarioGateway;
import faculdade.mercadopago.usecase.IUsuarioUseCase;

import java.util.Optional;

public class UsuarioUseCase implements IUsuarioUseCase {

    private final IUsuarioGateway gateway;

    public UsuarioUseCase(IUsuarioGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Usuario buscarUsuarioPorCpf(String cpf) {
        return gateway.buscarUsuarioCpf(cpf).orElseThrow(
                () -> new EntityNotFoundException(Usuario.class, cpf)
        );
    }

    @Override
    public Usuario processarUsuario(UsuarioRequest request) throws BusinessException {
        if (!request.identificarUsuario()) {
            return carregarUsuarioPadrao();
        }

        Optional<Usuario> usuarioExistente = gateway.buscarUsuarioCpf(request.cpf());

        if (usuarioExistente.isPresent()) {
            throw new BusinessException(
                    "Usuário já cadastrado com o CPF: " + request.cpf()
            );
        }

        Usuario usuario = new Usuario(
                null,
                request.nome(),
                request.cpf(),
                request.email()
        );
        return gateway.save(usuario);
    }

    private Usuario carregarUsuarioPadrao() {
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

    public Usuario buscaUsuarioPorId(Long codigo){
        return gateway.findById(codigo).orElseThrow(() -> new EntityNotFoundException(Usuario.class, "Código Usuário não localizado na base"));
    }
}
