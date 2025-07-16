package faculdade.mercadopago.core.domain.mapper;

import faculdade.mercadopago.core.domain.dto.ViewPedidoDto;
import faculdade.mercadopago.gateway.entity.PedidoEntity;
import faculdade.mercadopago.gateway.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(source = "usuario", target = "usuario")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "codigo", target = "pedido")
    ViewPedidoDto entityToDto(PedidoEntity pedidoEntity);

    default long map(UsuarioEntity usuarioEntity) {
        return usuarioEntity.getCodigo();
    }
}
