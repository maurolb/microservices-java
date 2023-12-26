delete from tbl_regions;
INSERT INTO tbl_regions (name) VALUES ('Mexico');
INSERT INTO tbl_regions (name) VALUES ('Rusia');
INSERT INTO tbl_regions (name) VALUES ('Argentina');

delete from tbl_customers;

INSERT INTO tbl_customers (first_name, last_name, number_id, email, photo_url, state, region_id)
VALUES ('Juancho', 'Tadentro', '38561389', 'juanchotadentro@gmail.com', 'https://pic.png', 'CREATED', 1);

INSERT INTO tbl_customers (first_name, last_name, number_id, email, photo_url, state, region_id)
VALUES ('Leandro', 'Gado', '13799337', 'leandrogado@gmail.com', 'https://pic.png', 'CREATED', 1);

INSERT INTO tbl_customers (first_name, last_name, number_id, email, photo_url, state, region_id)
VALUES ('Thon', 'Cho', '39820395', 'toncho@gmail.com', 'https://pic.png', 'CREATED', 2);