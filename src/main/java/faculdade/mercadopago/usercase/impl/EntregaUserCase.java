package faculdade.mercadopago.usercase.impl;

import faculdade.mercadopago.entity.Entrega;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.usercase.IEntregaUseCase;
import org.springframework.stereotype.Service;

@Service
public class EntregaUserCase implements IEntregaUseCase {

    @Override
    public Entrega entregarPedido(long id, IEntregaGateway gateway) {
        return null;
    }
}
