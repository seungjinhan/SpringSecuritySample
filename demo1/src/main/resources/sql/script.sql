CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);


insert into users VALUES ('user','{noop}Deepplin@1234','1');
insert into authorities VALUEs ('user','read');

insert into users VALUES ('admin','{bcrypt}$2a$12$Hjudlnew3D9tKX6SpSxkDO/P7OzFB/VrR6AiobGQ.MjEss0lAinH6','1');
insert into authorities VALUEs ('admin','admin');



CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    email VARCHAR(45) NOT NULL,
    pwd VARCHAR(200) NOT NULL,
    role VARCHAR(45) NOT NULL
);

insert into customer (email, pwd, role) values ('dee1@test.com', '{noop}Deepplin@1234', 'read');
insert into customer (email, pwd, role)  values ('dee2@test.com', '{bcrypt}$2a$12$Hjudlnew3D9tKX6SpSxkDO/P7OzFB/VrR6AiobGQ.MjEss0lAinH6', 'read')