package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.controller.mapper.dto.request.PedidoItemRequest;
import faculdade.mercadopago.controller.mapper.dto.response.PedidoItemResponse;
import faculdade.mercadopago.entity.PedidoItem;
import faculdade.mercadopago.gateway.entity.PedidoEntity;
import faculdade.mercadopago.gateway.entity.PedidoItemEntity;
import faculdade.mercadopago.gateway.entity.ProdutoEntity;

public class PedidoItemMapper {

    public static PedidoItemResponse toResponse(PedidoItem entity){
        return new PedidoItemResponse(entity.id(), entity.pedidoId(), entity.produtoId(), entity.quantidade(), entity.precoUnitario(), entity.precoTotal());
    }

    public static PedidoItem fromResponse(PedidoItemResponse pedidoItem){
        return new PedidoItem(pedidoItem.id(), pedidoItem.pedidoId(), pedidoItem.produtoId(), pedidoItem.quantidade(), pedidoItem.precoUnitario(), pedidoItem.precoTotal());
    }

    public static PedidoItem toEntity(PedidoItemRequest pedidoItemRequest) {
        return new PedidoItem(pedidoItemRequest.produtoId(), null, null, pedidoItemRequest.quantidade(), null, null);
    }

    public static PedidoItemEntity toEntityPersistence(PedidoItem pedidoItem) {
        return new PedidoItemEntity(pedidoItem.id(), new PedidoEntity(pedidoItem.pedidoId()), new ProdutoEntity(pedidoItem.produtoId()), pedidoItem.quantidade(), pedidoItem.precoUnitario(), pedidoItem.precoTotal());
    }
}
