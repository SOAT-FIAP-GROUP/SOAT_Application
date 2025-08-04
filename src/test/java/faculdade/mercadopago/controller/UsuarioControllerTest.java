package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.UsuarioMapper;
import faculdade.mercadopago.controller.mapper.dto.request.UsuarioRequest;
import faculdade.mercadopago.controller.mapper.dto.response.UsuarioResponse;
import faculdade.mercadopago.entity.Usuario;
import faculdade.mercadopago.mocks.MockGenerator;
import faculdade.mercadopago.usecase.IUsuarioUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UsuarioControllerTest {

    private IUsuarioUseCase usuarioUseCase;
    private UsuarioMapper usuarioMapper;
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        usuarioUseCase = mock(IUsuarioUseCase.class);
        usuarioMapper = mock(UsuarioMapper.class);
        usuarioController = new UsuarioController(usuarioUseCase, usuarioMapper);
    }

    @Test
    void deveBuscarUsuarioPorCpfComSucesso() {

        String cpf = "12345678900";
        Usuario usuario = MockGenerator.generateUsuarioMock();
        UsuarioResponse expectedResponse = MockGenerator.generateUsuarioResponseMock();

        when(usuarioUseCase.buscarUsuarioPorCpf(anyString())).thenReturn(usuario);
        when(usuarioMapper.toResponse(any(Usuario.class))).thenReturn(expectedResponse);


        UsuarioResponse result = usuarioController.buscarUsuarioPorCpf(usuario.cpf());

        assertNotNull(result);
        assertEquals(expectedResponse, result);
        verify(usuarioUseCase).buscarUsuarioPorCpf(usuario.cpf());
        verify(usuarioMapper).toResponse(usuario);
    }

    @Test
    void deveProcessarUsuarioComSucesso() {

        UsuarioRequest request = MockGenerator.generateUsuarioResquestIdentificadoTrueMock();
        Usuario usuario = MockGenerator.generateUsuarioMock();
        UsuarioResponse expectedResponse = MockGenerator.generateUsuarioResponseMock();

        when(usuarioUseCase.processarUsuario(any(UsuarioRequest.class))).thenReturn(usuario);
        when(usuarioMapper.toResponse(any(Usuario.class))).thenReturn(expectedResponse);

        UsuarioResponse result = usuarioController.processarUsuario(request);


        assertNotNull(result);
        assertEquals(expectedResponse, result);
        verify(usuarioUseCase).processarUsuario(request);
        verify(usuarioMapper).toResponse(usuario);
    }
}
