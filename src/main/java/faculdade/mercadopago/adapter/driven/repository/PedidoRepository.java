package faculdade.mercadopago.adapter.driven.repository;

import faculdade.mercadopago.adapter.driven.entity.PedidoEntity;
import faculdade.mercadopago.core.domain.enums.StatusPedidoEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PedidoRepository extends JpaRepository<PedidoEntity,Long> {
    List<PedidoEntity> findAllByStatus(StatusPedidoEnum status);
}
