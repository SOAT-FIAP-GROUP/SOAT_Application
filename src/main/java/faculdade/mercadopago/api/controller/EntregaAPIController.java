package faculdade.mercadopago.api.controller;

import faculdade.mercadopago.controller.EntregaController;
import faculdade.mercadopago.controller.mapper.dto.request.EntregaRequest;
import faculdade.mercadopago.controller.mapper.dto.response.EntregaResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/entregar")
public class EntregaAPIController {

    private final EntregaController entregaController;

    public EntregaAPIController(EntregaController entregaController) {this.entregaController = entregaController;}


    @Operation(summary = "Finalizar Pedido", description = "Rota responsável por finalizar o pedido e retirar da fila")
    @PostMapping
    public ResponseEntity<EntregaResponse> finalizarPedido(@RequestBody @Valid EntregaRequest entregaRequest) {
        var response =  entregaController.entregarPedido(entregaRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
