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


    public PedidoItem toModel() {
        return new PedidoItem(this.codigo, this.pedido.getCodigo(), this.produtoCodigo.getCodigo(), this.quantidade, this.precoUnitario, this.precoTotal);
    }

}
