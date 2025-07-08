CREATE SCHEMA IF NOT EXISTS menu_service;

CREATE TABLE IF NOT EXISTS menu_service.kitchen (
                                id serial4 NOT NULL,
                                "name" varchar NOT NULL,
                                CONSTRAINT kitchen_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS menu_service.dish (
                             id serial4 NOT NULL,
                             "name" varchar NOT NULL,
                             kitchen_id int4 NOT NULL,
                             price numeric(10, 2) NOT NULL,
                             CONSTRAINT dish_pk PRIMARY KEY (id),
                             CONSTRAINT dish_fk FOREIGN KEY (kitchen_id) REFERENCES menu_service.kitchen(id)
);