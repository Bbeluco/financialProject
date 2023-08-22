CREATE TABLE users (
    id int NOT NULL UNIQUE,
    name varchar(255) NOT NULL,
    cpf varchar(255) NOT NULL UNIQUE,
    email varchar(255) NOT NULL UNIQUE,
    password varchar(100) NOT NULL,
    walletId int,

    FOREIGN KEY (walletId) REFERENCES wallet(id)
);