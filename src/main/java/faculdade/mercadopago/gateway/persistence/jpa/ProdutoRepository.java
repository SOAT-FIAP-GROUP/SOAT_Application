package faculdade.mercadopago.gateway.persistence.jpa;

import faculdade.mercadopago.gateway.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    List<ProdutoEntity> findByCategoriaCodigo(long codigoCategoria);
}
