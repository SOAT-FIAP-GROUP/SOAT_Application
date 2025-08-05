package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.UsuarioMapper;
import faculdade.mercadopago.controller.mapper.dto.request.UsuarioRequest;
import faculdade.mercadopago.controller.mapper.dto.response.UsuarioResponse;
import faculdade.mercadopago.usecase.IUsuarioUseCase;

public class UsuarioController {

    private final IUsuarioUseCase usuarioUseCase;
    private final UsuarioMapper usuarioMapper;

    public UsuarioController(IUsuarioUseCase usuarioUseCase, UsuarioMapper usuarioMapper) {
        this.usuarioUseCase = usuarioUseCase;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioResponse buscarUsuarioPorCpf(String cpf){
        return usuarioMapper.toResponse(usuarioUseCase.buscarUsuarioPorCpf(cpf));
    }

    public UsuarioResponse processarUsuario(UsuarioRequest request){
        return usuarioMapper.toResponse(usuarioUseCase.processarUsuario(request));
    }
}
