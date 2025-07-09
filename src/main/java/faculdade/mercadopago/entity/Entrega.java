package faculdade.mercadopago.entity;

import java.time.LocalDateTime;

public record Entrega (long id, Pedido pedido, LocalDateTime dataHoraEntrega) {
}
