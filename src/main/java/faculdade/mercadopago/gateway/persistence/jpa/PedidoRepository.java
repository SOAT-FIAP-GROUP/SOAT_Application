package faculdade.mercadopago.gateway.persistence.jpa;

import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.gateway.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    List<PedidoEntity> findAllByStatus(StatusPedidoEnum status);

    @Query(value = """
            SELECT *
            FROM pedidos p
            WHERE p.status <> 'FINALIZADO'
            ORDER BY
              CASE p.status
                WHEN 'PRONTO' THEN 1
                WHEN 'EM_PREPARACAO' THEN 2
                WHEN 'RECEBIDO' THEN 3
                ELSE 4
              END,
              p.datahorasolicitacao ASC
            """, nativeQuery = true)
    List<PedidoEntity>findAllOrdenado();
}
