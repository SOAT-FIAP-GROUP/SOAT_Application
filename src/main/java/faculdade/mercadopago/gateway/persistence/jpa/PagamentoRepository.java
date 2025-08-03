package faculdade.mercadopago.gateway.persistence.jpa;

import faculdade.mercadopago.gateway.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {
}