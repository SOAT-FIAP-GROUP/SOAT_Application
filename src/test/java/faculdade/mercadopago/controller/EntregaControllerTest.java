package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.dto.request.EntregaRequest;
import faculdade.mercadopago.controller.mapper.dto.response.EntregaResponse;
import faculdade.mercadopago.entity.Entrega;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.usecase.IEntregaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EntregaControllerTest {

    private IEntregaUseCase entregaUseCase;
    private IEntregaGateway entregaGateway;
    private EntregaController entregaController;

    @BeforeEach
    public void setUp() {
        entregaUseCase = mock(IEntregaUseCase.class);
        entregaGateway = mock(IEntregaGateway.class);
        entregaController = new EntregaController(entregaUseCase, entregaGateway);
    }

    @Test
    public void deveRetornarEntregaResponseQuandoPedidoForEntregueComSucesso() {
        long codigo = 10;
        long pedidoId = 100;
        StatusPedidoEnum status = StatusPedidoEnum.FINALIZADO;
        LocalDateTime dataHoraSolicitacao = LocalDateTime.now();
        LocalDateTime dataHoraEntrega = LocalDateTime.now();

        EntregaRequest request = new EntregaRequest(codigo, pedidoId, status, dataHoraSolicitacao);

        Pedido pedido = mock(Pedido.class);

        Entrega entrega = new Entrega(codigo, pedido, dataHoraEntrega);

        when(entregaUseCase.entregarPedido(codigo, entregaGateway)).thenReturn(entrega);

        EntregaResponse response = entregaController.entregarPedido(request);

        assertNotNull(response);
        assertEquals(codigo, response.id());
        assertEquals(dataHoraEntrega, response.dataHoraEntrega());

        verify(entregaUseCase, times(1)).entregarPedido(codigo, entregaGateway);
    }
}
