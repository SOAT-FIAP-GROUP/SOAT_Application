package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.EntregaMapper;
import faculdade.mercadopago.controller.mapper.dto.request.EntregaRequest;
import faculdade.mercadopago.controller.mapper.dto.response.EntregaResponse;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.usercase.IEntregaUseCase;

public class EntregaController {

    private final IEntregaUseCase entregaUseCase;
    private final IEntregaGateway entregaGateway;
    private final EntregaMapper entregaMapper;

    public EntregaController(IEntregaUseCase entregaUseCase, IEntregaGateway entregaGateway, EntregaMapper entregaMapper) {
        this.entregaUseCase = entregaUseCase;
        this.entregaGateway = entregaGateway;
        this.entregaMapper = entregaMapper;
    }

    public EntregaResponse entregarPedido(EntregaRequest entregaRequest) {
        return entregaMapper.toResponse(entregaUseCase.entregarPedido(entregaRequest.codigo(), entregaGateway));
    }


}