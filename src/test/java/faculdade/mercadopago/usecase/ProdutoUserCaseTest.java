package faculdade.mercadopago.usecase;

import faculdade.mercadopago.entity.Categoria;
import faculdade.mercadopago.entity.Produto;
import faculdade.mercadopago.exception.EntityNotFoundException;
import faculdade.mercadopago.gateway.ICategoriaGateway;
import faculdade.mercadopago.gateway.IProdutoGateway;
import faculdade.mercadopago.mocks.MockGenerator;
import faculdade.mercadopago.usercase.impl.ProdutoUserCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoUserCaseTest {

    private ProdutoUserCase produtoUserCase;
    private IProdutoGateway produtoGateway;
    private ICategoriaGateway categoriaGateway;

    private static final Long ID = 1L;

    @BeforeEach
    void setUp() {
        produtoUserCase = new ProdutoUserCase();
        produtoGateway = mock(IProdutoGateway.class);
        categoriaGateway = mock(ICategoriaGateway.class);
    }

    @Test
    void deveCadastrarProdutoComSucesso() {
        Produto produto = MockGenerator.generateProdutoMock();
        when(produtoGateway.save(any(Produto.class))).thenReturn(produto);

        Produto resultado = produtoUserCase.cadastrarProduto(produto, produtoGateway);

        assertNotNull(resultado);
        verify(produtoGateway, times(1)).save(produto);
    }

    @Test
    void deveBuscarProdutoQuandoIdExistir() {
        Produto produto = MockGenerator.generateProdutoMock();
        when(produtoGateway.findById(anyLong())).thenReturn(Optional.of(produto));

        Produto resultado = produtoUserCase.buscarProduto(ID, produtoGateway);

        assertNotNull(resultado);
        verify(produtoGateway).findById(ID);
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoEncontrado() {
        Long id = 99L;
        when(produtoGateway.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                produtoUserCase.buscarProduto(ID, produtoGateway));
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        Produto produto = MockGenerator.generateProdutoMock();
        when(produtoGateway.findById(anyLong())).thenReturn(Optional.of(produto));
        when(produtoGateway.save(any(Produto.class))).thenReturn(produto);

        Produto resultado = produtoUserCase.atualizarProduto(ID, produto, produtoGateway);

        assertNotNull(resultado);
        verify(produtoGateway).findById(ID);
        verify(produtoGateway).save(produto);
    }

    @Test
    void deveRemoverProdutoComSucesso() {
        Produto produto = MockGenerator.generateProdutoMock();
        when(produtoGateway.findById(anyLong())).thenReturn(Optional.of(produto));

        produtoUserCase.removerProduto(ID, produtoGateway);

        verify(produtoGateway).findById(ID);
        verify(produtoGateway).removerProduto(produto);
    }

    @Test
    void deveBuscarProdutosPorCategoriaComSucesso() {
        List<Produto> produtos = List.of(MockGenerator.generateProdutoMock());
        when(produtoGateway.findByCategoriaCodigo(anyLong())).thenReturn(produtos);

        List<Produto> resultado = produtoUserCase.buscarProdutosPorCategoria(ID, produtoGateway);

        assertEquals(ID, resultado.size());
        verify(produtoGateway).findByCategoriaCodigo(ID);
    }

    @Test
    void deveBuscarCategoriaQuandoIdExistir() {
        Categoria categoria = MockGenerator.generateCategoriaMock();
        when(categoriaGateway.findById(anyLong())).thenReturn(Optional.of(categoria));

        Categoria resultado = produtoUserCase.buscarCategoria(ID, categoriaGateway);

        assertNotNull(resultado);
        verify(categoriaGateway).findById(ID);
    }

    @Test
    void deveLancarExcecaoQuandoCategoriaNaoForEncontrada() {
        when(categoriaGateway.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                produtoUserCase.buscarCategoria(ID, categoriaGateway));
    }
}

