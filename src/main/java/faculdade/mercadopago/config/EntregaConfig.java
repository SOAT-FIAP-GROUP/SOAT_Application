package faculdade.mercadopago.config;

import faculdade.mercadopago.api.controller.EntregaAPIController;
import faculdade.mercadopago.controller.EntregaController;
import faculdade.mercadopago.controller.mapper.EntregaMapper;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.gateway.IFilaPedidosPreparacaoGateway;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.gateway.impl.EntregaGateway;
import faculdade.mercadopago.gateway.persistence.jpa.EntregaRepository;
import faculdade.mercadopago.usecase.IEntregaUseCase;
import faculdade.mercadopago.usecase.impl.EntregaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntregaConfig {

    @Bean
    EntregaAPIController entregaAPIController(EntregaController entregaController) {
        return new EntregaAPIController(entregaController);
    }

    @Bean
    EntregaController entregaController(IEntregaUseCase entregaUseCase, IEntregaGateway entregaGateway, IPedidoGateway pedidoGateway, IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway){
        return new EntregaController(entregaUseCase, entregaGateway, pedidoGateway, filaPedidosPreparacaoGateway);
    }

    @Bean
    EntregaMapper entregaMapper(){
        return new EntregaMapper();
    }

    @Bean
    EntregaUseCase entregaUseCase(){
        return new EntregaUseCase();
    }

    @Bean
    EntregaGateway entregaGateway(EntregaRepository entregaRepository){
        return new EntregaGateway(entregaRepository);
    }
}
