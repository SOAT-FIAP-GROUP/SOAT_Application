package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.CategoriaMapper;
import faculdade.mercadopago.controller.mapper.ProdutoMapper;
import faculdade.mercadopago.controller.mapper.dto.request.ProdutoRequest;
import faculdade.mercadopago.controller.mapper.dto.response.CategoriaResponse;
import faculdade.mercadopago.controller.mapper.dto.response.ProdutoResponse;
import faculdade.mercadopago.usecase.IProdutoUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoController {

    private final IProdutoUseCase produtoUseCase;
    private final ProdutoMapper produtoMapper;
    private final CategoriaMapper categoriaMapper;

    public ProdutoController(IProdutoUseCase produtoUseCase, ProdutoMapper produtoMapper, CategoriaMapper categoriaMapper) {
        this.produtoUseCase = produtoUseCase;
        this.produtoMapper = produtoMapper;
        this.categoriaMapper = categoriaMapper;
    }

    public ProdutoResponse buscarProduto(Long id) {
        return produtoMapper.toResponse(produtoUseCase.buscarProduto(id));
    }

    public CategoriaResponse buscarCategoria(Long id) {
        return categoriaMapper.toResponse(produtoUseCase.buscarCategoria(id));
    }

    public List<ProdutoResponse> buscarProdutosPorCategoria(Long codigoCategoria) {
        return produtoUseCase.buscarProdutosPorCategoria(codigoCategoria)
                .stream().map(produtoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProdutoResponse cadastrarProduto(ProdutoRequest produto) {
        return produtoMapper.toResponse(
                produtoUseCase.cadastrarProduto(ProdutoMapper.toEntity(produto)));
    }

    public void removerProduto(Long id) {
        produtoUseCase.removerProduto(id);
    }

    public ProdutoResponse atualizarProduto(ProdutoRequest produto, Long id) {
        return produtoMapper.toResponse(
                produtoUseCase.atualizarProduto(id, ProdutoMapper.toEntity(produto)));
    }
}
