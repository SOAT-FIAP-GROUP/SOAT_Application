package faculdade.mercadopago.mocks;

import faculdade.mercadopago.controller.mapper.CategoriaMapper;
import faculdade.mercadopago.controller.mapper.ProdutoMapper;
import faculdade.mercadopago.controller.mapper.dto.request.ProdutoRequest;
import faculdade.mercadopago.controller.mapper.dto.response.CategoriaResponse;
import faculdade.mercadopago.controller.mapper.dto.response.ProdutoResponse;
import faculdade.mercadopago.entity.Categoria;
import faculdade.mercadopago.entity.Produto;

import java.math.BigDecimal;
import java.sql.Time;

public class MockGenerator {

    private static final Long ID = 1L;

    public static Produto generateProdutoMock(){
        Produto produto = new Produto(ID,
                "batata recheada",
                "batata recheada e é isso",
                generateCategoriaMock(),
                BigDecimal.valueOf(9.89D),
                Time.valueOf("0:10:00"));
        return produto;
    }

    public static Categoria generateCategoriaMock(){
        Categoria categoria = new Categoria(ID, "LANCHE");
        return categoria;
    }

    public static ProdutoResponse generateProdutoResponseMock() {
        ProdutoMapper produtoMapper = new ProdutoMapper();
        return produtoMapper.toResponse(generateProdutoMock());
    }

    public static ProdutoRequest generateProdutoRequestMock() {
        ProdutoRequest produtoRequest = new ProdutoRequest(
                "batata recheada",
                "batata recheada e é isso",
                generateCategoriaMock(),
                BigDecimal.valueOf(9.89D),
                Time.valueOf("0:10:00"));
        return produtoRequest;
    }

    public static CategoriaResponse generateCategoriaResponseMock() {
        CategoriaMapper categoriaMapper = new CategoriaMapper();
        return categoriaMapper.toResponse(generateCategoriaMock());
    }
}
