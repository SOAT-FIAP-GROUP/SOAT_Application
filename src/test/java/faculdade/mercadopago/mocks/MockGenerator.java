package faculdade.mercadopago.mocks;

import faculdade.mercadopago.controller.mapper.CategoriaMapper;
import faculdade.mercadopago.controller.mapper.PagamentoMapper;
import faculdade.mercadopago.controller.mapper.PedidoMapper;
import faculdade.mercadopago.controller.mapper.ProdutoMapper;
import faculdade.mercadopago.controller.mapper.dto.request.*;
import faculdade.mercadopago.controller.mapper.dto.response.*;
import faculdade.mercadopago.entity.*;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.entity.pagamento.ConfirmacaoPagamentoRes;
import faculdade.mercadopago.entity.pagamento.QrCodeRes;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class MockGenerator {

    private static final Long ID = 1L;

    public static Produto generateProdutoMock() {
        Produto produto = new Produto(ID,
                "batata recheada",
                "batata recheada e é isso",
                generateCategoriaMock(),
                BigDecimal.valueOf(9.89D),
                Time.valueOf("0:10:00"));
        return produto;
    }

    public static Categoria generateCategoriaMock() {
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

    public static Pedido generatePedidoMock() {
        Pedido pedido = new Pedido(ID, ID, StatusPedidoEnum.PRONTO,
                BigDecimal.valueOf(50), LocalDateTime.now(),
                Time.valueOf("00:10:00"), List.of());
        return pedido;
    }

    public static Entrega generateEntregaMock() {
        Entrega entrega = new Entrega(null, generatePedidoMock(), LocalDateTime.now());
        return entrega;
    }

    public static FilaPedidosPreparacao generateFilaPedidosPreparacaoMock() {
        FilaPedidosPreparacao fila = new FilaPedidosPreparacao(ID, generatePedidoMock());
        return fila;
    }

    public static QrCodeRequest generateQrCodeRequestMock() {
        return new QrCodeRequest(
                123L,
                100.0,
                List.of(new QrCodeRequest.ItemPedido(ID, 2, BigDecimal.valueOf(50)))
        );
    }

    public static QrCodeRes generateQrCodeResMock() {
        return new QrCodeRes(
                "mocked_qrcode",
                "mocked_url"
        );
    }

    public static PedidoResponse generatePedidoResponseMock() {
        PedidoMapper pedidoMapper = new PedidoMapper();
        return pedidoMapper.toResponse(generatePedidoMock());
    }

    public static PedidoRequest generatePedidoRequestMock() {
        PedidoItemRequest item1 = new PedidoItemRequest(ID, 2); // produtoId = 1, quantidade = 2
        PedidoItemRequest item2 = new PedidoItemRequest(2L, 1); // produtoId = 2, quantidade = 1

        return new PedidoRequest(
                ID,
                List.of(item1, item2)
        );
    }

    public static Pedido generatePedidoMockComItensVazios() {
        return new Pedido(
                null,
                ID,
                StatusPedidoEnum.RECEBIDO,
                null,
                LocalDateTime.now(),
                null,
                List.of()
        );
    }

    public static Pedido generatePedidoMockSemId() {
        PedidoItem item1 = new PedidoItem(null, null,ID,2, new BigDecimal("10.00"), new BigDecimal("20.00")
        );

        PedidoItem item2 = new PedidoItem(null, null,2L,1, new BigDecimal("15.00"),new BigDecimal("15.00"));

        return new Pedido(
                null,
                ID,
                StatusPedidoEnum.RECEBIDO,
                null,
                LocalDateTime.now(),
                null,
                List.of(item1, item2)
        );
    }

    public static Usuario generateUsuarioMock(){
        return new Usuario(ID, "Teste", "92902198078", "teste@email.com");
    }

    public static Usuario generateUsuarioPadraoMock(){
        return new Usuario(ID, "USUARIO PADRAO", "00000000000", "padrao@email.com");
    }

    public static UsuarioRequest generateUsuarioResquestIdentificadoTrueMock(){
        return new UsuarioRequest(true,"Teste", "92902198078", "teste@email.com");
    }

    public static UsuarioRequest generateUsuarioResquestIdentificadoFalseMock(){
        return new UsuarioRequest(false,"Teste", "92902198078", "teste@email.com");
    }

    public static UsuarioResponse generateUsuarioResponseMock(){
        return new UsuarioResponse(ID,"Teste", "92902198078", "teste@email.com");
    }

}
