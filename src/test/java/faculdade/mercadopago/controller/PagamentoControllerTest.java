package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.dto.request.QrCodeRequest;
import faculdade.mercadopago.controller.mapper.dto.response.PagamentoStatusResponse;
import faculdade.mercadopago.controller.mapper.dto.response.QrCodeResponse;
import faculdade.mercadopago.entity.pagamento.ConfirmacaoPagamentoRes;
import faculdade.mercadopago.entity.pagamento.QrCodeRes;
import faculdade.mercadopago.mocks.MockGenerator;
import faculdade.mercadopago.usecase.IPagamentoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PagamentoControllerTest {

    private PagamentoController pagamentoController;
    private IPagamentoUseCase pagamentoUseCase;

    @BeforeEach
    void setUp() {
        pagamentoUseCase = mock(IPagamentoUseCase.class);
        pagamentoController = new PagamentoController(pagamentoUseCase); // <-- Isso estava faltando
    }


    @Test
    void deveGerarQrCodeComSucesso() {
        QrCodeRequest request = MockGenerator.generateQrCodeRequestMock();
        QrCodeRes qrcodeResMock = MockGenerator.generateQrCodeResMock();

        when(pagamentoUseCase.processarQrCode(any(QrCodeRequest.class))).thenReturn(qrcodeResMock);

        QrCodeResponse response = pagamentoController.gerarQrCode(request);

        assertNotNull(response);
        assertEquals(qrcodeResMock.qr_data(), response.qr_data());
        assertEquals(qrcodeResMock.in_store_order_id(), response.in_store_order_id());

        verify(pagamentoUseCase, times(1)).processarQrCode(request);
    }

    @Test
    void deveConsultarPagamentoComSucesso() {
//        // Arrange
//        String pagamentoId = "12345";
//        ConfirmacaoPagamentoRes mockBody = MockGenerator.ConfirmacaoPagamentoResMock();
//
//        when(pagamentoUseCase.consultarPagamento(anyString()))
//                .thenReturn(mockBody); // sem ResponseEntity
//
//
//        // Act
//        PagamentoStatusResponse response = pagamentoController.consultar(pagamentoId);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(mockBody.status(), response.status());
//        assertEquals(mockBody.external_reference(), response.pedidoId());
//
//        verify(pagamentoUseCase, times(1)).consultarPagamento(pagamentoId);
    }
}
