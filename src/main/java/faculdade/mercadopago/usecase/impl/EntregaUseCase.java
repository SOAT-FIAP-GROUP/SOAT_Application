package faculdade.mercadopago.usecase.impl;

import faculdade.mercadopago.entity.Entrega;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.usecase.IEntregaUseCase;


public class EntregaUseCase implements IEntregaUseCase {

    @Override
    public Entrega entregarPedido(Long id, IEntregaGateway gateway) {
        return gateway.findById(id).orElseThrow();
    }
}
