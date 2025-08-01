package faculdade.mercadopago.config;

import faculdade.mercadopago.api.controller.PedidoAPIController;
import faculdade.mercadopago.controller.PedidoController;
import faculdade.mercadopago.controller.ProdutoController;
import faculdade.mercadopago.controller.UsuarioController;
import faculdade.mercadopago.controller.mapper.PedidoItemMapper;
import faculdade.mercadopago.controller.mapper.PedidoMapper;
import faculdade.mercadopago.gateway.IFilaPedidosPreparacaoGateway;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.gateway.IProdutoGateway;
import faculdade.mercadopago.gateway.IUsuarioGateway;
import faculdade.mercadopago.gateway.impl.FilaPedidosPreparacaoGateway;
import faculdade.mercadopago.gateway.impl.PedidoGateway;
import faculdade.mercadopago.gateway.persistence.jpa.FilaPedidosPreparacaoRepository;
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
    PedidoController pedidoController(IPedidoUseCase pedidoUseCase, PedidoMapper pedidoMapper, IPedidoGateway pedidoGateway, IProdutoGateway produtoGateway, IUsuarioGateway usuarioGateway, IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway){
        return new PedidoController(pedidoUseCase, pedidoMapper, pedidoGateway, produtoGateway, usuarioGateway, filaPedidosPreparacaoGateway);
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

    @Bean
    FilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway(FilaPedidosPreparacaoRepository filaPedidosPreparacaoRepository){
        return new FilaPedidosPreparacaoGateway(filaPedidosPreparacaoRepository);
    }
}
