package faculdade.mercadopago.controller.mapper.dto.request;

import faculdade.mercadopago.entity.Categoria;

import java.math.BigDecimal;
import java.sql.Time;

public record ProdutoRequest(String nome, String descricao, Long categoriaId, BigDecimal preco, Time tempopreparo) {
}
