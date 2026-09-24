CREATE DATABASE db_ecommerce_condoriccahuana;
USE db_ecommerce_condoriccahuana;

CREATE TABLE productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    categoria VARCHAR(100),
    precio DECIMAL(10, 2) NOT NULL,
    stock INT DEFAULT 0,
    marca VARCHAR(100),
    codigo_barras VARCHAR(50)
);

CREATE TABLE pedidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente VARCHAR(150) NOT NULL,
    fecha_compra DATETIME DEFAULT CURRENT_TIMESTAMP,
    monto_total DECIMAL(10, 2) NOT NULL,
    estado_pedido VARCHAR(50) NOT NULL,
    metodo_pago VARCHAR(50) NOT NULL
);

CREATE TABLE detalle_pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (pedido) REFERENCES pedidos(id),
    FOREIGN KEY (producto_id) REFERENCES productos(id)
);