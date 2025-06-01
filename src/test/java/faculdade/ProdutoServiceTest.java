package faculdade;

import faculdade.mercadopago.adapter.driven.entity.CategoriaEntity;
import faculdade.mercadopago.adapter.driven.entity.ProdutoEntity;
import faculdade.mercadopago.adapter.driven.repository.CategoriaRepository;
import faculdade.mercadopago.adapter.driven.repository.ProdutoRepository;
import faculdade.mercadopago.core.domain.dto.NewProdutoDto;
import faculdade.mercadopago.core.domain.dto.ViewProdutoDto;
import faculdade.mercadopago.core.domain.mapper.ProdutoMapper;
import faculdade.mercadopago.core.services.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private ProdutoMapper produtoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastrarProduto_DeveRetornarApiResponseComSucesso() {
        NewProdutoDto dto = new NewProdutoDto();
        ProdutoEntity entity = new ProdutoEntity();
        ProdutoEntity savedEntity = new ProdutoEntity();
        ViewProdutoDto viewDto = ViewProdutoDto.builder()
                .codigo(1)
                .nome("Produto 1")
                .descricao("Teste Produto 1")
                .categoria(1)
                .preco(BigDecimal.valueOf(12.00))
                .tempopreparo(new Time(300000))
                .build();

        when(produtoMapper.newDtoToEntity(dto)).thenReturn(entity);
        when(produtoRepository.save(entity)).thenReturn(savedEntity);
        when(produtoMapper.entityToDto(savedEntity)).thenReturn(viewDto);

        var response = produtoService.cadastrarProduto(dto);

        assertTrue(response.isSuccess());
        assertEquals(viewDto, response.getData());
    }

    @Test
    void buscarProduto_DeveRetornarProduto() throws Exception {
        long codigo = 1;
        ProdutoEntity entity = new ProdutoEntity();
        entity.setCodigo(codigo);
        entity.setNome("Produto Teste");
        entity.setPreco(BigDecimal.valueOf(15.00));
        entity.setDescricao("Descrição");
        entity.setTempopreparo(new Time(300000));
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setCodigo(2);
        entity.setCategoria(categoria);

        when(produtoRepository.findById(codigo)).thenReturn(Optional.of(entity));

        var response = produtoService.buscarProduto(codigo);

        assertTrue(response.isSuccess());
        assertEquals("Produto Teste", response.getData().getNome());
    }

    @Test
    void removerProduto_DeveRemoverComSucesso() throws Exception {
        long codigo = 1;
        ProdutoEntity entity = new ProdutoEntity();
        when(produtoRepository.findById(codigo)).thenReturn(Optional.of(entity));

        produtoService.removerProduto(codigo);

        verify(produtoRepository, times(1)).delete(entity);
    }

    @Test
    void atualizarProduto_DeveAtualizarComSucesso() throws Exception {
        long codigo = 1;
        ProdutoEntity entity = new ProdutoEntity();
        ProdutoEntity updated = new ProdutoEntity();
        ViewProdutoDto dto = ViewProdutoDto.builder()
                .codigo(1)
                .nome("Produto 1")
                .descricao("Teste Produto 1")
                .categoria(1)
                .preco(BigDecimal.valueOf(12.00))
                .tempopreparo(new Time(300000))
                .build();

        when(produtoRepository.findById(codigo)).thenReturn(Optional.of(entity));
        doNothing().when(produtoMapper).updateFromDto(dto, entity);
        when(produtoRepository.save(entity)).thenReturn(updated);
        ViewProdutoDto viewDto = ViewProdutoDto.builder()
                .codigo(1)
                .nome("Produto 1")
                .descricao("Atualizando Produto 1")
                .categoria(1)
                .preco(BigDecimal.valueOf(10.00))
                .tempopreparo(new Time(100000))
                .build();
        when(produtoMapper.entityToDto(updated)).thenReturn(viewDto);

        var response = produtoService.atualizarProduto(dto, codigo);

        assertTrue(response.isSuccess());
        assertEquals(viewDto, response.getData());
    }

    @Test
    void buscarCategoria_DeveRetornarCategoria() {
        long codigo = 1;
        CategoriaEntity entity = new CategoriaEntity();
        entity.setCodigo(codigo);
        entity.setNome("Lanches");

        when(categoriaRepository.findById(codigo)).thenReturn(Optional.of(entity));

        var response = produtoService.buscarCategoria(codigo);

        assertTrue(response.isSuccess());
        assertEquals("Lanches", response.getData().getNome());
    }

    @Test
    void buscarProdutosPorCategoria_DeveRetornarLista() {
        long categoriaCodigo = 1;
        ProdutoEntity entity = new ProdutoEntity();
        ViewProdutoDto dto = ViewProdutoDto.builder()
                .codigo(1)
                .nome("Produto 1")
                .descricao("Teste Produto 1")
                .categoria(1)
                .preco(BigDecimal.valueOf(12.00))
                .tempopreparo(new Time(300000))
                .build();

        when(produtoRepository.findByCategoriaCodigo(categoriaCodigo)).thenReturn(List.of(entity));
        when(produtoMapper.entityToDto(entity)).thenReturn(dto);

        var response = produtoService.buscarProdutosPorCategoria(categoriaCodigo);

        assertTrue(response.isSuccess());
        assertEquals(1, response.getData().size());
    }
}
