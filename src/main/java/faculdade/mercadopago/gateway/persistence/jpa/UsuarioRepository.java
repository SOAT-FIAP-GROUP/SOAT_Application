package faculdade.mercadopago.gateway.persistence.jpa;

import faculdade.mercadopago.gateway.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByCpf(String cpf);
}
