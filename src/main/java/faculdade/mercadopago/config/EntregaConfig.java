package faculdade.mercadopago.config;

import faculdade.mercadopago.api.controller.EntregaAPIController;
import faculdade.mercadopago.controller.EntregaController;
import faculdade.mercadopago.controller.mapper.EntregaMapper;
import faculdade.mercadopago.gateway.IEntregaGateway;
import faculdade.mercadopago.gateway.impl.EntregaGateway;
import faculdade.mercadopago.gateway.persistence.jpa.EntregaRepository;
import faculdade.mercadopago.usecase.IEntregaUseCase;
import faculdade.mercadopago.usecase.IFilaPedidosPreparacaoUseCase;
import faculdade.mercadopago.usecase.IPedidoUseCase;
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
    EntregaController entregaController(IEntregaUseCase entregaUseCase){
        return new EntregaController(entregaUseCase);
    }

    @Bean
    EntregaMapper entregaMapper(){
        return new EntregaMapper();
    }

    @Bean
    EntregaUseCase entregaUseCase(IPedidoUseCase pedidoUseCase, IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase, IEntregaGateway entregaGateway){
        return new EntregaUseCase(pedidoUseCase, filaPedidosPreparacaoUseCase, entregaGateway);
    }

    @Bean
    EntregaGateway entregaGateway(EntregaRepository entregaRepository){
        return new EntregaGateway(entregaRepository);
    }
}
