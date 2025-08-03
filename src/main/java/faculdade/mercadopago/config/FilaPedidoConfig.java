package faculdade.mercadopago.config;

import faculdade.mercadopago.gateway.IFilaPedidosPreparacaoGateway;
import faculdade.mercadopago.gateway.impl.FilaPedidosPreparacaoGateway;
import faculdade.mercadopago.gateway.persistence.jpa.FilaPedidosPreparacaoRepository;
import faculdade.mercadopago.usecase.impl.FilaPedidosPreparacaoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilaPedidoConfig {

    @Bean
    IFilaPedidosPreparacaoGateway filaPedidoGateway(FilaPedidosPreparacaoRepository filaPedidosPreparacaoRepository) {
        return new FilaPedidosPreparacaoGateway(filaPedidosPreparacaoRepository);
    }

    @Bean
    FilaPedidosPreparacaoUseCase filaPedidoUserCase(IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway){
        return new FilaPedidosPreparacaoUseCase(filaPedidosPreparacaoGateway);
    }
}