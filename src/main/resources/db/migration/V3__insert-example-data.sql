-- Produtos da categoria LANCHE (categoriacodigo = 1)
INSERT INTO produtos (nome, descricao, categoriacodigo, preco, tempopreparo) VALUES
  ('X-Burguer', 'Pão, hambúrguer, queijo e molho especial', 1, 15.90, '00:10:00'),
  ('X-Salada', 'Pão, hambúrguer, queijo, alface e tomate', 1, 16.90, '00:12:00');

-- Produtos da categoria ACOMPANHAMENTO (categoriacodigo = 2)
INSERT INTO produtos (nome, descricao, categoriacodigo, preco, tempopreparo) VALUES
  ('Batata Frita', 'Porção média de batata frita crocante', 2, 8.50, '00:05:00'),
  ('Onion Rings', 'Anéis de cebola empanados', 2, 9.90, '00:06:00');

-- Produtos da categoria BEBIDA (categoriacodigo = 3)
INSERT INTO produtos (nome, descricao, categoriacodigo, preco, tempopreparo) VALUES
  ('Refrigerante Lata', 'Lata 350ml - sabores variados', 3, 5.00, '00:00:30'),
  ('Suco Natural', 'Suco de laranja natural', 3, 6.50, '00:02:00');

-- Produtos da categoria SOBREMESA (categoriacodigo = 4)
INSERT INTO produtos (nome, descricao, categoriacodigo, preco, tempopreparo) VALUES
  ('Sorvete', 'Bola de sorvete com cobertura', 4, 7.00, '00:03:00'),
  ('Brownie', 'Brownie de chocolate com calda quente', 4, 9.00, '00:04:00');