package faculdade.mercadopago.controller.mapper.dto.response;

import java.math.BigDecimal;
import java.sql.Time;

public record ProdutoResponse(Long id, String nome, String descricao, CategoriaResponse categoria, BigDecimal preco, Time tempopreparo) {
}
