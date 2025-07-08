package faculdade.mercadopago.gateway.entity;

import faculdade.mercadopago.entity.Categoria;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "categorias")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    private String nome;

    public Categoria toModel(){
        return new Categoria(this.getCodigo(), this.getNome());
    }

}
