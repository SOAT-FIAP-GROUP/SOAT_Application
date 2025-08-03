package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.EntregaMapper;
import faculdade.mercadopago.controller.mapper.dto.request.EntregaRequest;
import faculdade.mercadopago.controller.mapper.dto.response.EntregaResponse;
import faculdade.mercadopago.usecase.IEntregaUseCase;

public class EntregaController {

    private final IEntregaUseCase entregaUseCase;

    public EntregaController(IEntregaUseCase entregaUseCase) {
        this.entregaUseCase = entregaUseCase;
    }

    public EntregaResponse entregarPedido(EntregaRequest entregaRequest) {
        return EntregaMapper.toResponse(entregaUseCase.entregarPedido(EntregaMapper.toEntity(entregaRequest)));
    }
}