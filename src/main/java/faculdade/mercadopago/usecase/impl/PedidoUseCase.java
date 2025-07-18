package faculdade.mercadopago.usecase.impl;

import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.usecase.IPedidoUseCase;

public class PedidoUseCase implements IPedidoUseCase {
    @Override
    public Pedido buscarProduto(Long id, IPedidoGateway gateway) {
        return null;
    }
}
