package faculdade.mercadopago.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import faculdade.mercadopago.entity.Entrega;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "entregas")
@Getter
@Setter
@EqualsAndHashCode(of = "codigo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EntregaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;

    @OneToOne
    @JoinColumn(name = "pedidocodigo")
    private PedidoEntity pedidoCodigo;

    @Column(name = "datahoraentrega")
    private LocalDateTime dataHoraEntrega;

    public Entrega toModel() {
        return new Entrega(this.getCodigo(), this.getPedidoCodigo().toModel(), this.getDataHoraEntrega());
    }

}
