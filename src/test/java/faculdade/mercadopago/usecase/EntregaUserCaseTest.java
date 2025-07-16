package faculdade.mercadopago.usecase;

import faculdade.mercadopago.entity.Entrega;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.usercase.impl.EntregaUserCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EntregaUserCaseTest {

    private EntregaUserCase entregaUserCase;
    private IEntregaGateway entregaGateway;

    @BeforeEach
    void setUp() {
        entregaUserCase = new EntregaUserCase();
        entregaGateway = mock(IEntregaGateway.class);
    }

//    @Test
//    void deveRetornarEntregaQuandoIdExistir() {
//
//        Long id = 1L;
//        Entrega entregaEsperada = new Entrega();
//        when(entregaGateway.findById(id)).thenReturn(Optional.of(entregaEsperada));
//
//        Entrega entregaRetornada = entregaUserCase.entregarPedido(id, entregaGateway);
//
//        assertNotNull(entregaRetornada);
//        assertEquals(entregaEsperada, entregaRetornada);
//        verify(entregaGateway, times(1)).findById(id);
//    }
//
//    @Test
//    void deveLancarExcecaoQuandoEntregaNaoForEncontrada() {
//
//        Long id = 99L;
//        when(entregaGateway.findById(id)).thenReturn(Optional.empty());
//
//        assertThrows(RuntimeException.class, () -> {
//            entregaUserCase.entregarPedido(id, entregaGateway);
//        });
//
//        verify(entregaGateway, times(1)).findById(id);
//    }
}
