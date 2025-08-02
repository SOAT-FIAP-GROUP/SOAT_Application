package faculdade.mercadopago.usecase;

import faculdade.mercadopago.entity.Entrega;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.gateway.IFilaPedidosPreparacaoGateway;
import faculdade.mercadopago.gateway.IPedidoGateway;

public interface IEntregaUseCase {

    Entrega entregarPedido(Entrega entrega, IEntregaGateway gateway, IPedidoGateway pedidoGateway, IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway);
}
