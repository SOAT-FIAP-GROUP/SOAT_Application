package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.PedidoMapper;
import faculdade.mercadopago.controller.mapper.dto.response.PedidoResponse;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.usecase.IPedidoUseCase;

public class PedidoController {
    private final IPedidoUseCase pedidoUseCase;
    private final IPedidoGateway pedidoGateway;

    public PedidoController(IPedidoUseCase pedidoUseCase, IPedidoGateway pedidoGateway, PedidoMapper pedidoMapper) {
        this.pedidoUseCase = pedidoUseCase;
        this.pedidoGateway = pedidoGateway;
    }

    public PedidoResponse buscarProduto(Long id) {
        return PedidoMapper.toResponse(pedidoUseCase.buscarProduto(id, pedidoGateway));
    }

}