# 📦 Tech Challenge - Fase 1

Essa aplicação foi construida para entrega do Tech Challenge da fase 1. 

Integrantes grupo:

- Andrew do Prado Soares
- Edmundo Alves Franco Junior
- Jose Augusto - RM 361650
- Nathalia Matielo - RM363100

A aplicação contempla a construção de APIs que juntas irão fornecer um sistema de gerenciamento para uma lanchonete.

O sistema possui rotas disponiveis para:

- Cadastro e gerenciamento de usuários
- Cadastro, atualização e exclusão de produtos
- Inclusão de pedidos
- Gestão de pagamentos
- Gerenciamento de status e entrega do pedido

---


## 📚 DDD

Conheça o DDD do nosso projeto no link: https://miro.com/app/board/uXjVI9DOubQ=/

---

## 🎥 Vídeo do Projeto

Veja a execução do nosso projeto no link: https://youtu.be/-9GxpBW_uAE

---

## ✅ Tecnologias Utilizadas

- Java 17  
- Spring Boot 


---

## 🚀 Como Executar Localmente

- Baixar e instalar JDK 17
- Baixar e instalar Maven

```bash
git clone https://github.com/JoseAugustoDosSantos/mercadopago-fiap-tc.git

```

Acesse a documentação Swagger:
```
http://localhost:8080/swagger-ui/index.html
```


Em caso de erro no cadastro de produtos verifique se as categorias foram incluídas no banco. Caso não retorne registros execute a inserção manualmente: 
```
INSERT INTO `categorias` (`CODIGO`, `NOME`) VALUES
	(1, 'LANCHE'),
	(2, 'ACOMPANHAMENTO'),
	(3, 'BEBIDA'),
	(4, 'SOBREMESA');
```

---

## 📚 Endpoints e Exemplos

### 👤 Usuário


#### 🔍 Buscar usuário por CPF

**GET** `/usuario?cpf=12345678900`

**Resposta:**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "nome": "João Silva",
    "cpf": "12345678900"
  }
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
  "success": true,
  "data": {
    "id": 2,
    "nome": "Maria Oliveira",
    "cpf": "98765432100",
    "email": "mariaoliveira@gmail.com"
  }
}
```

---

### 📦 Produto

#### 🔍 Buscar produto por código

**GET** `/api/produtos/buscar/produto/1`

**Resposta:**
```json
{
  "success": true,
  "data": {
    "codigo": 1,
    "nome": "X-Burger",
    "descricao": "Hambúrguer artesanal",
    "categoria": 10,
    "preco": 19.90,
    "tempopreparo": "00:15:00"
  }
}
```

#### ➕ Cadastrar novo produto

**POST** `/api/produtos`

**Body:**
```json
{
  "nome": "Coca-Cola",
  "descricao": "Refrigerante 350ml",
  "categoria": 3,
  "preco": 5.00,
  "tempopreparo": "00:01:00"
}
```

**Resposta:**
```json
{
  "success": true,
  "data": {
    "codigo": 2,
    "nome": "Coca-Cola",
    "descricao": "Refrigerante 350ml",
    "categoria": 3,
    "preco": 5.00,
    "tempopreparo": "00:01:00"
  }
}
```

---

### 🧾 Pedido

#### 📄 Listar pedidos por status

**GET** `/pedido?status=RECEBIDO`

**Resposta:**
```json
{
  "data": [
    {
      "pedido": 1,
      "usuario": 1,
      "status": "EM_PREPARACAO",
      "valorTotal": 10.00,
      "dataHoraSolicitacao": "2025-06-03T03:36:23.414949",
      "tempoTotalPreparo": "00:02:00"
    }
  ],
  "errors": [],
  "success": true
}
```

#### 🔄 Alterar status do pedido

**PUT** `/pedido/1`

**Body:**
```json
{
  "codigo":  1,
  "status": "EM_PREPARACAO"
}
```

**Resposta:**
```json
{
  "data": {
    "pedido": 1,
    "usuario": 1,
    "status": "EM_PREPARACAO",
    "valorTotal": 10.00,
    "dataHoraSolicitacao": "2025-06-03T03:36:23.414949",
    "tempoTotalPreparo": "00:02:00"
  },
  "errors": [],
  "success": true
}
```

#### ❌ Remover pedido da fila de preparo

**DELETE** `/pedido/1`

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
  "codigo":  1,
  "status": "FINALIZADO"
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

- Todas as respostas seguem o padrão de `ApiResponse<T>`.
- A API não possui autenticação implementada.
- Configure o banco de dados no `application.properties`.
