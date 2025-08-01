package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.PedidoMapper;
import faculdade.mercadopago.controller.mapper.dto.request.PedidoRequest;
import faculdade.mercadopago.controller.mapper.dto.response.PedidoResponse;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.gateway.IFilaPedidosPreparacaoGateway;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.gateway.IProdutoGateway;
import faculdade.mercadopago.gateway.IUsuarioGateway;
import faculdade.mercadopago.usecase.IPedidoUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoController {
    private final IPedidoUseCase pedidoUseCase;
    private final PedidoMapper pedidoMapper;
    private final IPedidoGateway pedidoGateway;
    private final IProdutoGateway produtoGateway;
    private final IUsuarioGateway usuarioGateway;
    private final IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway;

    public PedidoController(IPedidoUseCase pedidoUseCase, PedidoMapper pedidoMapper, IPedidoGateway pedidoGateway, IProdutoGateway produtoGateway, IUsuarioGateway usuarioGateway, IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway) {
        this.pedidoUseCase = pedidoUseCase;
        this.pedidoMapper = pedidoMapper;
        this.pedidoGateway = pedidoGateway;
        this.produtoGateway = produtoGateway;
        this.usuarioGateway = usuarioGateway;
        this.filaPedidosPreparacaoGateway = filaPedidosPreparacaoGateway;
    }


    public PedidoResponse buscarPedido(Long id) {
        return pedidoMapper.toResponse(pedidoUseCase.buscarPedido(id, pedidoGateway));
    }

    public PedidoResponse criarPedido(PedidoRequest pedidoRequest){
                return pedidoMapper.toResponse(pedidoUseCase.criarPedido(pedidoMapper.toEntity(pedidoRequest), pedidoGateway, produtoGateway, usuarioGateway));
    }

    public List<PedidoResponse> listarPedidos(StatusPedidoEnum status) {
        return  pedidoUseCase.listarPedidos(status, pedidoGateway).stream().map(pedidoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PedidoResponse alterarPedido(Long codigo, StatusPedidoEnum status) {
        return pedidoMapper.toResponse(pedidoUseCase.alterarPedido(codigo, status, pedidoGateway));
    }

    public void removerPedidoDaFila(Long codigoPedido) {
        pedidoUseCase.removerPedidoDaFila(codigoPedido, pedidoGateway, filaPedidosPreparacaoGateway);
    }
}