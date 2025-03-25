CREATE DATABASE IF NOT EXISTS inventoryms;
USE inventoryms;

CREATE TABLE IF NOT EXISTS categories (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE,
  is_active BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  sku VARCHAR(50) NOT NULL UNIQUE,
  name VARCHAR(150) NOT NULL,
  description TEXT,
  stock INT UNSIGNED DEFAULT 1,
  price DOUBLE NOT NULL,
  is_active BOOLEAN DEFAULT TRUE,
  category_id INT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (category_id) REFERENCES categories(id)
);

INSERT INTO categories (name) VALUES ('Electronics'), ('Clothing'), ('Books');

INSERT INTO products (sku, name, description, stock, price, category_id) VALUES
  ('SKU-001', 'Laptop', 'A laptop computer', 10, 1000.00, 1),
  ('SKU-002', 'T-shirt', 'A simple t-shirt', 100, 10.00, 2),
  ('SKU-003', 'Java Programming', 'A book about Java programming', 50, 20.00, 3);
