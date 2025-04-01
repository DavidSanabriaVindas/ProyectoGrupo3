-- Eliminación y Creación de la Base de Datos del Hotel
DROP SCHEMA IF EXISTS hotel;
DROP USER IF EXISTS usuario_hotel;
CREATE SCHEMA hotel;
CREATE USER 'usuario_hotel'@'%' IDENTIFIED BY 'Hotel_Clave.';
GRANT ALL PRIVILEGES ON hotel.* TO 'usuario_hotel'@'%';
FLUSH PRIVILEGES;
USE hotel;

-- Creación de las Tablas del Hotel con el campo 'activo'
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

-- Inserción de Datos de Ejemplo (con 'activo' establecido)
INSERT INTO reservas (nombre, apellido, cedula, telefono, correo, tipo_habitacion, activo) VALUES
('Ana', 'García', '12345678', '111222333', 'ana.garcia@example.com', 'Estandar', TRUE),
('Carlos', 'López', '87654321', '444555666', 'carlos.lopez@example.com', 'Suite', TRUE),
('Laura', 'Martínez', '98761234', '777888999', 'laura.martinez@example.com', 'Suite', TRUE),
('Pedro', 'Rodríguez', '43219876', '101112131', 'pedro.rodriguez@example.com', 'Premium', TRUE),
('Sofía', 'Pérez', '11223344', '141516171', 'sofia.perez@example.com', 'Estandar', TRUE),
('Diego', 'Sánchez', '44332211', '181920212', 'diego.sanchez@example.com', 'Premium', TRUE);

-- SISTEMA DE SEGURIDAD - NUEVAS TABLAS

-- Tabla de usuarios para inicio de sesión
CREATE TABLE usuario (
  id_usuario INT NOT NULL AUTO_INCREMENT,
  username varchar(20) NOT NULL,
  password varchar(512) NOT NULL,
  nombre VARCHAR(20) NOT NULL,
  apellidos VARCHAR(30) NOT NULL,
  correo VARCHAR(75) NULL,
  telefono VARCHAR(15) NULL,
  ruta_imagen varchar(1024),
  activo boolean,
  PRIMARY KEY (id_usuario)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

-- Datos de ejemplo de usuarios (passwords hasheados con bcrypt)
INSERT INTO usuario (id_usuario, username, password, nombre, apellidos, correo, telefono, ruta_imagen, activo) VALUES 
(1, 'admin', '$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.', 'Admin', 'Hotel', 'admin@hotel.com', '1112223333', 'https://example.com/images/admin.jpg', true),
(2, 'usuario', '$2a$10$GkEj.ZzmQa/aEfDmtLIh3udIH5fMphx/35d0EYeqZL5uzgCJ0lQRi', 'Usuario', 'Normal', 'usuario@hotel.com', '4445556666', 'https://example.com/images/usuario.jpg', true);

-- Definición de roles del sistema (solo ADMIN y USER)
CREATE TABLE role (  
  rol varchar(20),
  PRIMARY KEY (rol)  
);

INSERT INTO role (rol) VALUES ('ADMIN'), ('USER');

-- Relación entre usuarios y roles (muchos a muchos)
CREATE TABLE rol (
  id_rol INT NOT NULL AUTO_INCREMENT,
  nombre varchar(20),
  id_usuario int,
  PRIMARY KEY (id_rol)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

-- Asignación de roles a usuarios
INSERT INTO rol (id_rol, nombre, id_usuario) VALUES
 (1, 'ADMIN', 1), 
 (2, 'USER', 1);

-- Rutas protegidas y roles que pueden accederlas
CREATE TABLE ruta (
    id_ruta INT AUTO_INCREMENT NOT NULL,
    patron VARCHAR(255) NOT NULL,
    rol_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_ruta)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

-- Mapeo de rutas a roles específicos
INSERT INTO ruta (patron, rol_name) VALUES 
-- Rutas para administradores
('/reservas/nuevo', 'ADMIN'),
('/reservas/guardar', 'ADMIN'),
('/reservas/modificar/1', 'ADMIN'),
('/reservas/eliminar/**', 'ADMIN'),
('/usuario/**', 'ADMIN'),
('/constante/**', 'ADMIN'),
('/role/**', 'ADMIN'),
('/usuario_role/**', 'ADMIN'),
('/ruta/**', 'ADMIN'),
('/reportes/**', 'ADMIN'),
-- Rutas para usuarios regulares
('/reservas/consultar', 'USER'),
('/reservas/listar', 'USER'),
('/cuenta/**', 'USER'),
('/perfil/**', 'USER');

-- Rutas públicas que no requieren autenticación
CREATE TABLE ruta_permit (
    id_ruta INT AUTO_INCREMENT NOT NULL,
    patron VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_ruta)
) ENGINE = InnoDB;

-- Definición de rutas públicas
INSERT INTO ruta_permit (patron) VALUES 
('/'),
('/index'),
('/login'),
('/registro/**'),
('/disponibilidad/**'),
('/contacto/**'),
('/errores/**'),
('/js/**'),
('/css/**'),
('/images/**'),
('/webjars/**');

-- Constantes de configuración (incluye elementos de seguridad)
CREATE TABLE constante (
    id_constante INT AUTO_INCREMENT NOT NULL,
    atributo VARCHAR(25) NOT NULL,
    valor VARCHAR(150) NOT NULL,
    PRIMARY KEY (id_constante)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

-- Valores de configuración para seguridad y acceso
INSERT INTO constante (atributo, valor) VALUES 
('dominio', 'localhost'),
('certificado', 'c:/cert'),
('correo.soporte', 'soporte@hotel.com'),
('correo.reservas', 'reservas@hotel.com'),
('session.timeout', '30'),
('max.intentos.login', '3'),
('token.expiracion', '86400');