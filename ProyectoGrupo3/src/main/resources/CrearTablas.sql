-- Eliminación y Creación de la Base de Datos del Hotel
DROP SCHEMA IF EXISTS hotel;
DROP USER IF EXISTS usuario_hotel;

CREATE SCHEMA hotel;

CREATE USER 'usuario_hotel'@'%' IDENTIFIED BY 'Hotel_Clave.';
GRANT ALL PRIVILEGES ON hotel.* TO 'usuario_hotel'@'%';
FLUSH PRIVILEGES;

USE hotel;

-- Creación de las Tablas del Hotel con el campo 'activo'
CREATE TABLE habitacion_estandar (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    cedula VARCHAR(20),
    telefono VARCHAR(20),
    correo VARCHAR(100),
    activo boolean
);

CREATE TABLE habitacion_suite (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    cedula VARCHAR(20),
    telefono VARCHAR(20),
    correo VARCHAR(100),
    activo boolean
);

CREATE TABLE habitacion_premium (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    cedula VARCHAR(20),
    telefono VARCHAR(20),
    correo VARCHAR(100),
    activo boolean
);

-- Inserción de Datos de Ejemplo (con 'activo' establecido)
INSERT INTO habitacion_estandar (nombre, apellido, cedula, telefono, correo, activo) VALUES
('Ana', 'García', '12345678', '111222333', 'ana.garcia@example.com', TRUE),
('Carlos', 'López', '87654321', '444555666', 'carlos.lopez@example.com', TRUE);

INSERT INTO habitacion_suite (nombre, apellido, cedula, telefono, correo, activo) VALUES
('Laura', 'Martínez', '98761234', '777888999', 'laura.martinez@example.com', TRUE),
('Pedro', 'Rodríguez', '43219876', '101112131', 'pedro.rodriguez@example.com', TRUE);

INSERT INTO habitacion_premium (nombre, apellido, cedula, telefono, correo, activo) VALUES
('Sofía', 'Pérez', '11223344', '141516171', 'sofia.perez@example.com', TRUE),
('Diego', 'Sánchez', '44332211', '181920212', 'diego.sanchez@example.com', TRUE);