package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.EntregaMapper;
import faculdade.mercadopago.controller.mapper.dto.request.EntregaRequest;
import faculdade.mercadopago.controller.mapper.dto.response.EntregaResponse;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.usecase.IEntregaUseCase;

public class EntregaController {

    private final IEntregaUseCase entregaUseCase;
    private final IEntregaGateway entregaGateway;

    public EntregaController(IEntregaUseCase entregaUseCase, IEntregaGateway entregaGateway) {
        this.entregaUseCase = entregaUseCase;
        this.entregaGateway = entregaGateway;
    }

    public EntregaResponse entregarPedido(EntregaRequest entregaRequest) {
        return EntregaMapper.toResponse(entregaUseCase.entregarPedido(entregaRequest.codigo(), entregaGateway));
    }
}