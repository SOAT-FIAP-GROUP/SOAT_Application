package faculdade.mercadopago.gateway.impl;

import faculdade.mercadopago.controller.mapper.FilaPedidosPreparacaoMapper;
import faculdade.mercadopago.entity.FilaPedidosPreparacao;
import faculdade.mercadopago.gateway.IFilaPedidosPreparacaoGateway;
import faculdade.mercadopago.gateway.entity.FilaPedidosPreparacaoEntity;
import faculdade.mercadopago.gateway.persistence.jpa.FilaPedidosPreparacaoRepository;

import java.util.Optional;

public class FilaPedidosPreparacaoGateway implements IFilaPedidosPreparacaoGateway {

    private final FilaPedidosPreparacaoRepository filaPedidosPreparacaoRepository;

    public FilaPedidosPreparacaoGateway(FilaPedidosPreparacaoRepository filaPedidosPreparacaoRepository) {
        this.filaPedidosPreparacaoRepository = filaPedidosPreparacaoRepository;
    }

    @Override
    public FilaPedidosPreparacao save(FilaPedidosPreparacao entity) {
        return filaPedidosPreparacaoRepository.save(FilaPedidosPreparacaoMapper.toEntityPersistence(entity)).toModel();
    }

    @Override
    public Optional<FilaPedidosPreparacao> findById(Long id) {
        return filaPedidosPreparacaoRepository.findById(id).map(FilaPedidosPreparacaoEntity::toModel);
    }

    @Override
    public Optional<FilaPedidosPreparacao> findByPedidocodigoId(Long id) {
        return filaPedidosPreparacaoRepository.findByPedidocodigoCodigo(id).map(FilaPedidosPreparacaoEntity::toModel);
    }

    @Override
    public void removerPedidoDaFila(FilaPedidosPreparacao entity) {
        filaPedidosPreparacaoRepository.delete(FilaPedidosPreparacaoMapper.toEntityPersistence(entity));
    }
}
