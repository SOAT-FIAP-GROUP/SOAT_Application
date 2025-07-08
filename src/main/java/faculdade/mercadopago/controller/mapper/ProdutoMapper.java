package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.controller.mapper.dto.response.ProdutoResponse;
import faculdade.mercadopago.entity.Produto;
import faculdade.mercadopago.gateway.entity.ProdutoEntity;

public class ProdutoMapper {


    public static ProdutoResponse toResponse(Produto entity){
        return new ProdutoResponse(entity.id(), entity.nome(), entity.descricao(),
                CategoriaMapper.toResponse(entity.categoria()), entity.preco(), entity.tempopreparo());
    }

    public static Produto toEntity(ProdutoEntity produtoEntity) {
        return new Produto(produtoEntity.getCodigo(), produtoEntity.getNome(), produtoEntity.getDescricao(),
                CategoriaMapper.toEntity(produtoEntity.getCategoria()),produtoEntity.getPreco(), produtoEntity.getTempopreparo());
    }
}
