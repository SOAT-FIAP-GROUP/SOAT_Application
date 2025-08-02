package faculdade.mercadopago.usecase.impl;

import faculdade.mercadopago.entity.Entrega;
import faculdade.mercadopago.entity.FilaPedidosPreparacao;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.exception.EntityNotFoundException;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.gateway.IFilaPedidosPreparacaoGateway;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.usecase.IEntregaUseCase;


public class EntregaUseCase implements IEntregaUseCase {

    @Override
    public Entrega entregarPedido(Entrega entrega, IEntregaGateway entregaGateway, IPedidoGateway pedidoGateway, IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway) {

        Pedido pedido = pedidoGateway.findById(entrega.pedido().id()).orElseThrow(
                () -> new EntityNotFoundException(Pedido.class, entrega.id())
        );

        Entrega entregaNovo = new Entrega(null, pedido.withStatus(StatusPedidoEnum.FINALIZADO), entrega.dataHoraEntrega());

        Entrega entregaSalva = entregaGateway.save(entregaNovo);

        FilaPedidosPreparacao fila = filaPedidosPreparacaoGateway.findByPedidocodigoId(pedido.id()).orElseThrow(
                () -> new EntityNotFoundException(FilaPedidosPreparacao.class, pedido.id())
        );

        filaPedidosPreparacaoGateway.removerPedidoDaFila(fila);
        return entregaSalva;
    }
}
