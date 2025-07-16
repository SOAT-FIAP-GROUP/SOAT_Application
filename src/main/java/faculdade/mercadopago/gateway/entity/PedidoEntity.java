package faculdade.mercadopago.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.entity.Pedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"codigo"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "usuariocodigo")
    private UsuarioEntity usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedidoEnum status;

    @Column(name = "valortotal")
    private BigDecimal valorTotal;

    @Column(name = "datahorasolicitacao")
    private LocalDateTime dataHoraSolicitacao;

    @Column(name = "tempototalpreparo")
    private Time tempoTotalPreparo;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItemEntity> itens;

    public Pedido toModel() {
        return new Pedido(
                codigo,
                usuario != null ? usuario.toModel() : null,
                status,
                valorTotal,
                dataHoraSolicitacao,
                tempoTotalPreparo,
                itens != null
                        ? itens.stream().map(PedidoItemEntity::toModel).toList()
                        : List.of()
        );
    }

    public BigDecimal calcularValorTotalPedido(List<PedidoItemEntity> itens){
        return itens.stream()
                .map(PedidoItemEntity::calcularPrecoTotalItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Time calcularTempoTotalDePreparo(List<PedidoItemEntity> itens) {
        Duration tempoTotal = itens.stream()
                .map(PedidoItemEntity::calcularTempoTotalItem)
                .map(Time::toLocalTime)
                .map(t -> Duration.ofHours(t.getHour()).plusMinutes(t.getMinute()).plusSeconds(t.getSecond()))
                .reduce(Duration.ZERO, Duration::plus   );

        LocalTime totalTime = LocalTime.MIDNIGHT.plus(tempoTotal);
        return Time.valueOf(totalTime);
    }
}
