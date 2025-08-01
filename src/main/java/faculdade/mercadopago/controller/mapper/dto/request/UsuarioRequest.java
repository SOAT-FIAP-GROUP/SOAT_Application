package faculdade.mercadopago.controller.mapper.dto.request;

import faculdade.mercadopago.exception.BusinessException;

import java.util.Objects;

public record UsuarioRequest(Boolean identificarUsuario, String nome, String cpf, String email) {
    public void validar() {
        if (Objects.equals(identificarUsuario, null)) {
            throw new BusinessException("O campo 'identificarUsuario' é obrigatório.");
        }

        if (Objects.equals(identificarUsuario, true)) {
            validarCpf(cpf);
            validarNome(nome);
            validarEmail(email);
        }
    }

    private void validarNome(String nome) {
        if (Objects.equals(nome, null) || nome.isBlank()) {
            throw new BusinessException("O campo 'nome' é obrigatório quando 'identificarUsuario' for falso.");
        }

        if (nome.length() < 3 || nome.length() > 50) {
            throw new BusinessException("O campo 'nome' deve conter entre 3 e 50 caracteres.");
        }
    }

    private void validarCpf(String cpf) {
        if (Objects.equals(cpf, null) || cpf.isBlank()) {
            throw new BusinessException("O campo 'cpf' é obrigatório quando 'identificarUsuario' for verdadeiro.");
        }

        if (cpf.length() != 11) {
            throw new BusinessException("O campo 'cpf' deve conter exatamente 11 caracteres.");
        }

        if (!cpf.matches("\\d+")) {
            throw new BusinessException("O campo 'cpf' deve conter apenas números.");
        }
    }

    private void validarEmail(String email) {
        if (Objects.equals(email, null) || email.isBlank()) {
            throw new BusinessException("O campo 'email' é obrigatório.");
        }

        if (!email.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            throw new BusinessException("O campo 'email' deve ser um endereço de e-mail válido.");
        }
    }
}
