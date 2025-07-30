package faculdade.mercadopago.usecase;

import faculdade.mercadopago.controller.ProdutoController;
import faculdade.mercadopago.controller.UsuarioController;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.gateway.IPedidoGateway;

public interface IPedidoUseCase {

    Pedido buscarPedido(Long id, IPedidoGateway gateway);

    Pedido criarPedido(Pedido pedido, IPedidoGateway pedidoGateway, ProdutoController produtoController, UsuarioController usuarioController);

}
