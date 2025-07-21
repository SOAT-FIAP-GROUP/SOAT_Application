package faculdade.mercadopago.config;

import faculdade.mercadopago.api.controller.PagamentoAPIController;
import faculdade.mercadopago.controller.PagamentoController;
import faculdade.mercadopago.controller.mapper.PagamentoMapper;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.gateway.impl.PagamentoGateway;
import faculdade.mercadopago.gateway.persistence.jpa.PagamentoRepository;
import faculdade.mercadopago.usecase.IPagamentoUseCase;
import faculdade.mercadopago.usecase.impl.PagamentoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoConfig {
    @Bean
    PagamentoAPIController pagamentoAPIController(PagamentoController pagamentoController){
        return new PagamentoAPIController(pagamentoController);
    }

    @Bean
    PagamentoController pagamentoController(IPagamentoUseCase pagamentoUseCase, IPagamentoGateway pagamentoGateway){
        return new PagamentoController(pagamentoUseCase, pagamentoGateway);
    }

    @Bean
    PagamentoUseCase pagamentoUseCase(){
        return new PagamentoUseCase();
    }

    @Bean
    PagamentoGateway pagamentoGateway(PagamentoRepository pagamentoRepository){
        return new PagamentoGateway(pagamentoRepository);
    }

    @Bean
    PagamentoMapper pagamentoMapper(){
        return new PagamentoMapper();
    }
}