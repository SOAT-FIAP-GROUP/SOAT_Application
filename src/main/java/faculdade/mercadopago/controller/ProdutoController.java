package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.CategoriaMapper;
import faculdade.mercadopago.controller.mapper.dto.request.ProdutoRequest;
import faculdade.mercadopago.controller.mapper.dto.response.CategoriaResponse;
import faculdade.mercadopago.controller.mapper.dto.response.ProdutoResponse;
import faculdade.mercadopago.controller.mapper.ProdutoMapper;
import faculdade.mercadopago.core.domain.dto.NewProdutoDto;
import faculdade.mercadopago.core.domain.dto.ViewProdutoDto;
import faculdade.mercadopago.gateway.ICategoriaGateway;
import faculdade.mercadopago.gateway.IProdutoGateway;
import faculdade.mercadopago.usercase.IProdutoUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoController {

    private final IProdutoUseCase produtoUseCase;
    private final IProdutoGateway produtoGateway;
    private final ProdutoMapper produtoMapper;

    private final ICategoriaGateway categoriaGateway;
    private final CategoriaMapper categoriaMapper;

    public ProdutoController(IProdutoUseCase produtoUseCase, IProdutoGateway produtoGateway, ProdutoMapper produtoMapper, ICategoriaGateway categoriaGateway, CategoriaMapper categoriaMapper) {
        this.produtoUseCase = produtoUseCase;
        this.produtoGateway = produtoGateway;
        this.produtoMapper = produtoMapper;
        this.categoriaGateway = categoriaGateway;
        this.categoriaMapper = categoriaMapper;
    }

    public ProdutoResponse buscarProduto(Long id) {
        return produtoMapper.toResponse(produtoUseCase.buscarProduto(id, produtoGateway));
    }

    public CategoriaResponse buscarCategoria(Long id) {
        return categoriaMapper.toResponse(produtoUseCase.buscarCategoria(id, categoriaGateway));
    }

    public List<ProdutoResponse> buscarProdutosPorCategoria(Long codigoCategoria) {
        return produtoUseCase.buscarProdutosPorCategoria(codigoCategoria, produtoGateway)
                .stream().map(produtoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProdutoResponse cadastrarProduto(ProdutoRequest produto) {
        return produtoMapper.toResponse(
                produtoUseCase.cadastrarProduto(ProdutoMapper.toEntity(produto), produtoGateway));
    }

    public void removerProduto(Long id) {
        produtoUseCase.removerProduto(id,produtoGateway);
    }

    public ProdutoResponse atualizarProduto(ProdutoRequest produto, Long id) {
        return produtoMapper.toResponse(
                produtoUseCase.atualizarProduto(id, ProdutoMapper.toEntity(produto), produtoGateway));
    }
}
