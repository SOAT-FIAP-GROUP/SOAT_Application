package faculdade.mercadopago.usercase;

import faculdade.mercadopago.entity.Entrega;
import faculdade.mercadopago.gateway.IEntregaGateway;

public interface IEntregaUseCase {

    Entrega entregarPedido(long id, IEntregaGateway gateway);
}
