package faculdade.mercadopago.usecase.impl;

import faculdade.mercadopago.entity.FilaPedidosPreparacao;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.PedidoItem;
import faculdade.mercadopago.entity.Produto;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.exception.EntityNotFoundException;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.usecase.IFilaPedidosPreparacaoUseCase;
import faculdade.mercadopago.usecase.IPedidoUseCase;
import faculdade.mercadopago.usecase.IProdutoUseCase;
import faculdade.mercadopago.usecase.IUsuarioUseCase;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PedidoUseCase implements IPedidoUseCase {
    private final IPedidoGateway pedidoGateway;
    private final IUsuarioUseCase usuarioUseCase;
    private final IProdutoUseCase produtoUseCase;
    private final IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase;

    public PedidoUseCase(IPedidoGateway pedidoGateway, IUsuarioUseCase usuarioUseCase, IProdutoUseCase produtoUseCase, IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase) {
        this.pedidoGateway = pedidoGateway;
        this.usuarioUseCase = usuarioUseCase;
        this.produtoUseCase = produtoUseCase;
        this.filaPedidosPreparacaoUseCase = filaPedidosPreparacaoUseCase;
    }

    @Override
    public Pedido buscarPedido(Long id) {
        return pedidoGateway.findById(id).orElseThrow(() -> new EntityNotFoundException(Pedido.class, id));
    }

    @Transactional
    @Override
    public Pedido criarPedido(Pedido pedido) {

        usuarioUseCase.buscaUsuarioPorId(pedido.idUsuario());

        Pedido pedidoSalvar = pedido.preSalvar(pedido.idUsuario(), pedido.status(),pedido.dataHoraSolicitacao());

        Pedido pedidoSalvo = pedidoGateway.save(pedidoSalvar);

        AtomicReference<Duration> totalPreparo = new AtomicReference<>(Duration.ZERO);

        List<PedidoItem> itens = pedido.itens().stream()
                .map(item -> {
                    Produto produto = produtoUseCase.buscarProduto(item.id());
                    Duration tempoItem = Duration.between(LocalTime.MIDNIGHT, produto.tempopreparo().toLocalTime())
                            .multipliedBy(item.quantidade());
                    totalPreparo.updateAndGet(tp -> tp.plus(tempoItem));
                    return new PedidoItem(null, pedidoSalvo.id(), produto.id(), item.quantidade(), produto.preco(),
                            produto.preco().multiply(BigDecimal.valueOf(item.quantidade())));
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

    @Override
    public List<Pedido> listarPedidos(StatusPedidoEnum status) {
        return pedidoGateway.findAllByStatus(status);
    }

    @Override
    public Pedido alterarPedido(Long id, StatusPedidoEnum status) {
        Pedido pedido = this.buscarPedido(id);
        Pedido pedidoAtualizar = pedido.withStatus(status);
        return pedidoGateway.save(pedidoAtualizar);
    }

    @Override
    public FilaPedidosPreparacao adicionarPedidoNaFila(Long id) {
        Pedido pedido = this.buscarPedido(id);
        FilaPedidosPreparacao filaPedidosPreparacao = new FilaPedidosPreparacao(null, pedido);
        return filaPedidosPreparacaoUseCase.salvar(filaPedidosPreparacao);
    }

    @Override
    public void removerPedidoDaFila(Long id) {
        FilaPedidosPreparacao filaPedidosPreparacao = filaPedidosPreparacaoUseCase.findByPedidoPorId(id);
        filaPedidosPreparacaoUseCase.removerPedidoDaFila(filaPedidosPreparacao);
    }

    @Override
    public List<Pedido> listaPedidosOrd() {
        return pedidoGateway.findAllOrdenado();
    }

}
