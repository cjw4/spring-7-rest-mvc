drop table if exists beer_order;
drop table if exists beer_order_line;

CREATE TABLE beer_order (
   id varchar(36) NOT NULL,
   create_date datetime(6) NOT NULL,
   customer_ref varchar(255) NULL,
   last_modified_date DATETIME(6) NOT NULL,
   version BIGINT NOT NULL,
   customer_id varchar(36) NOT NULL,
   CONSTRAINT beer_order_pk PRIMARY KEY (id),
   CONSTRAINT beer_order_customer_FK FOREIGN KEY (customer_id) REFERENCES customer(id)
) ENGINE=InnoDB;

CREATE TABLE beer_order_line (
    id varchar(36) NOT NULL,
    beer_id varchar(36) NULL,
    create_date DATETIME(6) NOT NULL,
    last_modified_date DATETIME(6) NOT NULL,
    order_quantity INT NULL,
    quantity_allocated INT NULL,
    version BIGINT NOT NULL,
    beer_order_id varchar(36) NULL,
    CONSTRAINT beer_order_line_pk PRIMARY KEY (id),
    CONSTRAINT beer_order_line_beer_order_FK FOREIGN KEY (beer_order_id) REFERENCES beer_order(id),
    CONSTRAINT beer_order_line_beer_FK FOREIGN KEY (beer_id) REFERENCES beer(id)
) ENGINE=InnoDB;


