package faculdade.mercadopago.adapter.driven.entity;

import faculdade.mercadopago.gateway.entity.PedidoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "filapedidospreparacao")
@Data
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
}
