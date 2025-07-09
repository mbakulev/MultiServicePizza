CREATE SCHEMA IF NOT EXISTS user_service;

CREATE TABLE IF NOT EXISTS user_service.user (
                                 id serial4 NOT NULL,
                                 name varchar NOT NULL,
                                 email varchar NOT NULL,
                                 password varchar NOT NULL,
                                 CONSTRAINT customer_pk PRIMARY KEY (id)
);


