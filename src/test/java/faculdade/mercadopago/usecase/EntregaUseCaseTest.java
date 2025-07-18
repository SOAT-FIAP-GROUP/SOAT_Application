package faculdade.mercadopago.usecase;

import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.usecase.impl.EntregaUseCase;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

public class EntregaUseCaseTest {

    private EntregaUseCase entregaUserCase;
    private IEntregaGateway entregaGateway;

    @BeforeEach
    void setUp() {
        entregaUserCase = new EntregaUseCase();
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
