package faculdade.mercadopago.usecase;

import faculdade.mercadopago.entity.FilaPedidosPreparacao;

public interface IFilaPedidosPreparacaoUseCase {
    FilaPedidosPreparacao salvar(FilaPedidosPreparacao filaPedidosPreparacao);

    FilaPedidosPreparacao findByPedidoPorId(Long id);

    void removerPedidoDaFila(FilaPedidosPreparacao filaPedidosPreparacao);
}
