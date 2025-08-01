package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.EntregaMapper;
import faculdade.mercadopago.controller.mapper.dto.request.EntregaRequest;
import faculdade.mercadopago.controller.mapper.dto.response.EntregaResponse;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.gateway.IFilaPedidosPreparacaoGateway;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.usecase.IEntregaUseCase;
import faculdade.mercadopago.usecase.IPedidoUseCase;

public class EntregaController {

    private final IEntregaUseCase entregaUseCase;
    private final IEntregaGateway entregaGateway;
    private final IPedidoGateway pedidoGateway;
    private final IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway;

    public EntregaController(IEntregaUseCase entregaUseCase, IEntregaGateway entregaGateway, IPedidoGateway pedidoGateway, IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway) {
        this.entregaUseCase = entregaUseCase;
        this.entregaGateway = entregaGateway;
        this.pedidoGateway = pedidoGateway;
        this.filaPedidosPreparacaoGateway = filaPedidosPreparacaoGateway;
    }

    public EntregaResponse entregarPedido(EntregaRequest entregaRequest) {
        return EntregaMapper.toResponse(entregaUseCase.entregarPedido(EntregaMapper.toEntity(entregaRequest), entregaGateway, pedidoGateway, filaPedidosPreparacaoGateway));
    }
}