package faculdade.mercadopago.config;

import faculdade.mercadopago.api.controller.PagamentoAPIController;
import faculdade.mercadopago.controller.PagamentoController;
import faculdade.mercadopago.controller.mapper.PagamentoMapper;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.gateway.impl.PagamentoGateway;
import faculdade.mercadopago.gateway.persistence.jpa.PagamentoRepository;
import faculdade.mercadopago.gateway.persistence.jpa.PedidoRepository;
import faculdade.mercadopago.usecase.IPagamentoUseCase;
import faculdade.mercadopago.usecase.impl.PagamentoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PagamentoConfig {
    @Bean
    PagamentoAPIController pagamentoAPIController(PagamentoController pagamentoController){
        return new PagamentoAPIController(pagamentoController);
    }

    @Bean
    PagamentoController pagamentoController(IPagamentoUseCase pagamentoUseCase){
        return new PagamentoController(pagamentoUseCase);
    }

    @Bean
    PagamentoUseCase pagamentoUseCase(IPagamentoGateway pagamentoGateway){
        return new PagamentoUseCase(pagamentoGateway);
    }

    @Bean
    PagamentoGateway pagamentoGateway(PagamentoRepository pagamentoRepository, PedidoRepository pedidoRepository){
        return new PagamentoGateway(pagamentoRepository,pedidoRepository, new RestTemplate());
    }

    @Bean
    PagamentoMapper pagamentoMapper(){
        return new PagamentoMapper();
    }
}