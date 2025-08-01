CREATE TABLE categorias (
    codigo BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NULL,
    PRIMARY KEY (codigo)
);

CREATE TABLE entregas (
    codigo BIGINT NOT NULL AUTO_INCREMENT,
    pedidocodigo BIGINT NULL,
    datahoraentrega DATETIME NULL,
    PRIMARY KEY (codigo)
);

CREATE TABLE filapedidospreparacao (
    codigo BIGINT NOT NULL AUTO_INCREMENT,
    pedidocodigo BIGINT NULL,
    PRIMARY KEY (codigo)
);

CREATE TABLE pagamentos (
    codigo BIGINT NOT NULL AUTO_INCREMENT,
    pedidocodigo BIGINT NULL,
    valorpago DECIMAL(10,2) NULL,
    status VARCHAR(255) NULL,
    datahorapagamento DATETIME NULL,
    PRIMARY KEY (codigo)
);

CREATE TABLE pedidoitem (
    codigo BIGINT NOT NULL AUTO_INCREMENT,
    pedidocodigo BIGINT NULL,
    produtocodigo BIGINT NULL,
    quantidade INT NULL,
    precounitario DECIMAL(10,2) NULL,
    precototal DECIMAL(10,2) NULL,
    PRIMARY KEY (codigo)
);

CREATE TABLE pedidos (
    codigo BIGINT NOT NULL AUTO_INCREMENT,
    usuariocodigo BIGINT NULL,
    status VARCHAR(255) NULL,
    valortotal DECIMAL(10,2) NULL,
    datahorasolicitacao DATETIME NULL,
    tempototalpreparo TIME NULL,
    PRIMARY KEY (codigo)
);

CREATE TABLE produtos (
    codigo BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NULL,
    descricao VARCHAR(255) NULL,
    categoriacodigo BIGINT NULL,
    preco DECIMAL(10,2) NULL,
    tempopreparo TIME NULL,
    PRIMARY KEY (codigo)
);

CREATE TABLE usuarios (
    codigo BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    PRIMARY KEY (codigo)
);

-- Constraints

ALTER TABLE entregas
    ADD CONSTRAINT uc_entregas_pedidocodigo UNIQUE (pedidocodigo);

ALTER TABLE filapedidospreparacao
    ADD CONSTRAINT uc_filapedidospreparacao_pedidocodigo UNIQUE (pedidocodigo);

ALTER TABLE pagamentos
    ADD CONSTRAINT uc_pagamentos_pedidocodigo UNIQUE (pedidocodigo);

ALTER TABLE usuarios
    ADD CONSTRAINT uc_usuarios_cpf UNIQUE (cpf);

ALTER TABLE usuarios
    ADD CONSTRAINT uc_usuarios_email UNIQUE (email);

-- Foreign Keys

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