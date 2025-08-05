package faculdade.mercadopago.gateway;

import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;

import java.util.List;
import java.util.Optional;

public interface IPedidoGateway {
    Optional<Pedido> findById(Long id);

    Pedido save (Pedido entity);

    List<Pedido> findAllByStatus(StatusPedidoEnum status);

    List<Pedido> findAllOrdenado();
}
