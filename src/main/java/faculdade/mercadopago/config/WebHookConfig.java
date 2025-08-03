package faculdade.mercadopago.config;

import faculdade.mercadopago.api.controller.WebHookAPIController;
import faculdade.mercadopago.controller.WebHookController;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.usecase.IFilaPedidosPreparacaoUseCase;
import faculdade.mercadopago.usecase.IPagamentoUseCase;
import faculdade.mercadopago.usecase.IPedidoUseCase;
import faculdade.mercadopago.usecase.IWebHookUseCase;
import faculdade.mercadopago.usecase.impl.WebHookUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebHookConfig {
    @Bean
    WebHookAPIController webHookAPIController(WebHookController webHookController) {
        return new WebHookAPIController(webHookController);
    }

    @Bean
    WebHookController webHookController(IWebHookUseCase webHookUseCase, IPagamentoGateway pagamentoGateway) {
        return new WebHookController(webHookUseCase, pagamentoGateway);
    }

    @Bean
    WebHookUseCase webHookUseCase(IPedidoUseCase pedidoUseCase, IPagamentoUseCase pagamentoUseCase, IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase) {
        return new WebHookUseCase(pedidoUseCase, pagamentoUseCase, filaPedidosPreparacaoUseCase);
    }
}