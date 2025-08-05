package faculdade.mercadopago.usecase;

import faculdade.mercadopago.controller.mapper.dto.request.UsuarioRequest;
import faculdade.mercadopago.entity.Usuario;
import faculdade.mercadopago.exception.BusinessException;
import faculdade.mercadopago.exception.EntityNotFoundException;
import faculdade.mercadopago.gateway.IUsuarioGateway;
import faculdade.mercadopago.mocks.MockGenerator;
import faculdade.mercadopago.usecase.impl.UsuarioUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UsuarioUseCaseTest {

    private IUsuarioGateway gateway;
    private UsuarioUseCase useCase;

    private static final Long ID = 1L;

    @BeforeEach
    void setup() {
        gateway = mock(IUsuarioGateway.class);
        useCase = new UsuarioUseCase(gateway);
    }

    @Test
    void buscarUsuarioPorCpf_DeveRetornarUsuario_QuandoEncontrado() {
        Usuario usuario = MockGenerator.generateUsuarioMock();
        when(gateway.buscarUsuarioCpf(anyString())).thenReturn(Optional.of(usuario));

        Usuario resultado = useCase.buscarUsuarioPorCpf(usuario.cpf());

        assertEquals(usuario, resultado);
    }

    @Test
    void buscarUsuarioPorCpf_DeveLancarExcecao_QuandoNaoEncontrado() {
        when(gateway.buscarUsuarioCpf(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> useCase.buscarUsuarioPorCpf("12345678910"));
    }

    @Test
    void processarUsuario_DeveRetornarUsuarioSalvo_QuandoNovoUsuarioIdentificado() throws BusinessException {
        UsuarioRequest request = MockGenerator.generateUsuarioResquestIdentificadoTrueMock();

        Usuario usuarioSalvo = MockGenerator.generateUsuarioMock();

        when(gateway.buscarUsuarioCpf(anyString())).thenReturn(Optional.empty());
        when(gateway.save(any(Usuario.class))).thenReturn(usuarioSalvo);

        Usuario resultado = useCase.processarUsuario(request);

        assertEquals(usuarioSalvo, resultado);
    }

    @Test
    void processarUsuario_DeveLancarBusinessException_QuandoUsuarioJaExistente() {
        UsuarioRequest request = MockGenerator.generateUsuarioResquestIdentificadoTrueMock();
        Usuario usuarioSalvo = MockGenerator.generateUsuarioMock();
        when(gateway.buscarUsuarioCpf(anyString())).thenReturn(Optional.of(usuarioSalvo));

        assertThrows(BusinessException.class, () -> useCase.processarUsuario(request));
    }

    @Test
    void processarUsuario_DeveRetornarUsuarioPadrao_QuandoUsuarioNaoIdentificadoEPadraoJaExiste() throws BusinessException {
        Usuario padrao = MockGenerator.generateUsuarioPadraoMock();
        UsuarioRequest request = MockGenerator.generateUsuarioResquestIdentificadoFalseMock();

        when(gateway.buscarUsuarioCpf(anyString())).thenReturn(Optional.of(padrao));

        Usuario resultado = useCase.processarUsuario(request);

        assertEquals(padrao, resultado);
    }

    @Test
    void processarUsuario_DeveCriarEDevolverUsuarioPadrao_QuandoNaoExistente() throws BusinessException {
        UsuarioRequest request = MockGenerator.generateUsuarioResquestIdentificadoFalseMock();
        Usuario usuarioPadrao = MockGenerator.generateUsuarioPadraoMock();

        when(gateway.buscarUsuarioCpf(anyString())).thenReturn(Optional.empty());
        when(gateway.save(any(Usuario.class))).thenReturn(usuarioPadrao);

        Usuario resultado = useCase.processarUsuario(request);

        assertEquals(usuarioPadrao, resultado);
    }

    @Test
    void buscaUsuarioPorId_DeveRetornarUsuario_QuandoEncontrado() {
        Usuario usuario = MockGenerator.generateUsuarioMock();
        when(gateway.findById(anyLong())).thenReturn(Optional.of(usuario));

        Usuario resultado = useCase.buscaUsuarioPorId(ID);

        assertEquals(usuario, resultado);
    }

    @Test
    void buscaUsuarioPorId_DeveLancarExcecao_QuandoNaoEncontrado() {
        when(gateway.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> useCase.buscaUsuarioPorId(ID));
    }
}

