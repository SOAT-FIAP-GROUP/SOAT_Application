package faculdade.mercadopago.api.controller;

import faculdade.mercadopago.controller.PedidoController;
import faculdade.mercadopago.controller.mapper.dto.response.PedidoResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedido")
public class PedidoAPIController {
    private final PedidoController pedidoController;

    public PedidoAPIController(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
    }

    @Operation(summary = "Buscar pedido por codigo", description = "Retorna um pedido especifico cadastrado no codigo")
    @GetMapping("/buscar/pedido/{codigoProduto}")
    public ResponseEntity<PedidoResponse> buscarPorProduto(@PathVariable Long codigoPedido) throws Exception {
        var response = pedidoController.buscarProduto(codigoPedido);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
