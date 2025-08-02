package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.controller.mapper.dto.request.EntregaRequest;
import faculdade.mercadopago.controller.mapper.dto.request.ProdutoRequest;
import faculdade.mercadopago.controller.mapper.dto.response.EntregaResponse;
import faculdade.mercadopago.entity.Categoria;
import faculdade.mercadopago.entity.Entrega;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.Produto;
import faculdade.mercadopago.gateway.entity.EntregaEntity;

public class EntregaMapper {

    public static EntregaResponse toResponse(Entrega entity){
        return new EntregaResponse(entity.id(), entity.pedido().status(), entity.dataHoraEntrega());
    }

    public static EntregaEntity toEntityPersistence(Entrega entity) {
        return new EntregaEntity(entity.id(), PedidoMapper.toEntityPersistence(entity.pedido()), entity.dataHoraEntrega());
    }

    public static Entrega toEntity(EntregaRequest entrega) {
        Pedido pedido = new Pedido(entrega.pedidoId(), null,null,null,null,null,null);
        return new Entrega(null, pedido, entrega.dataHoraSolicitacao());
    }
}
