
INSERT INTO brand (brand_id, brand_name) VALUES
(1, 'Gucci'),
(2, 'Adidas'),
(3, 'Vercase');
INSERT INTO category (category_id, category_name) VALUES
(1, 'T-Shirt'),
(2, 'Sneaker'),
(3, 'Hat');
INSERT INTO product (product_id, product_name, price, description, color, brand_id, category_id) VALUES
(1, 'Gucci T-Shirt', 100, 'New Product!!', 'Yelow', 1, 1),
(2, 'Air Force 1', 200, 'The best seller', 'Blue', 2, 2),
(3, 'Vercase Raffia', 50, 'Commonly to trending', 'Skin', 3, 3);
INSERT INTO product_image (id, url, product_id) VALUES
(1, 'image1.jpg', 1),
(2, 'image2.jpg', 1),
(3, 'image3.jpg', 2),
(4, 'image4.jpg', 3);