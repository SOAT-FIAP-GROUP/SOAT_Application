package faculdade.mercadopago.gateway.persistence.jpa;

import faculdade.mercadopago.adapter.driven.entity.EntregaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<EntregaEntity, Long> {
}
