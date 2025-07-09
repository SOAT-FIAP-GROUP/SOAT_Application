package faculdade.mercadopago.usercase.impl;

import faculdade.mercadopago.entity.Entrega;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.usercase.IEntregaUseCase;

public class EntregaUserCase implements IEntregaUseCase {

    @Override
    public Entrega entregarPedido(Long id, IEntregaGateway gateway) {
        return gateway.findById(id).orElseThrow();
    }
}
