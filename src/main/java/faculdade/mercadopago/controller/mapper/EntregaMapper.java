package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.controller.mapper.dto.response.EntregaResponse;
import faculdade.mercadopago.entity.Entrega;

public class EntregaMapper {

    public static EntregaResponse toResponse(Entrega entity){
        return new EntregaResponse(entity.id(), entity.pedido().status(), entity.dataHoraEntrega());
    }
}
