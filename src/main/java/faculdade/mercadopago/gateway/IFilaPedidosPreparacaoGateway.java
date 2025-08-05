package faculdade.mercadopago.gateway;

import faculdade.mercadopago.entity.FilaPedidosPreparacao;

import java.util.Optional;

public interface IFilaPedidosPreparacaoGateway {

    FilaPedidosPreparacao save(FilaPedidosPreparacao entity);

    Optional<FilaPedidosPreparacao> findById(Long id);

    Optional<FilaPedidosPreparacao> findByPedidocodigoId(Long id);

    void removerPedidoDaFila(FilaPedidosPreparacao entity);

}
