package faculdade.mercadopago.config;

import faculdade.mercadopago.api.controller.ProdutoAPIController;
import faculdade.mercadopago.controller.ProdutoController;
import faculdade.mercadopago.controller.mapper.CategoriaMapper;
import faculdade.mercadopago.controller.mapper.ProdutoMapper;
import faculdade.mercadopago.gateway.ICategoriaGateway;
import faculdade.mercadopago.gateway.IProdutoGateway;
import faculdade.mercadopago.gateway.impl.CategoriaGateway;
import faculdade.mercadopago.gateway.impl.ProdutoGateway;
import faculdade.mercadopago.gateway.persistence.jpa.CategoriaRepository;
import faculdade.mercadopago.gateway.persistence.jpa.ProdutoRepository;
import faculdade.mercadopago.usercase.IProdutoUseCase;
import faculdade.mercadopago.usercase.impl.ProdutoUserCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoConfig {

    @Bean
    ProdutoGateway produtoGateway(ProdutoRepository produtoRepository) {
        return new ProdutoGateway(produtoRepository);
    }

    @Bean
    ProdutoController produtoController(IProdutoUseCase produtoUseCase, IProdutoGateway produtoGateway,
                                        ProdutoMapper produtoMapper, ICategoriaGateway categoriaGateway,
                                        CategoriaMapper categoriaMapper) {
        return new ProdutoController(produtoUseCase, produtoGateway, produtoMapper, categoriaGateway,
                categoriaMapper);
    }

    @Bean
    ProdutoAPIController produtoAPIController(ProdutoController produtoController) {
        return new ProdutoAPIController(produtoController);
    }

    @Bean
    ProdutoMapper produtoMapper(){
        return new ProdutoMapper();
    }

    @Bean
    CategoriaMapper categoriaMapper(){
        return new CategoriaMapper();
    }

    @Bean
    ProdutoUserCase produtoUserCase(){
        return new ProdutoUserCase();
    }

    @Bean
    CategoriaGateway categoriaGateway(CategoriaRepository categoriaRepository) {
        return new CategoriaGateway(categoriaRepository);
    }

}
