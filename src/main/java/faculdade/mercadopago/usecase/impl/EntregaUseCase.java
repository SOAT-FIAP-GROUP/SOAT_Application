package faculdade.mercadopago.usecase.impl;

import faculdade.mercadopago.entity.Entrega;
import faculdade.mercadopago.entity.FilaPedidosPreparacao;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.usecase.IEntregaUseCase;
import faculdade.mercadopago.usecase.IFilaPedidosPreparacaoUseCase;
import faculdade.mercadopago.usecase.IPedidoUseCase;


public class EntregaUseCase implements IEntregaUseCase {

    private final IPedidoUseCase pedidoUseCase;
    private final IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase;
    private final IEntregaGateway entregaGateway;

    public EntregaUseCase(IPedidoUseCase pedidoUseCase, IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase, IEntregaGateway entregaGateway) {
        this.pedidoUseCase = pedidoUseCase;
        this.filaPedidosPreparacaoUseCase = filaPedidosPreparacaoUseCase;
        this.entregaGateway = entregaGateway;
    }

    @Override
    public Entrega entregarPedido(Entrega entrega) {

        Pedido pedido = pedidoUseCase.buscarPedido(entrega.pedido().id());

        Entrega entregaNovo = new Entrega(null, pedido.withStatus(StatusPedidoEnum.FINALIZADO), entrega.dataHoraEntrega());

        Entrega entregaSalva = entregaGateway.save(entregaNovo);

        FilaPedidosPreparacao fila = filaPedidosPreparacaoUseCase.findByPedidoPorId(pedido.id());

        filaPedidosPreparacaoUseCase.removerPedidoDaFila(fila);
        return entregaSalva;
    }
}
