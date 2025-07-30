package faculdade.mercadopago.usecase.impl;

import faculdade.mercadopago.controller.ProdutoController;
import faculdade.mercadopago.controller.UsuarioController;
import faculdade.mercadopago.controller.mapper.ProdutoMapper;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.PedidoItem;
import faculdade.mercadopago.entity.Produto;
import faculdade.mercadopago.exception.EntityNotFoundException;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.usecase.IPedidoUseCase;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PedidoUseCase implements IPedidoUseCase {

    @Override
    public Pedido buscarPedido(Long id, IPedidoGateway gateway) {
        return gateway.findById(id).orElseThrow(() -> new EntityNotFoundException(Pedido.class, id));
    }
    @Transactional
    @Override
    public Pedido criarPedido(Pedido pedido, IPedidoGateway pedidoGateway, ProdutoController produtoController, UsuarioController usuarioController) {

        Pedido pedidoSalvar = new Pedido(
                pedido.id(),
                pedido.idUsuario(),
                pedido.status(),
                null,
                pedido.dataHoraSolicitacao(),
                pedido.tempoTotalPreparo(),
                new ArrayList<>()
        );

        Pedido pedidoSalvo = pedidoGateway.save(pedidoSalvar);
        AtomicReference<Duration> totalPreparo = new AtomicReference<>(Duration.ZERO);
        List<PedidoItem> itens = pedido.itens().stream()
                .map(item -> {
                    Produto produto = ProdutoMapper.toResponseByRes(produtoController.buscarProduto(item.id()));
                    Duration tempoItem = Duration.between(LocalTime.MIDNIGHT, produto.tempopreparo().toLocalTime())
                            .multipliedBy(item.quantidade());
                    totalPreparo.updateAndGet(tp -> tp.plus(tempoItem));
                    return new PedidoItem(null, pedidoSalvo.id(), produto.id(), item.quantidade(), produto.preco(), produto.preco().multiply(BigDecimal.valueOf(item.quantidade())));
                }).toList();

        if (itens.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "O pedido deve conter ao menos um item.");
        }

        BigDecimal totalAPagar = itens.stream()
                .map(PedidoItem::precoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Duration duracao = totalPreparo.get();
        LocalTime tempoTotal = LocalTime.of(
                duracao.toHoursPart(),
                duracao.toMinutesPart(),
                duracao.toSecondsPart()
        );
        Time tempoTotalSql = Time.valueOf(tempoTotal);

        Pedido pedidoAtualizar = new Pedido(
                pedidoSalvo.id(),
                pedido.idUsuario(),
                pedido.status(),
                totalAPagar,
                pedido.dataHoraSolicitacao(),
                tempoTotalSql,
                itens
        );
        return pedidoGateway.save(pedidoAtualizar);
    }

}
