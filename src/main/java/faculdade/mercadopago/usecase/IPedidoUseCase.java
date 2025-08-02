package faculdade.mercadopago.usecase;

import faculdade.mercadopago.entity.FilaPedidosPreparacao;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.gateway.IFilaPedidosPreparacaoGateway;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.gateway.IProdutoGateway;
import faculdade.mercadopago.gateway.IUsuarioGateway;

import java.util.List;

public interface IPedidoUseCase {

    Pedido buscarPedido(Long id, IPedidoGateway gateway);

    Pedido criarPedido(Pedido pedido, IPedidoGateway pedidoGateway, IProdutoGateway produtoGateway,
                       IUsuarioGateway usuarioGateway);

    List<Pedido> listarPedidos(StatusPedidoEnum status, IPedidoGateway pedidoGateway);

    Pedido alterarPedido(Long id, StatusPedidoEnum status, IPedidoGateway pedidoGateway);

    FilaPedidosPreparacao adicionarPedidoNaFila(Long id, IPedidoGateway pedidoGateway, IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway);

    void removerPedidoDaFila(Long id, IPedidoGateway pedidoGateway, IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway);
}
