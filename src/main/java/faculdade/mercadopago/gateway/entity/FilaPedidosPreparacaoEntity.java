package faculdade.mercadopago.gateway.entity;

import faculdade.mercadopago.entity.FilaPedidosPreparacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Table(name = "filapedidospreparacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "filapedidospreparacao")
public class FilaPedidosPreparacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @OneToOne
    @JoinColumn(name = "pedidocodigo")
    private PedidoEntity pedidocodigo;

    public FilaPedidosPreparacao toModel() {
        return new FilaPedidosPreparacao(this.codigo, this.pedidocodigo.toModel());
    }
}
