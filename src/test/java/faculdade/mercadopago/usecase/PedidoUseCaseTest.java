package faculdade.mercadopago.usecase;

import faculdade.mercadopago.entity.FilaPedidosPreparacao;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.Produto;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.exception.EntityNotFoundException;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.mocks.MockGenerator;
import faculdade.mercadopago.usecase.impl.PedidoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PedidoUseCaseTest {
    private PedidoUseCase pedidoUseCase;
    private IPedidoGateway pedidoGateway;
    private IUsuarioUseCase usuarioUseCase;
    private IProdutoUseCase produtoUseCase;
    private IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase;

    private static final Long ID = 1L;

    @BeforeEach
    void setUp() {
        pedidoGateway = mock(IPedidoGateway.class);
        usuarioUseCase = mock(IUsuarioUseCase.class);
        produtoUseCase = mock(IProdutoUseCase.class);
        filaPedidosPreparacaoUseCase = mock(IFilaPedidosPreparacaoUseCase.class);

        pedidoUseCase = new PedidoUseCase(pedidoGateway, usuarioUseCase, produtoUseCase, filaPedidosPreparacaoUseCase);
    }

    @Test
    void deveBuscarPedidoComSucesso() {
        Pedido pedido = MockGenerator.generatePedidoMock();
        when(pedidoGateway.findById(1L)).thenReturn(Optional.of(pedido));

        Pedido result = pedidoUseCase.buscarPedido(1L);

        assertEquals(pedido, result);
    }

    @Test
    void deveLancarExcecaoQuandoPedidoNaoEncontrado() {
        when(pedidoGateway.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> pedidoUseCase.buscarPedido(999L));
    }

    @Test
    void deveCriarPedidoComSucesso() {
        Pedido pedido = MockGenerator.generatePedidoMockSemId();
        Pedido pedidoPreSalvar = pedido.preSalvar(pedido.idUsuario(), pedido.status(), pedido.dataHoraSolicitacao());
        Pedido pedidoSalvo = new Pedido(1L, pedido.idUsuario(), pedido.status(), null, pedido.dataHoraSolicitacao(), null, List.of());
        Produto produto = MockGenerator.generateProdutoMock();

        when(usuarioUseCase.buscaUsuarioPorId(pedido.idUsuario())).thenReturn(null);
        when(pedidoGateway.save(any())).thenReturn(pedidoSalvo);
        when(produtoUseCase.buscarProduto(any())).thenReturn(produto);

        Pedido result = pedidoUseCase.criarPedido(pedido);

        assertNotNull(result);
        assertEquals(pedidoSalvo.id(), result.id());
        verify(pedidoGateway, times(2)).save(any()); // uma para salvar inicial e outra para salvar final
    }

    @Test
    void deveLancarExcecaoAoCriarPedidoSemItens() {
        Pedido pedido = MockGenerator.generatePedidoMockComItensVazios();

        when(usuarioUseCase.buscaUsuarioPorId(pedido.idUsuario())).thenReturn(null);
        when(pedidoGateway.save(any())).thenReturn(pedido);

        assertThrows(ResponseStatusException.class, () -> pedidoUseCase.criarPedido(pedido));
    }

    @Test
    void deveListarPedidosPorStatus() {
        StatusPedidoEnum status = StatusPedidoEnum.RECEBIDO;
        Pedido pedido = MockGenerator.generatePedidoMock();
        when(pedidoGateway.findAllByStatus(status)).thenReturn(List.of(pedido));

        List<Pedido> result = pedidoUseCase.listarPedidos(status);

        assertEquals(1, result.size());
    }

    @Test
    void deveAlterarStatusDoPedido() {
        Pedido pedido = MockGenerator.generatePedidoMock();
        Pedido pedidoAtualizado = pedido.withStatus(StatusPedidoEnum.PRONTO);

        when(pedidoGateway.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoGateway.save(any())).thenReturn(pedidoAtualizado);

        Pedido result = pedidoUseCase.alterarPedido(1L, StatusPedidoEnum.PRONTO);

        assertEquals(StatusPedidoEnum.PRONTO, result.status());
    }

    @Test
    void deveAdicionarPedidoNaFila() {
        Pedido pedido = MockGenerator.generatePedidoMock();
        FilaPedidosPreparacao fila = new FilaPedidosPreparacao(null, pedido);

        when(pedidoGateway.findById(1L)).thenReturn(Optional.of(pedido));
        when(filaPedidosPreparacaoUseCase.salvar(any())).thenReturn(fila);

        FilaPedidosPreparacao result = pedidoUseCase.adicionarPedidoNaFila(1L);

        assertEquals(pedido, result.pedido());
    }

    @Test
    void deveRemoverPedidoDaFila() {
        FilaPedidosPreparacao fila = mock(FilaPedidosPreparacao.class);
        when(filaPedidosPreparacaoUseCase.findByPedidoPorId(1L)).thenReturn(fila);

        pedidoUseCase.removerPedidoDaFila(1L);

        verify(filaPedidosPreparacaoUseCase).removerPedidoDaFila(fila);
    }

    @Test
    void deveListarPedidosOrdenados() {
        Pedido pedido = MockGenerator.generatePedidoMock();
        when(pedidoGateway.findAllOrdenado()).thenReturn(List.of(pedido));

        List<Pedido> pedidos = pedidoUseCase.listaPedidosOrd();

        assertEquals(1, pedidos.size());
        assertEquals(pedido, pedidos.get(0));
    }

}
