package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.PedidoMapper;
import faculdade.mercadopago.controller.mapper.dto.request.PedidoRequest;
import faculdade.mercadopago.controller.mapper.dto.response.PedidoResponse;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.usecase.IPedidoUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoController {
    private final IPedidoUseCase pedidoUseCase;
    private final PedidoMapper pedidoMapper;

    public PedidoController(IPedidoUseCase pedidoUseCase, PedidoMapper pedidoMapper) {
        this.pedidoUseCase = pedidoUseCase;
        this.pedidoMapper = pedidoMapper;
    }


    public PedidoResponse buscarPedido(Long id) {
        return pedidoMapper.toResponse(pedidoUseCase.buscarPedido(id));
    }

    public PedidoResponse criarPedido(PedidoRequest pedidoRequest){
                return pedidoMapper.toResponse(pedidoUseCase.criarPedido(pedidoMapper.toEntity(pedidoRequest)));
    }

    public List<PedidoResponse> listarPedidos(StatusPedidoEnum status) {
        return  pedidoUseCase.listarPedidos(status).stream().map(pedidoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PedidoResponse alterarPedido(Long codigo, StatusPedidoEnum status) {
        return pedidoMapper.toResponse(pedidoUseCase.alterarPedido(codigo, status));
    }

    public void removerPedidoDaFila(Long codigoPedido) {
        pedidoUseCase.removerPedidoDaFila(codigoPedido);
    }

    public List<PedidoResponse> listaPedidosOrd() {
        return pedidoUseCase.listaPedidosOrd().stream().map(pedidoMapper::toResponse)
                .collect(Collectors.toList());
    }
}