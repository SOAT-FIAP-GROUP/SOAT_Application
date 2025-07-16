package faculdade.mercadopago.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import faculdade.mercadopago.entity.PedidoItem;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;

@Table(name = "pedidoitem")
@Data
@Builder
@Entity
@EqualsAndHashCode(of = "codigo")
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "pedidocodigo")
    @JsonIgnore
    private PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name = "produtocodigo")
    @JsonIgnore
    private ProdutoEntity produtoCodigo;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "precounitario")
    private BigDecimal precoUnitario;

    @Column(name = "precototal")
    private BigDecimal precoTotal;


    public BigDecimal calcularPrecoTotalItem() {
        if (this.produtoCodigo != null && this.produtoCodigo.getPreco() != null && quantidade > 0) {
            return this.precoTotal = this.produtoCodigo.getPreco().multiply(BigDecimal.valueOf(quantidade));
        } else {
            return this.precoTotal = BigDecimal.ZERO;
        }
    }

    public Time calcularTempoTotalItem() {
        LocalTime tempoUnitario = produtoCodigo.getTempopreparo().toLocalTime();
        Duration duracaoUnitario = Duration.ofHours(tempoUnitario.getHour())
                .plusMinutes(tempoUnitario.getMinute())
                .plusSeconds(tempoUnitario.getSecond());

        Duration duracaoTotal = duracaoUnitario.multipliedBy(quantidade);
        LocalTime tempoTotal = LocalTime.MIDNIGHT.plus(duracaoTotal);

        return Time.valueOf(tempoTotal);
    }

    public PedidoItem toModel() {
        return new PedidoItem(this.codigo, this.quantidade, this.precoUnitario, this.precoTotal);
    }

}
