package faculdade.mercadopago.config;

import faculdade.mercadopago.api.controller.PedidoAPIController;
import faculdade.mercadopago.controller.PedidoController;
import faculdade.mercadopago.controller.mapper.PedidoItemMapper;
import faculdade.mercadopago.controller.mapper.PedidoMapper;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.gateway.impl.FilaPedidosPreparacaoGateway;
import faculdade.mercadopago.gateway.impl.PedidoGateway;
import faculdade.mercadopago.gateway.persistence.jpa.FilaPedidosPreparacaoRepository;
import faculdade.mercadopago.gateway.persistence.jpa.PedidoRepository;
import faculdade.mercadopago.usecase.IFilaPedidosPreparacaoUseCase;
import faculdade.mercadopago.usecase.IPedidoUseCase;
import faculdade.mercadopago.usecase.IProdutoUseCase;
import faculdade.mercadopago.usecase.IUsuarioUseCase;
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
    PedidoController pedidoController(IPedidoUseCase pedidoUseCase, PedidoMapper pedidoMapper){
        return new PedidoController(pedidoUseCase, pedidoMapper);
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
    PedidoUseCase pedidoUseCase(IPedidoGateway pedidoGateway, IUsuarioUseCase usuarioUseCase, IProdutoUseCase produtoUseCase, IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase){
        return new PedidoUseCase(pedidoGateway, usuarioUseCase, produtoUseCase, filaPedidosPreparacaoUseCase);
    }

    @Bean
    PedidoGateway pedidoGateway(PedidoRepository pedidoRepository){
        return new PedidoGateway(pedidoRepository);
    }

    @Bean
    FilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway(FilaPedidosPreparacaoRepository filaPedidosPreparacaoRepository){
        return new FilaPedidosPreparacaoGateway(filaPedidosPreparacaoRepository);
    }
}
