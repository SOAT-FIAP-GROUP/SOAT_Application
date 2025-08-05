package faculdade.mercadopago.usecase;

import faculdade.mercadopago.entity.Entrega;
import faculdade.mercadopago.entity.FilaPedidosPreparacao;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.mocks.MockGenerator;
import faculdade.mercadopago.usecase.impl.EntregaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EntregaUseCaseTest {

    private IEntregaGateway entregaGateway;
    private IPedidoUseCase pedidoUseCase;
    private IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase;
    private EntregaUseCase entregaUseCase;

    @BeforeEach
    public void setup() {
        entregaGateway = mock(IEntregaGateway.class);
        pedidoUseCase = mock(IPedidoUseCase.class);
        filaPedidosPreparacaoUseCase = mock(IFilaPedidosPreparacaoUseCase.class);
        entregaUseCase = new EntregaUseCase(pedidoUseCase, filaPedidosPreparacaoUseCase, entregaGateway);
    }

    @Test
    public void deveEntregarPedidoComSucesso() {
        Pedido pedido = MockGenerator.generatePedidoMock();

        Entrega entrega = MockGenerator.generateEntregaMock();

        Pedido pedidoFinalizado = pedido.withStatus(StatusPedidoEnum.FINALIZADO);

        Entrega entregaSalva = new Entrega(10L, pedidoFinalizado, entrega.dataHoraEntrega());

        FilaPedidosPreparacao fila = MockGenerator.generateFilaPedidosPreparacaoMock();

        when(pedidoUseCase.buscarPedido(anyLong())).thenReturn(pedido);
        when(entregaGateway.save(any())).thenReturn(entregaSalva);
        when(filaPedidosPreparacaoUseCase.findByPedidoPorId(anyLong())).thenReturn(fila);

        Entrega resultado = entregaUseCase.entregarPedido(entrega);

        assertNotNull(resultado);
        assertEquals(10L, resultado.id());
        assertEquals(StatusPedidoEnum.FINALIZADO, resultado.pedido().status());
        verify(entregaGateway, times(1)).save(any());
        verify(filaPedidosPreparacaoUseCase, times(1)).removerPedidoDaFila(fila);
    }
}
