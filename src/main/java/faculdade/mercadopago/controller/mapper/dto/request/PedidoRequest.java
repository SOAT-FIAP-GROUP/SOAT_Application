package faculdade.mercadopago.controller.mapper.dto.request;

import faculdade.mercadopago.core.domain.enums.StatusPedidoEnum;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoRequest(Long idUsuario, StatusPedidoEnum status, BigDecimal valorTotal, LocalDateTime dataHoraSolicitacao, Time tempoTotalPreparo, List<PedidoItemRequest> itens) {
}
