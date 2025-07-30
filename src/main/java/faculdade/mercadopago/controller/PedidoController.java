package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.PedidoMapper;
import faculdade.mercadopago.controller.mapper.dto.request.PedidoRequest;
import faculdade.mercadopago.controller.mapper.dto.response.PedidoResponse;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.usecase.IPedidoUseCase;

public class PedidoController {
    private final IPedidoUseCase pedidoUseCase;
    private final PedidoMapper pedidoMapper;
    private final IPedidoGateway pedidoGateway;
    private final ProdutoController produtoController;
    private final UsuarioController usuarioController;

    public PedidoController(IPedidoUseCase pedidoUseCase, IPedidoGateway pedidoGateway,ProdutoController produtoController, UsuarioController usuarioController, PedidoMapper pedidoMapper) {
        this.pedidoUseCase = pedidoUseCase;
        this.pedidoGateway = pedidoGateway;
        this.produtoController = produtoController;
        this.usuarioController = usuarioController;
        this.pedidoMapper = pedidoMapper;


    }

    public PedidoResponse buscarProduto(Long id) {
        return pedidoMapper.toResponse(pedidoUseCase.buscarPedido(id, pedidoGateway));
    }

    public PedidoResponse criarPedido(PedidoRequest pedidoRequest){
                return pedidoMapper.toResponse(pedidoUseCase.criarPedido(pedidoMapper.toEntity(pedidoRequest), pedidoGateway, produtoController, usuarioController));
    }
}