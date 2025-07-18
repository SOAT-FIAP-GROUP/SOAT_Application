package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.UsuarioMapper;
import faculdade.mercadopago.controller.mapper.dto.request.UsuarioRequest;
import faculdade.mercadopago.controller.mapper.dto.response.UsuarioResponse;
import faculdade.mercadopago.gateway.IUsuarioGateway;
import faculdade.mercadopago.usecase.IUsuarioUseCase;

public class UsuarioController {

    private final IUsuarioUseCase usuarioUseCase;
    private final IUsuarioGateway usuarioGateway;
    private final UsuarioMapper usuarioMapper;

    public UsuarioController(IUsuarioUseCase usuarioUseCase, IUsuarioGateway usuarioGateway, UsuarioMapper usuarioMapper) {
        this.usuarioUseCase = usuarioUseCase;
        this.usuarioGateway = usuarioGateway;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioResponse buscarUsuarioPorCpf(String cpf){
        return UsuarioMapper.toResponse(usuarioUseCase.buscarUsuarioPorCpf(cpf, usuarioGateway));
    }

    public UsuarioResponse processarUsuario(UsuarioRequest request){
        return UsuarioMapper.toResponse(usuarioUseCase.processarUsuario(request, usuarioGateway));
    }
}
