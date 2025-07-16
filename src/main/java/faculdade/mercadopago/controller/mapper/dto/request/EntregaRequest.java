package faculdade.mercadopago.controller.mapper.dto.request;

import faculdade.mercadopago.entity.enums.StatusPedidoEnum;

import java.time.LocalDateTime;

public record EntregaRequest(Long codigo, Long pedido, StatusPedidoEnum status, LocalDateTime dataHoraSolicitacao) {
}
