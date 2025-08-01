package faculdade.mercadopago.usecase;

import faculdade.mercadopago.entity.Entrega;
import faculdade.mercadopago.entity.FilaPedidosPreparacao;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.gateway.IFilaPedidosPreparacaoGateway;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.mocks.MockGenerator;
import faculdade.mercadopago.usecase.impl.EntregaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EntregaUseCaseTest {

    private IEntregaGateway entregaGateway;
    private IPedidoGateway pedidoGateway;
    private IFilaPedidosPreparacaoGateway filaGateway;
    private EntregaUseCase entregaUseCase;

    @BeforeEach
    public void setup() {
        entregaGateway = mock(IEntregaGateway.class);
        pedidoGateway = mock(IPedidoGateway.class);
        filaGateway = mock(IFilaPedidosPreparacaoGateway.class);
        entregaUseCase = new EntregaUseCase();
    }

    @Test
    public void deveEntregarPedidoComSucesso() {
        Pedido pedido = MockGenerator.generatePedidoMock();

        Entrega entrega = MockGenerator.generateEntregaMock();

        Pedido pedidoFinalizado = pedido.withStatus(StatusPedidoEnum.FINALIZADO);

        Entrega entregaSalva = new Entrega(10L, pedidoFinalizado, entrega.dataHoraEntrega());

        FilaPedidosPreparacao fila = MockGenerator.generateFilaPedidosPreparacaoMock();

        when(pedidoGateway.findById(anyLong())).thenReturn(Optional.of(pedido));
        when(entregaGateway.save(any())).thenReturn(entregaSalva);
        when(filaGateway.findByPedidocodigoId(anyLong())).thenReturn(Optional.of(fila));

        Entrega resultado = entregaUseCase.entregarPedido(entrega, entregaGateway, pedidoGateway, filaGateway);

        assertNotNull(resultado);
        assertEquals(10L, resultado.id());
        assertEquals(StatusPedidoEnum.FINALIZADO, resultado.pedido().status());
        verify(entregaGateway, times(1)).save(any());
        verify(filaGateway, times(1)).removerPedidoDaFila(fila);
    }
}
