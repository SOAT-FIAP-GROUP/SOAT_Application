package faculdade.mercadopago.controller.mapper.dto.request;

public record UsuarioRequest(Boolean identificarUsuario, String nome, String cpf, String email) {
}
