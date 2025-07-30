package faculdade.mercadopago.api.controller;

import faculdade.mercadopago.controller.PedidoController;
import faculdade.mercadopago.controller.mapper.dto.request.PedidoRequest;
import faculdade.mercadopago.controller.mapper.dto.response.PedidoResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedido")
public class PedidoAPIController {
    private final PedidoController pedidoController;

    public PedidoAPIController(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
    }

    @Operation(summary = "Buscar pedido por codigo", description = "Retorna um pedido especifico cadastrado no codigo")
    @GetMapping("/buscar/{codigoPedido}")
    public ResponseEntity<PedidoResponse> buscarPorProduto(@PathVariable Long codigoPedido) throws Exception {
        var response = pedidoController.buscarProduto(codigoPedido);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Criar pedido", description = "Retorna um novo Pedido")
    @PostMapping
    public ResponseEntity<PedidoResponse> criarPedido(@RequestBody PedidoRequest pedidoRequest) throws Exception {
        var response = pedidoController.criarPedido(pedidoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
