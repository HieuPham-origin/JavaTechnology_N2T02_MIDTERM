
INSERT INTO brand (brand_id, brand_name) VALUES
(1, 'Gucci'),
(2, 'Vercase'),
(3, 'Adidas');
INSERT INTO category (category_id, category_name) VALUES
(1, 'T-Shirt'),
(2, 'Jeans'),
(3, 'Sneaker');
INSERT INTO product (product_id, product_name, price, description, color, brand_id, category_id) VALUES
(1, 'T-Shirt', 30, 'New Product!!', 'Yelow', 1, 1),
(2, 'Jeans', 60, 'The best seller', 'Blue', 2, 2),
(3, 'Sneakers', 150, 'Commonly to trending', 'Skin', 3, 3);
INSERT INTO product_image (id, url, product_id) VALUES
(1, 't-shirt.jpg', 1),
(2, 'Jeans.jpg', 2),
(3, 'sneakers.jpg', 3);