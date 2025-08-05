package faculdade.mercadopago.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import faculdade.mercadopago.entity.Entrega;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "entregas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "codigo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EntregaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @OneToOne
    @JoinColumn(name = "pedidocodigo")
    private PedidoEntity pedidoCodigo;

    @Column(name = "datahoraentrega")
    private LocalDateTime dataHoraEntrega;

    public Entrega toModel() {
        return new Entrega(this.getCodigo(), this.getPedidoCodigo().toModel(), this.getDataHoraEntrega());
    }

}
