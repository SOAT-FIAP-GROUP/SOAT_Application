package faculdade.mercadopago.usecase;

import faculdade.mercadopago.entity.Entrega;
import faculdade.mercadopago.gateway.IEntregaGateway;

public interface IEntregaUseCase {

    Entrega entregarPedido(Long id, IEntregaGateway gateway);
}
