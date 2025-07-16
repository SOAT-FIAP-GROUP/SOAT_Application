package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.dto.request.EntregaRequest;
import faculdade.mercadopago.controller.mapper.dto.response.EntregaResponse;
import faculdade.mercadopago.entity.Entrega;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.usercase.IEntregaUseCase;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EntregaControllerTest {

    private IEntregaUseCase entregaUseCase;
    private IEntregaGateway entregaGateway;
    private EntregaController entregaController;

    @BeforeEach
    void setUp() {
        entregaUseCase = mock(IEntregaUseCase.class);
        entregaGateway = mock(IEntregaGateway.class);
        entregaController = new EntregaController(entregaUseCase, entregaGateway);
    }

//    @Test
//    public void deveRetornarEntregaResponseQuandoPedidoForEntregueComSucesso() {
//
//        Long codigo = 10L;
//        EntregaRequest request = new EntregaRequest(codigo);
//        Entrega entrega = new Entrega(codigo,);
//
//        when(entregaUseCase.entregarPedido(codigo, entregaGateway)).thenReturn(entrega);
//
//        EntregaResponse response = entregaController.entregarPedido(request);
//
//        assertNotNull(response);
//        assertEquals(codigo, response.id());
//
//        verify(entregaUseCase, times(1)).entregarPedido(codigo, entregaGateway);
//    }
}