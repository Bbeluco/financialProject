INSERT INTO users (id, name, cpf, email, password) VALUES (1, 'Bruno Beluco Bispo', '12312312301', 'b@beluco.com', 'teste123');
INSERT INTO users (id, name, cpf, email, password) VALUES (2, 'Gigi abacaxi', '99999999901', 'g@lobo.com', 'teste123');
INSERT INTO users (id, name, cpf, email, password) VALUES (3, 'Fiona', '88888888803', 'fi@ona.com', 'teste123');

INSERT INTO wallet (balance) VALUES (5000.35);
INSERT INTO wallet (balance) VALUES (120.9);
INSERT INTO wallet (balance) VALUES (0.3);

UPDATE users set walletId=1 WHERE id=1;
UPDATE users set walletId=2 WHERE id=2;
UPDATE users set walletId=3 WHERE id=3;