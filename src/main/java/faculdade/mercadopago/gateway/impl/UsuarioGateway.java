package faculdade.mercadopago.gateway.impl;

import faculdade.mercadopago.controller.mapper.UsuarioMapper;
import faculdade.mercadopago.entity.Usuario;
import faculdade.mercadopago.gateway.IUsuarioGateway;
import faculdade.mercadopago.gateway.entity.UsuarioEntity;
import faculdade.mercadopago.gateway.persistence.jpa.UsuarioRepository;

import java.util.Optional;

public class UsuarioGateway implements IUsuarioGateway {

    private final UsuarioRepository usuarioRepositoty;

    public UsuarioGateway(UsuarioRepository usuarioRepositoty) {
        this.usuarioRepositoty = usuarioRepositoty;
    }

    @Override
    public Optional<Usuario> buscarUsuarioCpf(String cpf) {
        return usuarioRepositoty.findByCpf(cpf).map(UsuarioEntity::toModel);
    }

    @Override
    public Usuario save(Usuario entity) {
        return usuarioRepositoty.save(UsuarioMapper.toEntityPersistence(entity))
                .toModel();
    }
}
