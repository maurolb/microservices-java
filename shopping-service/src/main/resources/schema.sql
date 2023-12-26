DROP TABLE IF EXISTS tbl_invoices;

CREATE TABLE tbl_invoices (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  number_invoice VARCHAR(250),
  description VARCHAR(250),
  customer_id BIGINT,
  create_at TIMESTAMP,
  state VARCHAR(250)
);

DROP TABLE IF EXISTS tbl_invoice_items;

CREATE TABLE tbl_invoice_items (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  quantity DOUBLE,
  price DOUBLE,
  product_id BIGINT,
  invoice_id BIGINT,
  FOREIGN KEY (invoice_id) REFERENCES tbl_invoices(id)
);
