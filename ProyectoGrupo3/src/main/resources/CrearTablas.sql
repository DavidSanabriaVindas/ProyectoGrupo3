-- Eliminación y Creación de la Base de Datos del Hotel
DROP SCHEMA IF EXISTS hotel;
DROP USER IF EXISTS usuario_hotel;

CREATE SCHEMA hotel;

CREATE USER 'usuario_hotel'@'%' IDENTIFIED BY 'Hotel_Clave.';
GRANT ALL PRIVILEGES ON hotel.* TO 'usuario_hotel'@'%';
FLUSH PRIVILEGES;

USE hotel;

-- Creación de las Tablas del Hotel con el campo 'activo'
CREATE TABLE reservas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    cedula VARCHAR(20),
    telefono VARCHAR(20),
    correo VARCHAR(100),
    tipo_habitacion varchar(100) NOT NULL,
    activo boolean
);

-- Inserción de Datos de Ejemplo (con 'activo' establecido)
INSERT INTO reservas (nombre, apellido, cedula, telefono, correo, tipo_habitacion, activo) VALUES
('Ana', 'García', '12345678', '111222333', 'ana.garcia@example.com', 'Estandar', TRUE),
('Carlos', 'López', '87654321', '444555666', 'carlos.lopez@example.com', 'Suite', TRUE),
('Laura', 'Martínez', '98761234', '777888999', 'laura.martinez@example.com', 'Suite', TRUE),
('Pedro', 'Rodríguez', '43219876', '101112131', 'pedro.rodriguez@example.com', 'Premium', TRUE),
('Sofía', 'Pérez', '11223344', '141516171', 'sofia.perez@example.com', 'Estandar', TRUE),
('Diego', 'Sánchez', '44332211', '181920212', 'diego.sanchez@example.com', 'Premium', TRUE);