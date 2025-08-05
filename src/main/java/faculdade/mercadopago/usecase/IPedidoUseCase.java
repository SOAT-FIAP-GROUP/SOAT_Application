package faculdade.mercadopago.usecase;

import faculdade.mercadopago.entity.FilaPedidosPreparacao;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;

import java.util.List;

public interface IPedidoUseCase {

    Pedido buscarPedido(Long id);

    Pedido criarPedido(Pedido pedido);

    List<Pedido> listarPedidos(StatusPedidoEnum status);

    Pedido alterarPedido(Long id, StatusPedidoEnum status);

    FilaPedidosPreparacao adicionarPedidoNaFila(Long id);

    void removerPedidoDaFila(Long id);

    List<Pedido> listaPedidosOrd();
}
