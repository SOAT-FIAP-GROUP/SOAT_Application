package faculdade.mercadopago.core.domain.mapper;

import faculdade.mercadopago.core.domain.dto.NewProdutoDto;
import faculdade.mercadopago.core.domain.dto.ViewProdutoDto;
import faculdade.mercadopago.gateway.entity.CategoriaEntity;
import faculdade.mercadopago.gateway.entity.ProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    @Mapping(source = "categoria.codigo", target = "categoria")
    ViewProdutoDto entityToDto(ProdutoEntity produtoEntity);

    @Mapping(source = "categoria", target = "categoria")
    ProdutoEntity newDtoToEntity(NewProdutoDto newProdutoDto);

    @Mapping(source = "categoria", target = "categoria")
    ProdutoEntity viewDtoToEntity(ViewProdutoDto viewProdutoDto);

    @Mapping(source = "categoria", target = "categoria")
    void updateFromDto(ViewProdutoDto dto, @MappingTarget ProdutoEntity entity);

    default CategoriaEntity map(Long idCategoria) {
        if (idCategoria == null) return null;
        CategoriaEntity d = new CategoriaEntity();
        d.setCodigo(idCategoria);
        return d;
    }
}
