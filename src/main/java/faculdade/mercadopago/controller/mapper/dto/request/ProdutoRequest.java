package faculdade.mercadopago.controller.mapper.dto.request;

import faculdade.mercadopago.entity.Categoria;

import java.math.BigDecimal;
import java.sql.Time;

public record ProdutoRequest(String nome, String descricao, Categoria categoria, BigDecimal preco, Time tempopreparo) {
}
