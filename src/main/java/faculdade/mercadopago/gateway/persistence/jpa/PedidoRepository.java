package faculdade.mercadopago.gateway.persistence.jpa;

import faculdade.mercadopago.core.domain.enums.StatusPedidoEnum;
import faculdade.mercadopago.gateway.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    List<PedidoEntity> findAllByStatus(StatusPedidoEnum status);
}
