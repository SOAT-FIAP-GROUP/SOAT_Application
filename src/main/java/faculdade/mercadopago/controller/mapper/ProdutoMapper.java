package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.controller.mapper.dto.request.ProdutoRequest;
import faculdade.mercadopago.controller.mapper.dto.response.ProdutoResponse;
import faculdade.mercadopago.entity.Categoria;
import faculdade.mercadopago.entity.Produto;
import faculdade.mercadopago.gateway.entity.ProdutoEntity;

public class ProdutoMapper {

    public ProdutoResponse toResponse(Produto entity){
        return new ProdutoResponse(entity.id(), entity.nome(), entity.descricao(),
                CategoriaMapper.toResponse(entity.categoria()), entity.preco(), entity.tempopreparo());
    }

    public static Produto toEntity(ProdutoRequest produto) {
        Categoria categoria = new Categoria(produto.categoriaId(), null);
        return new Produto(null, produto.nome(), produto.descricao(),
                categoria, produto.preco(), produto.tempopreparo());
    }

    public static ProdutoEntity toEntityPersistence(Produto entity) {
        return new ProdutoEntity(entity.id(), entity.nome(), entity.descricao(),
                CategoriaMapper.toEntityPersistence(entity.categoria()),entity.preco(), entity.tempopreparo());
    }

    public static Produto toResponseByRes(ProdutoResponse response){
        return new Produto(response.id(), response.nome(), response.descricao(), CategoriaMapper.toResponseByRes(response.categoria()), response.preco(), response.tempopreparo());
    }
}
