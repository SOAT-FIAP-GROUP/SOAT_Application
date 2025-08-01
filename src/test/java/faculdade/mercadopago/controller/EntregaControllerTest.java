package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.EntregaMapper;
import faculdade.mercadopago.controller.mapper.dto.request.EntregaRequest;
import faculdade.mercadopago.controller.mapper.dto.response.EntregaResponse;
import faculdade.mercadopago.entity.Entrega;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.gateway.IFilaPedidosPreparacaoGateway;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.usecase.IEntregaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EntregaControllerTest {

    private IEntregaUseCase entregaUseCase;
    private IEntregaGateway entregaGateway;
    private IPedidoGateway pedidoGateway;
    private IFilaPedidosPreparacaoGateway filaGateway;

    private EntregaController entregaController;

    @BeforeEach
    public void setUp() {
        entregaUseCase = mock(IEntregaUseCase.class);
        entregaGateway = mock(IEntregaGateway.class);
        pedidoGateway = mock(IPedidoGateway.class);
        filaGateway = mock(IFilaPedidosPreparacaoGateway.class);

        entregaController = new EntregaController(entregaUseCase, entregaGateway, pedidoGateway, filaGateway);
    }

    @Test
    public void deveRetornarEntregaResponseQuandoPedidoForEntregueComSucesso() {
        Long entregaId = 1L;
        LocalDateTime dataEntrega = LocalDateTime.now();

        EntregaRequest entregaRequest = mock(EntregaRequest.class);
        Entrega entrega = mock(Entrega.class);
        Entrega entregaSalva = mock(Entrega.class);
        EntregaResponse entregaResponse = mock(EntregaResponse.class);

        try (MockedStatic<EntregaMapper> mockedMapper = mockStatic(EntregaMapper.class)) {
            mockedMapper.when(() -> EntregaMapper.toEntity(entregaRequest)).thenReturn(entrega);
            mockedMapper.when(() -> EntregaMapper.toResponse(entregaSalva)).thenReturn(entregaResponse);

            when(entregaUseCase.entregarPedido(entrega, entregaGateway, pedidoGateway, filaGateway))
                    .thenReturn(entregaSalva);

            EntregaResponse resultado = entregaController.entregarPedido(entregaRequest);

            assertNotNull(resultado);
            assertEquals(entregaResponse, resultado);
            verify(entregaUseCase, times(1)).entregarPedido(entrega, entregaGateway, pedidoGateway, filaGateway);
        }
    }
}
