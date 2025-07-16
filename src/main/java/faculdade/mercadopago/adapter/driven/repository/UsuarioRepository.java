package faculdade.mercadopago.adapter.driven.repository;

import faculdade.mercadopago.gateway.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {

    UsuarioEntity findByCpf(String cpf);
}
