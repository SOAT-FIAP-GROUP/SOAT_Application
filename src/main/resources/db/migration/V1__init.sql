CREATE TABLE categorias
(
    codigo BIGINT AUTO_INCREMENT NOT NULL,
    nome   VARCHAR(255) NULL,
    CONSTRAINT pk_categorias PRIMARY KEY (codigo)
);

CREATE TABLE entregas
(
    codigo          BIGINT AUTO_INCREMENT NOT NULL,
    pedidocodigo    BIGINT NULL,
    datahoraentrega datetime NULL,
    CONSTRAINT pk_entregas PRIMARY KEY (codigo)
);

CREATE TABLE filapedidospreparacao
(
    codigo       BIGINT AUTO_INCREMENT NOT NULL,
    pedidocodigo BIGINT NULL,
    CONSTRAINT pk_filapedidospreparacao PRIMARY KEY (codigo)
);

CREATE TABLE pagamentos
(
    codigo            BIGINT AUTO_INCREMENT NOT NULL,
    pedidocodigo      BIGINT NULL,
    valorpago         DECIMAL NULL,
    status            VARCHAR(255) NULL,
    datahorapagamento datetime NULL,
    CONSTRAINT pk_pagamentos PRIMARY KEY (codigo)
);

CREATE TABLE pedidoitem
(
    codigo        BIGINT AUTO_INCREMENT NOT NULL,
    pedidocodigo  BIGINT NULL,
    produtocodigo BIGINT NULL,
    quantidade    INT NULL,
    precounitario DECIMAL NULL,
    precototal    DECIMAL NULL,
    CONSTRAINT pk_pedidoitem PRIMARY KEY (codigo)
);

CREATE TABLE pedidos
(
    codigo              BIGINT AUTO_INCREMENT NOT NULL,
    usuariocodigo       BIGINT NULL,
    status              VARCHAR(255) NULL,
    valortotal          DECIMAL NULL,
    datahorasolicitacao datetime NULL,
    tempototalpreparo   time NULL,
    CONSTRAINT pk_pedidos PRIMARY KEY (codigo)
);

CREATE TABLE produtos
(
    codigo          BIGINT AUTO_INCREMENT NOT NULL,
    nome            VARCHAR(255) NULL,
    descricao       VARCHAR(255) NULL,
    categoriacodigo BIGINT NULL,
    preco           DECIMAL NULL,
    tempopreparo    time NULL,
    CONSTRAINT pk_produtos PRIMARY KEY (codigo)
);

CREATE TABLE usuarios
(
    codigo BIGINT AUTO_INCREMENT NOT NULL,
    nome   VARCHAR(255) NULL,
    cpf    VARCHAR(255) NULL,
    email  VARCHAR(255) NULL,
    CONSTRAINT pk_usuarios PRIMARY KEY (codigo)
);

ALTER TABLE entregas
    ADD CONSTRAINT uc_entregas_pedidocodigo UNIQUE (pedidocodigo);

ALTER TABLE filapedidospreparacao
    ADD CONSTRAINT uc_filapedidospreparacao_pedidocodigo UNIQUE (pedidocodigo);

ALTER TABLE pagamentos
    ADD CONSTRAINT uc_pagamentos_pedidocodigo UNIQUE (pedidocodigo);

ALTER TABLE produtos
    ADD CONSTRAINT uc_produtos_categoriacodigo UNIQUE (categoriacodigo);

ALTER TABLE entregas
    ADD CONSTRAINT FK_ENTREGAS_ON_PEDIDOCODIGO FOREIGN KEY (pedidocodigo) REFERENCES pedidos (codigo);

ALTER TABLE filapedidospreparacao
    ADD CONSTRAINT FK_FILAPEDIDOSPREPARACAO_ON_PEDIDOCODIGO FOREIGN KEY (pedidocodigo) REFERENCES pedidos (codigo);

ALTER TABLE pagamentos
    ADD CONSTRAINT FK_PAGAMENTOS_ON_PEDIDOCODIGO FOREIGN KEY (pedidocodigo) REFERENCES pedidos (codigo);

ALTER TABLE pedidoitem
    ADD CONSTRAINT FK_PEDIDOITEM_ON_PEDIDOCODIGO FOREIGN KEY (pedidocodigo) REFERENCES pedidos (codigo);

ALTER TABLE pedidoitem
    ADD CONSTRAINT FK_PEDIDOITEM_ON_PRODUTOCODIGO FOREIGN KEY (produtocodigo) REFERENCES produtos (codigo);

ALTER TABLE pedidos
    ADD CONSTRAINT FK_PEDIDOS_ON_USUARIOCODIGO FOREIGN KEY (usuariocodigo) REFERENCES usuarios (codigo);

ALTER TABLE produtos
    ADD CONSTRAINT FK_PRODUTOS_ON_CATEGORIACODIGO FOREIGN KEY (categoriacodigo) REFERENCES categorias (codigo);