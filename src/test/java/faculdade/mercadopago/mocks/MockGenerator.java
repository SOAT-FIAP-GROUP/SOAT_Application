package faculdade.mercadopago.mocks;

import faculdade.mercadopago.controller.mapper.CategoriaMapper;
import faculdade.mercadopago.controller.mapper.ProdutoMapper;
import faculdade.mercadopago.controller.mapper.dto.request.ProdutoRequest;
import faculdade.mercadopago.controller.mapper.dto.response.CategoriaResponse;
import faculdade.mercadopago.controller.mapper.dto.response.ProdutoResponse;
import faculdade.mercadopago.entity.*;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

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
                ID,
                BigDecimal.valueOf(9.89D),
                Time.valueOf("0:10:00"));
        return produtoRequest;
    }

    public static CategoriaResponse generateCategoriaResponseMock() {
        CategoriaMapper categoriaMapper = new CategoriaMapper();
        return categoriaMapper.toResponse(generateCategoriaMock());
    }

    public static Pedido generatePedidoMock(){
        Pedido pedido = new Pedido(ID, ID, StatusPedidoEnum.PRONTO,
                BigDecimal.valueOf(50), LocalDateTime.now(),
                Time.valueOf("00:10:00"), List.of());
        return pedido;
    }

    public static Entrega generateEntregaMock(){
        Entrega entrega = new Entrega(null, generatePedidoMock(), LocalDateTime.now());
        return entrega;
    }

    public static FilaPedidosPreparacao generateFilaPedidosPreparacaoMock(){
        FilaPedidosPreparacao fila = new FilaPedidosPreparacao(ID, generatePedidoMock());
        return fila;
    }
}
