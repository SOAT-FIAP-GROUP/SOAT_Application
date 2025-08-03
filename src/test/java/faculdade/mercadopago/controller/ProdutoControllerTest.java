package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.CategoriaMapper;
import faculdade.mercadopago.controller.mapper.ProdutoMapper;
import faculdade.mercadopago.controller.mapper.dto.request.ProdutoRequest;
import faculdade.mercadopago.controller.mapper.dto.response.CategoriaResponse;
import faculdade.mercadopago.controller.mapper.dto.response.ProdutoResponse;
import faculdade.mercadopago.entity.Categoria;
import faculdade.mercadopago.entity.Produto;
import faculdade.mercadopago.gateway.ICategoriaGateway;
import faculdade.mercadopago.gateway.IProdutoGateway;
import faculdade.mercadopago.mocks.MockGenerator;
import faculdade.mercadopago.usecase.IProdutoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ProdutoControllerTest {

    private ProdutoController produtoController;
    private IProdutoUseCase produtoUseCase;
    private IProdutoGateway produtoGateway;
    private ProdutoMapper produtoMapper;
    private ICategoriaGateway categoriaGateway;
    private CategoriaMapper categoriaMapper;

    private static final Long ID = 1L;

    @BeforeEach
    void setUp() {
        produtoUseCase = mock(IProdutoUseCase.class);
        produtoGateway = mock(IProdutoGateway.class);
        produtoMapper = mock(ProdutoMapper.class);
        categoriaGateway = mock(ICategoriaGateway.class);
        categoriaMapper = mock(CategoriaMapper.class);

        produtoController = new ProdutoController(
                produtoUseCase, produtoMapper, categoriaMapper
        );
    }

    @Test
    void deveBuscarProdutoComSucesso() {
        Produto produto = MockGenerator.generateProdutoMock();
        ProdutoResponse response = MockGenerator.generateProdutoResponseMock();

        when(produtoUseCase.buscarProduto(anyLong())).thenReturn(produto);
        when(produtoMapper.toResponse(produto)).thenReturn(response);

        ProdutoResponse resultado = produtoController.buscarProduto(ID);

        assertEquals(response, resultado);
        verify(produtoUseCase).buscarProduto(ID);
        verify(produtoMapper).toResponse(produto);
    }

    @Test
    void deveBuscarCategoriaComSucesso() {
        Categoria categoria = MockGenerator.generateCategoriaMock();
        CategoriaResponse response = MockGenerator.generateCategoriaResponseMock();

        when(produtoUseCase.buscarCategoria(anyLong())).thenReturn(categoria);
        //when(categoriaMapper.toResponse(categoria)).thenReturn(response);

        CategoriaResponse resultado = produtoController.buscarCategoria(ID);

        assertEquals(response, resultado);
        verify(produtoUseCase).buscarCategoria(ID);
        //verify(categoriaMapper).toResponse(categoria);
    }

    @Test
    void deveBuscarProdutosPorCategoria() {
        Produto produto = MockGenerator.generateProdutoMock();
        ProdutoResponse response = MockGenerator.generateProdutoResponseMock();

        when(produtoUseCase.buscarProdutosPorCategoria(anyLong()))
                .thenReturn(List.of(produto));
        when(produtoMapper.toResponse(any(Produto.class))).thenReturn(response);

        List<ProdutoResponse> resultado = produtoController.buscarProdutosPorCategoria(ID);

        assertEquals(ID, resultado.size());
        assertTrue(resultado.contains(response));

        verify(produtoUseCase).buscarProdutosPorCategoria(anyLong());
        verify(produtoMapper).toResponse(produto);
    }

    @Test
    void deveCadastrarProdutoComSucesso() {
        ProdutoRequest request = MockGenerator.generateProdutoRequestMock();
        Produto produto = MockGenerator.generateProdutoMock();
        ProdutoResponse response = MockGenerator.generateProdutoResponseMock();

        when(produtoUseCase.cadastrarProduto(any(Produto.class))).thenReturn(produto);
        when(produtoMapper.toResponse(any(Produto.class))).thenReturn(response);

        ProdutoResponse resultado = produtoController.cadastrarProduto(request);

        assertEquals(response, resultado);
        verify(produtoUseCase).cadastrarProduto(any(Produto.class));
        verify(produtoMapper).toResponse(produto);
    }

    @Test
    void deveRemoverProdutoComSucesso() {

        produtoController.removerProduto(ID);

        verify(produtoUseCase).removerProduto(ID);
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        ProdutoRequest request = MockGenerator.generateProdutoRequestMock();
        Produto produto = MockGenerator.generateProdutoMock();
        ProdutoResponse response = MockGenerator.generateProdutoResponseMock();

        when(produtoUseCase.atualizarProduto(anyLong(), any(Produto.class))).thenReturn(produto);
        when(produtoMapper.toResponse(any(Produto.class))).thenReturn(response);

        ProdutoResponse resultado = produtoController.atualizarProduto(request, ID);

        assertEquals(response, resultado);
        verify(produtoUseCase).atualizarProduto(anyLong(), any(Produto.class));
        verify(produtoMapper).toResponse(produto);
    }
}
