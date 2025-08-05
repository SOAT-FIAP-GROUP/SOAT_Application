package faculdade.mercadopago.controller.mapper.dto.request;

import java.util.List;

public record PedidoRequest(Long idUsuario, List<PedidoItemRequest> itens) {
}
