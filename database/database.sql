CREATE DATABASE midtermjava;
CREATE TABLE brand (
  brand_id INT PRIMARY KEY AUTO_INCREMENT,
  brand_name VARCHAR(255) NOT NULL
);

CREATE TABLE category (
  category_id INT PRIMARY KEY AUTO_INCREMENT,
  category_name VARCHAR(255) NOT NULL
);

CREATE TABLE customer (
  customer_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL,
  enabled BOOLEAN NOT NULL
);

CREATE TABLE product (
  product_id INT PRIMARY KEY AUTO_INCREMENT,
  product_name VARCHAR(255) NOT NULL,
  price INT NOT NULL,
  description VARCHAR(255),
  color VARCHAR(255),
  brand_id INT,
  category_id INT,
  FOREIGN KEY (brand_id) REFERENCES brand(brand_id),
  FOREIGN KEY (category_id) REFERENCES category(category_id)
);

CREATE TABLE product_image (
  id INT PRIMARY KEY AUTO_INCREMENT,
  url VARCHAR(255) NOT NULL,
  product_id INT,
  FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE `order` (
  order_id INT PRIMARY KEY AUTO_INCREMENT,
  price INT NOT NULL,
  customer_id INT,
  status VARCHAR(255),
  payment VARCHAR(255),
  day_created DATETIME,
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE order_detail (
  product_id INT,
  order_id INT,
  quantity INT NOT NULL,
  price INT NOT NULL,
  PRIMARY KEY (product_id, order_id),
  FOREIGN KEY (product_id) REFERENCES product(product_id),
  FOREIGN KEY (order_id) REFERENCES `order`(order_id)
);