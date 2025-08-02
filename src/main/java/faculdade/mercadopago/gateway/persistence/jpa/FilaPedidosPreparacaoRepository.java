package faculdade.mercadopago.gateway.persistence.jpa;

import faculdade.mercadopago.gateway.entity.FilaPedidosPreparacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilaPedidosPreparacaoRepository extends JpaRepository<FilaPedidosPreparacaoEntity,Long> {
    Optional<FilaPedidosPreparacaoEntity> findByPedidocodigoCodigo(Long id);
}
