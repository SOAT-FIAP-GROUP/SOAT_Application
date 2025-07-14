package faculdade.mercadopago.gateway.persistence.jpa;

import faculdade.mercadopago.gateway.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
}
