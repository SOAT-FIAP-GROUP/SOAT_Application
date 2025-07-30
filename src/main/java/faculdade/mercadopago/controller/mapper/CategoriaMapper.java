package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.controller.mapper.dto.request.CategoriaRequest;
import faculdade.mercadopago.controller.mapper.dto.response.CategoriaResponse;
import faculdade.mercadopago.entity.Categoria;
import faculdade.mercadopago.gateway.entity.CategoriaEntity;

public class CategoriaMapper {

    public static CategoriaResponse toResponse(Categoria entity){
        return new CategoriaResponse(entity.id(), entity.nome());
    }

    public static Categoria toEntity(CategoriaRequest categoria){
        return new Categoria(categoria.id(), categoria.nome());
    }

    public static CategoriaEntity toEntityPersistence(Categoria entity){
        return new CategoriaEntity(entity.id(), entity.nome());
    }

    public static Categoria toResponseByRes(CategoriaResponse response){
        return new Categoria(response.id(), response.nome());
    }
}
