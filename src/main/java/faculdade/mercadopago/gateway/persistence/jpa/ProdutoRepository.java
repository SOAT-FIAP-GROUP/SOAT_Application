package faculdade.mercadopago.gateway.persistence.jpa;

import faculdade.mercadopago.gateway.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
