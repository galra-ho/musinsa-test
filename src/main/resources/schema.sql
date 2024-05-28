DROP TABLE if EXISTS product;
DROP TABLE if EXISTS brand;
DROP TABLE if EXISTS category;

CREATE TABLE brand
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    is_deleted BOOLEAN      NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL
);

CREATE TABLE category
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(30) NOT NULL,
    created_at TIMESTAMP   NOT NULL,
    updated_at TIMESTAMP   NOT NULL
);


CREATE TABLE product
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_id    BIGINT,
    category_id BIGINT,
    price       DECIMAL(15, 2),
    is_deleted  BOOLEAN   NOT NULL,
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP NOT NULL
);