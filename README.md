# 📦 Tech Challenge - Fase 2

---

Essa aplicação foi construída para entrega do Tech Challenge da fase 2, cujo objetivo principal foi a refatoração do código já existente da arquitetura hexagonal para o padrão Clean Architecture, além da implementação da estrutura Kubernetes para orquestração e escalabilidade.

## Integrantes do grupo:

- Iago Cavalcante Geraldo- RM 362832
- Jose Augusto dos Santos- RM 361650
- Nathalia Matielo Rodrigues- RM 363100
- Rogerio Inacio Silva Junior- RM 364104
- Vanessa Moreira Wendling - RM 362741

---

## 💡 Solução Proposta

Foi desenvolvido um sistema de autoatendimento para fast food, que:

- Permite que o cliente faça pedidos diretamente via interface, podendo se identificar por CPF, cadastrar-se ou permanecer anônimo.


- O cliente pode montar o combo em etapas opcionais: Lanche, Acompanhamento e Bebida, com exibição clara de nome, descrição e preço.


- Possui integração com Mercado Pago via QRCode para pagamento.


- Exibe para o cliente um monitor de acompanhamento do pedido, com status atualizados em tempo real: Recebido, Em preparação, Pronto e Finalizado.


- Notifica o cliente quando o pedido estiver pronto para retirada.


- Permite ao estabelecimento gerenciar clientes, produtos e acompanhar os pedidos em andamento.



---

## 📦 Funcionalidades Entregues na Fase 2

- Refatoração do código para Clean Architecture.


- APIs REST para:
    - Cadastro e identificação de usuário
    - Realização de pedido
    - Integração com Mercado Pago para geração do QRCode
    - Webhook para confirmação de pagamento via Mercado Pago
    - Listagem de pedidos filtrados e ordenados por status e tempo
    - Controle de pedidos entregues
    - Gerenciamento de clientes, produtos e pedidos


- Implantação em Kubernetes:
    - Escalabilidade automática (Horizontal Pod Autoscaler).
    - Uso de ConfigMaps e Secrets para valores sensíveis.
    - Boas práticas com Deployments e Services para orquestração e exposição.

---


##  Arquitetura

![diagrama.png](diagrama.png)

### Requisitos contemplados

- Escalabilidade e alta disponibilidade com Kubernetes.
- Segurança e gerenciamento de configuração via Secrets e ConfigMaps.
- Visibilidade e controle total via painel administrativo.

---


## 📚 DDD

Conheça o DDD do nosso projeto no link: https://miro.com/app/board/uXjVI9DOubQ=/

---

## 🎥 Vídeo Demonstrativo

Assista ao vídeo com demonstração do funcionamento da aplicação e da arquitetura:


---

## ⚙️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Kubernetes local (Minikube ou Docker Desktop)
- Mercado Pago (integração de pagamento via QRCode)
- MariaDB (Banco de dados)

---

## 🚀 Como Executar Localmente

1. Instale JDK 17 e Maven.
2. Clone o repositório:
    ```bash
    git clone https://github.com/JoseAugustoDosSantos/mercadopago-fiap-tc-fase-2.git
    cd mercadopago-fiap-tc-fase-2
    ```
3. Configure o banco de dados e as categorias (caso não existam) no MariaDB:
    ```sql
    INSERT INTO categorias (CODIGO, NOME) VALUES
      (1, 'LANCHE'),
      (2, 'ACOMPANHAMENTO'),
      (3, 'BEBIDA'),
      (4, 'SOBREMESA');
    ```
4. Execute a aplicação via Maven:
    ```bash
    mvn spring-boot:run
    ```
5. Acesse a documentação Swagger:
    ```
    http://localhost:8080/swagger-ui/index.html
    ```
6.  Para execução via Kubernetes:
    - Se estiver usando **Minikube** habilite o metrics-server (necessário para HPA funcionar):
    ```bash
    minikube addons enable metrics-server
    ```
    - Aplique os manifetos YAML:
    ```bash
    kubectl apply -f k8s/
    ```
    - **Se estiver usando Minikube:**
    ```bash
    minikube service lanchonete-service
    ```

    Esse comando deve abrir automaticamente uma aba no navegador com a URL.  
    Acesse `.../swagger-ui/index.html` no final da URL para ver a documentação dos endpoints.

    - **Se estiver usando Docker Desktop:**

    Acesse diretamente no navegador:

    - http://localhost:30000/
    - http://localhost:30000/swagger-ui/index.html

    Neles você poderá visualizar a documentação interativa (OpenAPI/Swagger) dos endpoints disponíveis.
   
    - Endpoints para Health Checks:
      - Liveness Probe:
      ```bash
      /actuator/health/liveness
      ```
      - Readiness Probe:
      ```bash
      /actuator/health/readiness
      ```

---

## 📚 Endpoints e Exemplos

### 👤 Usuário


#### 🔍 Buscar usuário por CPF

**GET** `/usuario?cpf=12345678900`

**Resposta:**
```json
{
  "id": 1,
  "nome": "Maria",
  "cpf": "12345678901",
  "email": "teste@teste.com"
} 
```


#### ➕ Criar novo usuário

**POST** `/usuario`

**Body:**
```json
{
  "identificar_usuario": true,
  "nome": "Maria Oliveira",
  "cpf": "98765432100",
  "email": "mariaoliveira@gmail.com"
}
```

**Resposta:**
```json
{
  "id": 1,
  "nome": "Maria",
  "cpf": "12345678901",
  "email": "teste@teste.com"
}
```

---

### 📦 Produto

#### ➕ Cadastrar novo produto

**POST** `/api/produtos`

**Body:**
```json
{
  "nome": "Coca-Cola",
  "descricao": "Refrigerante 350ml",
  "categoriaId": 3,
  "preco": 5.00,
  "tempopreparo": "00:01:00"
}
```

**Resposta:**
```json
{
  "id": 9,
  "nome": "X-Burguer Bão Demais",
  "descricao": "Pão, hambúrguer, queijo e molho especial",
  "categoria": {
    "id": 1,
    "nome": null
  },
  "preco": 19.99,
  "tempopreparo": "00:15:00"
}
```


#### 🔍 Buscar produto por código

**GET** `/api/produtos/buscar/produto/1`

**Resposta:**
```json
{
  "id": 1,
  "nome": "X-Burguer",
  "descricao": "Pão, hambúrguer, queijo e molho especial",
  "categoria": {
    "id": 1,
    "nome": "LANCHE"
  },
  "preco": 15.90,
  "tempopreparo": "00:10:00"
}
```

#### 🔍 Buscar produto por categoria

**GET** `/api/produtos/buscar/categoria/:codigoCategoria`

**Resposta:**
```json
{
  "id": 1,
  "nome": "LANCHE"
}
```

#### 🔍 Buscar lista de produto por categoria

**GET** `/api/produtos/buscar/categoria/:codigoCategoria/produtos`

**Resposta:**
```json
[
  {
    "id": 1,
    "nome": "X-Burguer",
    "descricao": "Pão, hambúrguer, queijo e molho especial",
    "categoria": {
      "id": 1,
      "nome": "LANCHE"
    },
    "preco": 15.90,
    "tempopreparo": "00:10:00"
  },
  {
    "id": 2,
    "nome": "X-Salada",
    "descricao": "Pão, hambúrguer, queijo, alface e tomate",
    "categoria": {
      "id": 1,
      "nome": "LANCHE"
    },
    "preco": 16.90,
    "tempopreparo": "00:12:00"
  },
  {
    "id": 9,
    "nome": "X-Burguer Bão Demais",
    "descricao": "Pão, hambúrguer, queijo e molho especial",
    "categoria": {
      "id": 1,
      "nome": "LANCHE"
    },
    "preco": 19.99,
    "tempopreparo": "00:15:00"
  },
  {
    "id": 10,
    "nome": "X-Burguer Bão",
    "descricao": "Pão, hambúrguer, queijo e molho especial",
    "categoria": {
      "id": 1,
      "nome": "LANCHE"
    },
    "preco": 19.99,
    "tempopreparo": "00:15:00"
  }
]
```

#### Atualizar informações produtos

**PUT** `/api/produtos/:codigo`

**Body:**
```json
{
  "nome": "X-Burguer Bão",
  "descricao": "Pão, hambúrguer, queijo e molho especial",
  "categoriaId": 1,
  "preco": 19.99,
  "tempopreparo": "00:15:00"
}
```

**Resposta:**
```json
{
  "id": 10,
  "nome": "X-Burguer Bão",
  "descricao": "Pão, hambúrguer, queijo e molho especial",
  "categoria": {
    "id": 1,
    "nome": null
  },
  "preco": 19.99,
  "tempopreparo": "00:15:00"
}
```

#### ❌ Remover pedido da fila de preparo

**DELETE** `/api/produtos/:codigo`

**Resposta:** `204 No Content`


---

### 🧾 Pedido

#### ➕ Cadastrar novo pedido

**POST** `/api/pedido`

**Body:**
```json
{
  "idUsuario": 1,
  "itens": [
    {
      "produtoId": 1,
      "quantidade": 2
    }
  ]
}
```

**Resposta:**
```json
{
  "id": 2,
  "idUsuario": 1,
  "status": "RECEBIDO",
  "valorTotal": 31.80,
  "dataHoraSolicitacao": "2025-08-03T17:26:30.8292112",
  "tempoTotalPreparo": "00:20:00",
  "itens": [
    {
      "id": 2,
      "pedidoId": 2,
      "produtoId": 1,
      "quantidade": 2,
      "precoUnitario": 15.90,
      "precoTotal": 31.80
    }
  ]
}
```

#### 📄 Buscar pedidos por status

**GET** `/api/pedido?status=RECEBIDO`

**Resposta:**
```json
[
  {
    "id": 2,
    "idUsuario": 1,
    "status": "RECEBIDO",
    "valorTotal": 31.80,
    "dataHoraSolicitacao": "2025-08-03T17:26:30",
    "tempoTotalPreparo": "00:20:00",
    "itens": [
      {
        "id": 2,
        "pedidoId": 2,
        "produtoId": 1,
        "quantidade": 2,
        "precoUnitario": 15.90,
        "precoTotal": 31.80
      }
    ]
  }
]
```

#### 📄 Buscar pedidos por codigo

**GET** `/api/pedido/buscar/:codigoPedido`

**Resposta:**
```json
{
  "id": 1,
  "idUsuario": 1,
  "status": "EM_PREPARACAO",
  "valorTotal": 31.80,
  "dataHoraSolicitacao": "2025-08-03T17:10:53",
  "tempoTotalPreparo": "00:20:00",
  "itens": [
    {
      "id": 1,
      "pedidoId": 1,
      "produtoId": 1,
      "quantidade": 2,
      "precoUnitario": 15.90,
      "precoTotal": 31.80
    }
  ]
}
```

#### 🔄 Alterar status do pedido

**PUT** `/api/pedido/status/:codigo?status=EM_PREPARACAO`

**Resposta:**
```json
{
  "id": 1,
  "idUsuario": 1,
  "status": "EM_PREPARACAO",
  "valorTotal": 31.80,
  "dataHoraSolicitacao": "2025-08-03T17:10:53",
  "tempoTotalPreparo": "00:20:00",
  "itens": [
    {
      "id": 1,
      "pedidoId": 1,
      "produtoId": 1,
      "quantidade": 2,
      "precoUnitario": 15.90,
      "precoTotal": 31.80
    }
  ]
}
```

#### ❌ Remover pedido da fila de preparo

**DELETE** `/api/pedido/remover/fila/:codigoPedido`

**Resposta:** `204 No Content`

---

### 💳 Pagamento

#### 🧾 Gerar QR Code de pagamento

**POST** `/api/payments`

**Body:**
```json
{
  "OrderId": 3,
  "TotalAmount": 10.000000,
  "Itens": [
    {
      "Codigo": 3,
      "quantidade": 2,
      "Valor": 5.000000
    }
  ]
}

```

**Resposta:**
```json
{
  "data": {
    "in_store_order_id": "a0eae50a-e0a6-4d08-8d5b-b7e4bcf79304",
    "qr_data": "00020101021243650016COM.MERCADOLIBRE020130636a0eae50a-e0a6-4d08-8d5b-b7e4bcf793045204000053039865802BR5913Andrew Soares6009SAO PAULO62070503***63040655"
  },
  "errors": [],
  "success": true
}
```

#### 🧾 Efetivar Criação do QR Code de Pagamento

**POST** `https://api.mercadopago.com/v1/payments`

**Body:**
```json
{
  "transaction_amount": 10,
  "payment_method_id": "pix",
  "description": "Compra de teste QR",
  "external_reference": "3",
  "installments": 1,
  "payer": {
    "first_name": "Teste",
    "last_name": "User",
    "email": "email@gmail.com"
  }
}

```

**Resposta:**
```json
{
  "id": 1323573924,
  "date_created": "2025-06-02T23:49:17.258-04:00"
} 
```

#### ✅ Confirmar pagamento

**POST** `/api/payments/mercadopago/confirmapagamento`

**Body:**
```json
{
  "id": "1323573924",
  "live_mode": false,
  "type": "payment",
  "date_created": "2025-05-22T12:54:53Z",
  "user_id": 17679366,
  "api_version": "v1",
  "action": "payment.created",
  "data": {
    "id": "1323573924"
  }
}
```

**Resposta:**
```json
{
  "data": null,
  "errors": [],
  "success": true
}
```

---

### 🛵 Entrega

#### 🚚 Finalizar pedido

**POST** `/entregar`

**Body:**
```json
{
  "pedidoId": 1,
  "dataHoraSolicitacao": "2025-08-02T12:54:53Z"
}
```

**Resposta:**
```json
{
  "data": {
    "codigo": 1,
    "status": "FINALIZADO",
    "dataHoraEntrega": "2025-06-03T04:33:39.72109629"
  },
  "errors": [],
  "success": true
}
```

---


## 📌 Observações Finais

- A API não possui autenticação implementada.
