package faculdade.mercadopago.usecase.impl;

import faculdade.mercadopago.entity.FilaPedidosPreparacao;
import faculdade.mercadopago.exception.EntityNotFoundException;
import faculdade.mercadopago.gateway.IFilaPedidosPreparacaoGateway;
import faculdade.mercadopago.usecase.IFilaPedidosPreparacaoUseCase;

public class FilaPedidosPreparacaoUseCase implements IFilaPedidosPreparacaoUseCase {

    private final IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway;

    public FilaPedidosPreparacaoUseCase(IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway) {
        this.filaPedidosPreparacaoGateway = filaPedidosPreparacaoGateway;
    }

    @Override
    public FilaPedidosPreparacao salvar(FilaPedidosPreparacao filaPedidosPreparacao) {
        return filaPedidosPreparacaoGateway.save(filaPedidosPreparacao);
    }

    @Override
    public FilaPedidosPreparacao findByPedidoPorId(Long id) {
        return filaPedidosPreparacaoGateway.findByPedidocodigoId(id).orElseThrow(() -> new EntityNotFoundException(FilaPedidosPreparacao.class, id));
    }

    @Override
    public void removerPedidoDaFila(FilaPedidosPreparacao filaPedidosPreparacao) {
        filaPedidosPreparacaoGateway.removerPedidoDaFila(filaPedidosPreparacao);
    }
}
