package faculdade.mercadopago.usecase;

import faculdade.mercadopago.entity.Entrega;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.usecase.impl.EntregaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EntregaUseCaseTest {

    private EntregaUseCase entregaUserCase;
    private IEntregaGateway entregaGateway;

    @BeforeEach
    public void setUp() {
        entregaUserCase = new EntregaUseCase();
        entregaGateway = mock(IEntregaGateway.class);
    }

    @Test
    public void deveRetornarEntregaQuandoIdExistir() {
        Long id = 1L;
        Entrega entregaEsperada = mock(Entrega.class);

        when(entregaGateway.findById(id)).thenReturn(Optional.of(entregaEsperada));

        Entrega entregaRetornada = entregaUserCase.entregarPedido(id, entregaGateway);

        assertNotNull(entregaRetornada);
        assertEquals(entregaEsperada, entregaRetornada);
        verify(entregaGateway, times(1)).findById(id);
    }


    @Test
    public void deveLancarExcecaoQuandoEntregaNaoForEncontrada() {
        Long id = 99L;
        when(entregaGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            entregaUserCase.entregarPedido(id, entregaGateway);
        });

        verify(entregaGateway, times(1)).findById(id);
    }
}
