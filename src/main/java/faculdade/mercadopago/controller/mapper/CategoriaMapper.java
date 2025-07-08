package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.controller.mapper.dto.response.CategoriaResponse;
import faculdade.mercadopago.entity.Categoria;
import faculdade.mercadopago.gateway.entity.CategoriaEntity;

public class CategoriaMapper {

    public static CategoriaResponse toResponse(Categoria entity){
        return new CategoriaResponse(entity.id(), entity.nome());
    }

    public static Categoria toEntity(CategoriaEntity categoriaEntity){
        return new Categoria(categoriaEntity.getCodigo(), categoriaEntity.getNome());
    }
}
