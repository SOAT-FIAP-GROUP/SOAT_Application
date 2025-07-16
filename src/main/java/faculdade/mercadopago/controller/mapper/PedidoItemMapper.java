package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.controller.mapper.dto.response.PedidoItemResponse;
import faculdade.mercadopago.entity.PedidoItem;
import faculdade.mercadopago.gateway.entity.PedidoItemEntity;

public class PedidoItemMapper {

    public static PedidoItemResponse toResponse(PedidoItem entity){
        return new PedidoItemResponse(entity.id(), entity.quantidade(), entity.precoUnitario(), entity.precoTotal());
    }

    public static PedidoItem toEntity(PedidoItemEntity pedidoItemEntity) {
        return new PedidoItem(pedidoItemEntity.getCodigo(), pedidoItemEntity.getQuantidade(), pedidoItemEntity.getPrecoUnitario(), pedidoItemEntity.getPrecoTotal());
    }
}
