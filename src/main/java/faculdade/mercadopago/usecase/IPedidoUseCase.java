package faculdade.mercadopago.usecase;

import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.gateway.IPedidoGateway;

public interface IPedidoUseCase {

    Pedido buscarProduto(Long id, IPedidoGateway gateway);
}
