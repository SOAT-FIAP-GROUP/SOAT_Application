package faculdade.mercadopago.controller.mapper.dto.request;

import faculdade.mercadopago.core.domain.enums.StatusPedidoEnum;

import java.time.LocalDateTime;

public record EntregaRequest(long codigo, long pedido, StatusPedidoEnum status, LocalDateTime dataHoraSolicitacao) {
}
