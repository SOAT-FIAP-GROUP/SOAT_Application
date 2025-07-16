package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.controller.mapper.dto.request.ProdutoRequest;
import faculdade.mercadopago.controller.mapper.dto.response.ProdutoResponse;
import faculdade.mercadopago.entity.Produto;
import faculdade.mercadopago.gateway.entity.ProdutoEntity;

public class ProdutoMapper {

    public ProdutoResponse toResponse(Produto entity){
        return new ProdutoResponse(entity.id(), entity.nome(), entity.descricao(),
                CategoriaMapper.toResponse(entity.categoria()), entity.preco(), entity.tempopreparo());
    }

    public static Produto toEntity(ProdutoRequest produto) {
        return new Produto(null, produto.nome(), produto.descricao(),
                produto.categoria(), produto.preco(), produto.tempopreparo());
    }

    public static ProdutoEntity toEntityPersistence(Produto entity) {
        return new ProdutoEntity(entity.id(), entity.nome(), entity.descricao(),
                CategoriaMapper.toEntityPersistence(entity.categoria()),entity.preco(), entity.tempopreparo());
    }
}
