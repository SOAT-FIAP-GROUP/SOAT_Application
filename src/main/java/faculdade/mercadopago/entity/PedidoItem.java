package faculdade.mercadopago.entity;

import java.math.BigDecimal;

public record PedidoItem (long id, int quantidade, BigDecimal precoUnitario, BigDecimal precoTotal) {
}
