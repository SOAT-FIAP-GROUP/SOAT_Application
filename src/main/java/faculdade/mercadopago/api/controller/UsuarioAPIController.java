package faculdade.mercadopago.api.controller;

import faculdade.mercadopago.controller.UsuarioController;
import faculdade.mercadopago.controller.mapper.dto.request.UsuarioRequest;
import faculdade.mercadopago.controller.mapper.dto.response.UsuarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioAPIController {

    private final UsuarioController usuarioController;

    public UsuarioAPIController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    @Operation(summary = "Identificar usuário", description = "Busca cadastro de cliente já existente")
    @GetMapping
    public ResponseEntity<UsuarioResponse> buscarUsuario(@RequestParam String cpf) throws Exception {
        var response = usuarioController.buscarUsuarioPorCpf(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @Operation(summary = "Cadastrar usuário", description = "Cadastra um novo usuário ou identifica usuário com registro padrão")
    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@RequestBody @Valid UsuarioRequest request) throws Exception {
        var response = usuarioController.processarUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
