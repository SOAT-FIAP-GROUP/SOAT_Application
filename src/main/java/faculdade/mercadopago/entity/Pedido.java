package faculdade.mercadopago.entity;

import faculdade.mercadopago.core.domain.enums.StatusPedidoEnum;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

public record Pedido (Usuario usuario, StatusPedidoEnum status, BigDecimal valorTotal, LocalDateTime dataHoraSolicitacao, Time tempoTotalPreparo, List<PedidoItem> itens) {
}
