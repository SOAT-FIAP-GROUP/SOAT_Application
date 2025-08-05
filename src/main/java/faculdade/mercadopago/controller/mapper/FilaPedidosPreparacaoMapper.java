package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.entity.FilaPedidosPreparacao;
import faculdade.mercadopago.gateway.entity.FilaPedidosPreparacaoEntity;

public class FilaPedidosPreparacaoMapper {

    public static FilaPedidosPreparacaoEntity toEntityPersistence(FilaPedidosPreparacao filaPedidosPreparacao){
        return new FilaPedidosPreparacaoEntity(filaPedidosPreparacao.id(),
                PedidoMapper.toEntityPersistence(filaPedidosPreparacao.pedido()));
    }
}
