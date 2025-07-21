ALTER TABLE usuarios
    ADD CONSTRAINT uc_usuarios_cpf UNIQUE (cpf);

ALTER TABLE usuarios
    ADD CONSTRAINT uc_usuarios_email UNIQUE (email);

ALTER TABLE usuarios
    MODIFY cpf VARCHAR (255) NOT NULL;

ALTER TABLE usuarios
    MODIFY email VARCHAR (255) NOT NULL;

ALTER TABLE usuarios
    MODIFY nome VARCHAR (255) NOT NULL;