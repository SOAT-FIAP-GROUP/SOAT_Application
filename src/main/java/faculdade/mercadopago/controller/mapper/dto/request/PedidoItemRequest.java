package faculdade.mercadopago.controller.mapper.dto.request;

import java.math.BigDecimal;

public record PedidoItemRequest(Long id, int quantidade, BigDecimal precoUnitario, BigDecimal precoTotal) {
}
