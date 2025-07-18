package faculdade.mercadopago.config;

import faculdade.mercadopago.api.controller.PedidoAPIController;
import faculdade.mercadopago.controller.PedidoController;
import faculdade.mercadopago.controller.mapper.PedidoItemMapper;
import faculdade.mercadopago.controller.mapper.PedidoMapper;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.gateway.impl.PedidoGateway;
import faculdade.mercadopago.gateway.persistence.jpa.PedidoRepository;
import faculdade.mercadopago.usecase.IPedidoUseCase;
import faculdade.mercadopago.usecase.impl.PedidoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoConfig {

    @Bean
    PedidoAPIController pedidoAPIController(PedidoController pedidoController){
        return new PedidoAPIController(pedidoController);
    }

    @Bean
    PedidoController pedidoController(IPedidoUseCase pedidoUseCase, IPedidoGateway pedidoGateway, PedidoMapper pedidoMapper){
        return new PedidoController(pedidoUseCase, pedidoGateway, pedidoMapper);
    }

    @Bean
    PedidoMapper pedidoMapper(){
        return new PedidoMapper();
    }

    @Bean
    PedidoItemMapper pedidoItemMapper(){
        return new PedidoItemMapper();
    }

    @Bean
    PedidoUseCase pedidoUseCase(){
        return new PedidoUseCase();
    }

    @Bean
    PedidoGateway pedidoGateway(PedidoRepository pedidoRepository){
        return new PedidoGateway(pedidoRepository);
    }
}
