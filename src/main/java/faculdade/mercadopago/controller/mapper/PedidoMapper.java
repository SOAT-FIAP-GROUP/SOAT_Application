package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.controller.mapper.dto.response.PedidoResponse;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.Usuario;
import faculdade.mercadopago.gateway.entity.PedidoEntity;

public class PedidoMapper {

    public static PedidoResponse toResponse(Pedido entity){
        return new PedidoResponse(entity.usuario().codigo(), entity.status(), entity.valorTotal(), entity.dataHoraSolicitacao(),
                entity.tempoTotalPreparo(),
                entity.itens().stream().map(PedidoItemMapper::toResponse).toList());
    }

    public static Pedido toEntity(PedidoEntity pedidoEntity) {
        //Mock usuario REMOVER NO FUTURO
        Usuario usuario = new Usuario(1L, "teste","teste","teste");
        return new Pedido(null, usuario, pedidoEntity.getStatus(), pedidoEntity.getValorTotal(), pedidoEntity.getDataHoraSolicitacao(),
                pedidoEntity.getTempoTotalPreparo(), pedidoEntity.getItens().stream().map(PedidoItemMapper::toEntity).toList());
    }

}
