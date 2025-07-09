package faculdade.mercadopago.gateway.entity;

import faculdade.mercadopago.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;

    private String nome;
    private String cpf;
    private String email;

    public Usuario toModel() {
        return new Usuario(this.codigo, this.nome, this.cpf, this.email);
    }
}
