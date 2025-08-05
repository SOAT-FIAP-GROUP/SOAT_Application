package faculdade.mercadopago.entity;

import java.time.LocalDateTime;

public record Entrega (Long id, Pedido pedido, LocalDateTime dataHoraEntrega) {
}
