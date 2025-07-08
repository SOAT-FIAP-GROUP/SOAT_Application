package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.dto.response.ProdutoResponse;
import faculdade.mercadopago.controller.mapper.ProdutoMapper;
import faculdade.mercadopago.core.domain.dto.NewProdutoDto;
import faculdade.mercadopago.core.domain.dto.ViewProdutoDto;
import faculdade.mercadopago.gateway.IProdutoGateway;
import faculdade.mercadopago.usercase.IProdutoUseCase;

public class ProdutoController {

    private final IProdutoUseCase produtoUseCase;
    private final IProdutoGateway produtoGateway;
    private final ProdutoMapper produtoMapper;

    public ProdutoController(IProdutoUseCase produtoUseCase, IProdutoGateway produtoGateway, ProdutoMapper produtoMapper) {
        this.produtoUseCase = produtoUseCase;
        this.produtoGateway = produtoGateway;
        this.produtoMapper = produtoMapper;
    }

    public ProdutoResponse buscarProduto(Long id) {
        return produtoMapper.toResponse(produtoUseCase.buscarProduto(id, produtoGateway));
    }

    public Object buscarCategoria(Long codigoCategoria) {
        return null;
    }

    public Object buscarProdutosPorCategoria(Long codigoCategoria) {
        return null;
    }

    public Object cadastrarProduto(NewProdutoDto produto) {
        return null;
    }

    public void removerProduto(Long codigo) {
    }

    public Object atualizarProduto(ViewProdutoDto produto, Long codigo) {
        return null;
    }
}
