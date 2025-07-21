package faculdade.mercadopago.api.controller;

import faculdade.mercadopago.controller.PagamentoController;
import faculdade.mercadopago.controller.mapper.dto.request.QrCodeRequest;
import faculdade.mercadopago.controller.mapper.dto.response.QrCodeResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoAPIController {

    private final PagamentoController pagamentoController;

    public PagamentoAPIController(PagamentoController pagamentoController) {
        this.pagamentoController = pagamentoController;
    }

    @Operation(summary = "Gerar um Qr Code", description = "Retorna uma string do Qr Code")
    @PostMapping
    public ResponseEntity<QrCodeResponse> gerarQrCode(@RequestBody @Valid QrCodeRequest request){
        var response = pagamentoController.gerarQrCode(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}