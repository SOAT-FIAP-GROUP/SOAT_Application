package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.controller.mapper.dto.response.UsuarioResponse;
import faculdade.mercadopago.entity.Usuario;
import faculdade.mercadopago.gateway.entity.UsuarioEntity;

public class UsuarioMapper {
    public UsuarioResponse toResponse(Usuario entity) {
        return new UsuarioResponse(entity.codigo(), entity.nome(), entity.cpf(), entity.email());
    }
    public static UsuarioEntity toEntityPersistence(Usuario entity) {
        return new UsuarioEntity(entity.codigo(), entity.nome(), entity.cpf(), entity.email());
    }


}
