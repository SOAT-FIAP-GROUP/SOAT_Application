package faculdade.mercadopago.config;

import faculdade.mercadopago.api.controller.UsuarioAPIController;
import faculdade.mercadopago.controller.UsuarioController;
import faculdade.mercadopago.controller.mapper.UsuarioMapper;
import faculdade.mercadopago.gateway.IUsuarioGateway;
import faculdade.mercadopago.gateway.impl.UsuarioGateway;
import faculdade.mercadopago.gateway.persistence.jpa.UsuarioRepository;
import faculdade.mercadopago.usecase.IUsuarioUseCase;
import faculdade.mercadopago.usecase.impl.UsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfig {

    @Bean
    UsuarioUseCase usuarioUseCase(){
        return new UsuarioUseCase();
    }

    @Bean
    UsuarioGateway usuarioGateway(UsuarioRepository usuarioRepository){
        return new UsuarioGateway(usuarioRepository);
    }

    @Bean
    UsuarioController usuarioController(IUsuarioUseCase usuarioUseCase, IUsuarioGateway usuarioGateway, UsuarioMapper usuarioMapper){
        return new UsuarioController(usuarioUseCase, usuarioGateway, usuarioMapper);
    }

    @Bean
    UsuarioAPIController usuarioAPIController(UsuarioController usuarioController){
        return new UsuarioAPIController(usuarioController);
    }

    @Bean
    UsuarioMapper usuarioMapper(){
        return new UsuarioMapper();
    }


}
