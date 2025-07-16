package faculdade.mercadopago.controller.mapper.dto.response;

import faculdade.mercadopago.core.domain.enums.StatusPedidoEnum;

import java.time.LocalDateTime;

public record EntregaResponse (Long id, StatusPedidoEnum status, LocalDateTime dataHoraEntrega) {
}
