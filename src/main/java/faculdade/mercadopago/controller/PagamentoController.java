package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.PagamentoMapper;
import faculdade.mercadopago.controller.mapper.dto.request.QrCodeRequest;
import faculdade.mercadopago.controller.mapper.dto.response.QrCodeResponse;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.usecase.IPagamentoUseCase;

public class PagamentoController {

    private final IPagamentoUseCase pagamentoUseCase;

    public PagamentoController(IPagamentoUseCase pagamentoUseCase) {
        this.pagamentoUseCase = pagamentoUseCase;
    }


    public QrCodeResponse gerarQrCode(QrCodeRequest request) {
        return PagamentoMapper.toResponse(pagamentoUseCase.processarQrCode(request));
    }
}