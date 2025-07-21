package faculdade.mercadopago.gateway.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "pagamentos")
public class PagamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO")
    private Long id;

    @NonNull
    @OneToOne
    @JoinColumn(name = "pedidocodigo", referencedColumnName = "CODIGO")
    private PedidoEntity pedidoId;

    @NonNull
    @Column(name = "VALORPAGO")
    private BigDecimal valor;

    @NonNull
    @Column(name = "STATUS")
    private String status;

    @Column(name = "DATAHORAPAGAMENTO")
    private LocalDateTime dataPagamento;

    @PrePersist
    public void prePersist() {
        if (this.dataPagamento == null) {
            this.dataPagamento = LocalDateTime.now();
        }
    }
}
