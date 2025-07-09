CREATE SCHEMA IF NOT EXISTS order_service;

CREATE TABLE IF NOT EXISTS order_service.order_status (
                                     id serial4 NOT NULL,
                                     "name" varchar NOT NULL,
                                     CONSTRAINT order_status_pk PRIMARY KEY (id),
                                     CONSTRAINT order_status_un UNIQUE (name)
);


CREATE TABLE IF NOT EXISTS order_service."order" (
                                id serial4 NOT NULL,
                                customer_id int4 NOT NULL,
                                created_at timestamp NOT NULL DEFAULT now(),
                                order_status_id int4 NOT NULL,
                                total_order_amount numeric(10, 2) NOT NULL,
                                CONSTRAINT order_pk PRIMARY KEY (id),
                                CONSTRAINT order_fk_1 FOREIGN KEY (order_status_id) REFERENCES order_service.order_status(id)
);

CREATE TABLE IF NOT EXISTS order_service.order_items (
                                    id bigserial NOT NULL,
                                    order_id int4 NOT NULL,
                                    dish_id int4 NOT NULL,
                                    quantity int4 NOT NULL,
                                    CONSTRAINT order_items_check CHECK ((quantity > 0)),
                                    CONSTRAINT order_items_pk PRIMARY KEY (id),
                                    CONSTRAINT order_items_fk_1 FOREIGN KEY (order_id) REFERENCES order_service."order"(id)
);