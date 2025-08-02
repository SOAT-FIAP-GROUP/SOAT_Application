package faculdade.mercadopago.gateway.entity;


import faculdade.mercadopago.entity.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Time;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "produtos")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;
    private String descricao;

    @JoinColumn(name = "categoriacodigo", referencedColumnName = "codigo")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private CategoriaEntity categoria;

    private BigDecimal preco;

    private Time tempopreparo;

    public Produto toModel(){
        return new Produto(this.getCodigo(),this.getNome(),this.getDescricao(),
                this.getCategoria() != null ? this.getCategoria().toModel() : null,this.getPreco(), this.getTempopreparo());
    }

    public ProdutoEntity(Long codigo){
        this.codigo = codigo;
    }
}
