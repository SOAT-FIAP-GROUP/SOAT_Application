package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.controller.mapper.dto.request.PedidoRequest;
import faculdade.mercadopago.controller.mapper.dto.response.PedidoResponse;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.Usuario;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.gateway.entity.PedidoEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PedidoMapper {

    public PedidoResponse toResponse(Pedido entity){
        return new PedidoResponse(entity.id(), entity.idUsuario(), entity.status(), entity.valorTotal(), entity.dataHoraSolicitacao(),
                entity.tempoTotalPreparo(),
                entity.itens().stream().map(PedidoItemMapper::toResponse).toList());
    }

    public static Pedido fromResponse(PedidoResponse pedido){
        return new Pedido(pedido.id(), pedido.idUsuario(), pedido.status(),pedido.valorTotal(),pedido.dataHoraSolicitacao(),pedido.tempoTotalPreparo(),
                pedido.itens().stream().map(PedidoItemMapper::fromResponse).toList());
    }

    public Pedido toEntity(PedidoRequest pedidoRequest) {
        return new Pedido(null, pedidoRequest.idUsuario(), StatusPedidoEnum.RECEBIDO, null, LocalDateTime.now(), null, pedidoRequest.itens().stream().map(PedidoItemMapper::toEntity).toList());
    }

    public static PedidoEntity toEntityPersistence(Pedido pedido) {
        Usuario usuario = new Usuario(pedido.idUsuario(), null,null,null);
        return new PedidoEntity(pedido.id(), UsuarioMapper.toEntityPersistence(usuario), pedido.status(), pedido.valorTotal(), pedido.dataHoraSolicitacao(),
                pedido.tempoTotalPreparo(), pedido.itens().stream().map(PedidoItemMapper::toEntityPersistence).collect(Collectors.toCollection(ArrayList::new)));
    }
}
