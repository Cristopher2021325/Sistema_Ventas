DROP DATABASE IF EXISTS db_sistema_ventas_in5cm;
CREATE DATABASE db_sistema_ventas_in5cm;
USE db_sistema_ventas_in5cm;

CREATE TABLE Clientes (
    dpi_cliente INT PRIMARY KEY NOT NULL,
    nombre_cliente VARCHAR(50),
    apellido_cliente VARCHAR(50),
    direccion VARCHAR(100),
    estado INT
);

CREATE TABLE Usuarios (
    codigo_usuario INT PRIMARY KEY NOT NULL,
    username VARCHAR(45),
    password VARCHAR(45),
    email VARCHAR(60),
    rol VARCHAR(45),
    estado INT
);

CREATE TABLE Productos (
    codigo_producto INT PRIMARY KEY,
    nombre_producto VARCHAR(60),
    precio DECIMAL(10,2),
    stock INT,
    estado INT
);

CREATE TABLE Ventas (
    codigo_venta INT PRIMARY KEY NOT NULL,
    fecha_venta DATE,
    total DECIMAL(10,2),
    estado INT,
    clientes_dpi_cliente INT,
    usuarios_codigo_usuario INT,
    FOREIGN KEY (clientes_dpi_cliente) REFERENCES Clientes(dpi_cliente) ON DELETE CASCADE,
    FOREIGN KEY (usuarios_codigo_usuario) REFERENCES Usuarios(codigo_usuario) ON DELETE CASCADE
);

CREATE TABLE Detalle_venta (
    codigo_detalle_venta INT PRIMARY KEY NOT NULL,
    cantidad INT,
    precio_unitario DECIMAL(10,2),
    subtotal DECIMAL(10,2),
    productos_codigo_producto INT,
    ventas_codigo_venta INT,
    FOREIGN KEY (productos_codigo_producto) REFERENCES Productos(codigo_producto) ON DELETE CASCADE,
    FOREIGN KEY (ventas_codigo_venta) REFERENCES Ventas(codigo_venta) ON DELETE CASCADE
);

DROP PROCEDURE IF EXISTS sp_create_Cliente;
DELIMITER $$
CREATE PROCEDURE sp_create_Cliente(IN p_dpi INT, IN p_nombre VARCHAR(50), IN p_apellido VARCHAR(50), IN p_direccion VARCHAR(100), IN p_estado INT)
BEGIN
    INSERT INTO Clientes (dpi_cliente, nombre_cliente, apellido_cliente, direccion, estado)
    VALUES (p_dpi, p_nombre, p_apellido, p_direccion, p_estado);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_read_all_Clientes;
DELIMITER $$
CREATE PROCEDURE sp_read_all_Clientes()
BEGIN
    SELECT * FROM Clientes;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_update_Cliente;
DELIMITER $$
CREATE PROCEDURE sp_update_Cliente(IN p_dpi INT, IN p_nombre VARCHAR(50), IN p_apellido VARCHAR(50), IN p_direccion VARCHAR(100), IN p_estado INT)
BEGIN
    UPDATE Clientes
    SET nombre_cliente = p_nombre,
        apellido_cliente = p_apellido,
        direccion = p_direccion,
        estado = p_estado
    WHERE dpi_cliente = p_dpi;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_delete_Cliente;
DELIMITER $$
CREATE PROCEDURE sp_delete_Cliente(IN p_dpi INT)
BEGIN
    DELETE FROM Clientes WHERE dpi_cliente = p_dpi;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_create_Usuario;
DELIMITER $$
CREATE PROCEDURE sp_create_Usuario(IN p_codigo INT, IN p_username VARCHAR(45), IN p_password VARCHAR(45), IN p_email VARCHAR(60), IN p_rol VARCHAR(45), IN p_estado INT)
BEGIN
    INSERT INTO Usuarios (codigo_usuario, username, password, email, rol, estado)
    VALUES (p_codigo, p_username, p_password, p_email, p_rol, p_estado);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_read_all_Usuarios;
DELIMITER $$
CREATE PROCEDURE sp_read_all_Usuarios()
BEGIN
    SELECT * FROM Usuarios;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_update_Usuario;
DELIMITER $$
CREATE PROCEDURE sp_update_Usuario(IN p_codigo INT, IN p_username VARCHAR(45), IN p_password VARCHAR(45), IN p_email VARCHAR(60), IN p_rol VARCHAR(45), IN p_estado INT)
BEGIN
    UPDATE Usuarios
    SET username = p_username,
        password = p_password,
        email = p_email,
        rol = p_rol,
        estado = p_estado
    WHERE codigo_usuario = p_codigo;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_delete_Usuario;
DELIMITER $$
CREATE PROCEDURE sp_delete_Usuario(IN p_codigo INT)
BEGIN
    DELETE FROM Usuarios WHERE codigo_usuario = p_codigo;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_create_Producto;
DELIMITER $$
CREATE PROCEDURE sp_create_Producto(IN p_codigo INT, IN p_nombre VARCHAR(60), IN p_precio DECIMAL(10,2), IN p_stock INT, IN p_estado INT)
BEGIN
    INSERT INTO Productos (codigo_producto, nombre_producto, precio, stock, estado)
    VALUES (p_codigo, p_nombre, p_precio, p_stock, p_estado);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_read_all_Productos;
DELIMITER $$
CREATE PROCEDURE sp_read_all_Productos()
BEGIN
    SELECT * FROM Productos;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_update_Producto;
DELIMITER $$
CREATE PROCEDURE sp_update_Producto(IN p_codigo INT, IN p_nombre VARCHAR(60), IN p_precio DECIMAL(10,2), IN p_stock INT, IN p_estado INT)
BEGIN
    UPDATE Productos
    SET nombre_producto = p_nombre,
        precio = p_precio,
        stock = p_stock,
        estado = p_estado
    WHERE codigo_producto = p_codigo;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_delete_Producto;
DELIMITER $$
CREATE PROCEDURE sp_delete_Producto(IN p_codigo INT)
BEGIN
    DELETE FROM Productos WHERE codigo_producto = p_codigo;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_create_Venta;
DELIMITER $$
CREATE PROCEDURE sp_create_Venta(IN p_codigo INT, IN p_fecha DATE, IN p_total DECIMAL(10,2), IN p_estado INT, IN p_cliente INT, IN p_usuario INT)
BEGIN
    INSERT INTO Ventas (codigo_venta, fecha_venta, total, estado, clientes_dpi_cliente, usuarios_codigo_usuario)
    VALUES (p_codigo, p_fecha, p_total, p_estado, p_cliente, p_usuario);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_read_all_Ventas;
DELIMITER $$
CREATE PROCEDURE sp_read_all_Ventas()
BEGIN
    SELECT * FROM Ventas;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_update_Venta;
DELIMITER $$
CREATE PROCEDURE sp_update_Venta(IN p_codigo INT, IN p_fecha DATE, IN p_total DECIMAL(10,2), IN p_estado INT, IN p_cliente INT, IN p_usuario INT)
BEGIN
    UPDATE Ventas
    SET fecha_venta = p_fecha,
        total = p_total,
        estado = p_estado,
        clientes_dpi_cliente = p_cliente,
        usuarios_codigo_usuario = p_usuario
    WHERE codigo_venta = p_codigo;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_delete_Venta;
DELIMITER $$
CREATE PROCEDURE sp_delete_Venta(IN p_codigo INT)
BEGIN
    DELETE FROM Ventas WHERE codigo_venta = p_codigo;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_create_Detalle_venta;
DELIMITER $$
CREATE PROCEDURE sp_create_Detalle_venta(IN p_codigo INT, IN p_cantidad INT, IN p_precio DECIMAL(10,2), IN p_subtotal DECIMAL(10,2), IN p_producto INT, IN p_venta INT)
BEGIN
    INSERT INTO Detalle_venta (codigo_detalle_venta, cantidad, precio_unitario, subtotal, productos_codigo_producto, ventas_codigo_venta)
    VALUES (p_codigo, p_cantidad, p_precio, p_subtotal, p_producto, p_venta);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_read_all_Detalle_venta;
DELIMITER $$
CREATE PROCEDURE sp_read_all_Detalle_venta()
BEGIN
    SELECT * FROM Detalle_venta;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_update_Detalle_venta;
DELIMITER $$
CREATE PROCEDURE sp_update_Detalle_venta(IN p_codigo INT, IN p_cantidad INT, IN p_precio DECIMAL(10,2), IN p_subtotal DECIMAL(10,2), IN p_producto INT, IN p_venta INT)
BEGIN
    UPDATE Detalle_venta
    SET cantidad = p_cantidad,
        precio_unitario = p_precio,
        subtotal = p_subtotal,
        productos_codigo_producto = p_producto,
        ventas_codigo_venta = p_venta
    WHERE codigo_detalle_venta = p_codigo;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_delete_Detalle_venta;
DELIMITER $$
CREATE PROCEDURE sp_delete_Detalle_venta(IN p_codigo INT)
BEGIN
    DELETE FROM Detalle_venta WHERE codigo_detalle_venta = p_codigo;
END$$
DELIMITER ;

INSERT INTO Clientes (dpi_cliente, nombre_cliente, apellido_cliente, direccion, estado) VALUES
(1, 'Juan', 'Perez', 'Guatemala', 1),
(2, 'Maria', 'Lopez', 'Santa Rosa', 1),
(3, 'Carlos', 'Ramirez', 'Jutiapa', 1);

INSERT INTO Usuarios (codigo_usuario, username, password, email, rol, estado) VALUES
(1, 'admin', '1234', 'admin@mail.com', 'ADMIN', 1),
(2, 'vendedor1', '1234', 'vendedor1@mail.com', 'VENDEDOR', 1);

INSERT INTO Productos (codigo_producto, nombre_producto, precio, stock, estado) VALUES
(1, 'Rolex Submariner', 85000.00, 10, 1),
(2, 'Omega Seamaster', 65000.00, 8, 1),
(3, 'Tag Heuer Carrera', 42000.00, 12, 1),
(4, 'Patek Philippe Nautilus', 150000.00, 5, 1),
(5, 'Audemars Piguet Royal Oak', 180000.00, 4, 1);

INSERT INTO Ventas (codigo_venta, fecha_venta, total, estado, clientes_dpi_cliente, usuarios_codigo_usuario) VALUES
(1, '2026-04-01', 85000.00, 1, 1, 1),
(2, '2026-04-02', 65000.00, 1, 1, 1),
(3, '2026-04-03', 42000.00, 1, 2, 2),
(4, '2026-04-04', 150000.00, 1, 2, 1),
(5, '2026-04-05', 180000.00, 1, 3, 2),
(6, '2026-04-06', 85000.00, 1, 1, 1),
(7, '2026-04-07', 65000.00, 1, 2, 1),
(8, '2026-04-08', 42000.00, 1, 3, 2),
(9, '2026-04-09', 150000.00, 1, 1, 1),
(10, '2026-04-10', 180000.00, 1, 2, 2);

INSERT INTO Detalle_venta (codigo_detalle_venta, cantidad, precio_unitario, subtotal, productos_codigo_producto, ventas_codigo_venta) VALUES
(1, 1, 85000.00, 85000.00, 1, 1),
(2, 1, 65000.00, 65000.00, 2, 2),
(3, 1, 42000.00, 42000.00, 3, 3),
(4, 1, 150000.00, 150000.00, 4, 4),
(5, 1, 180000.00, 180000.00, 5, 5),
(6, 1, 85000.00, 85000.00, 1, 6),
(7, 1, 65000.00, 65000.00, 2, 7),
(8, 1, 42000.00, 42000.00, 3, 8),
(9, 1, 150000.00, 150000.00, 4, 9),
(10, 1, 180000.00, 180000.00, 5, 10);

INSERT INTO Clientes (dpi_cliente, nombre_cliente, apellido_cliente, direccion, estado) VALUES
(4, 'Ana', 'Martinez', 'Escuintla', 1),
(5, 'Luis', 'Gomez', 'Guatemala', 1),
(6, 'Sofia', 'Castillo', 'Cuilapa', 1),
(7, 'Pedro', 'Hernandez', 'Chiquimulilla', 1),
(8, 'Karla', 'Mendez', 'Barberena', 1);

INSERT INTO Ventas (codigo_venta, fecha_venta, total, estado, clientes_dpi_cliente, usuarios_codigo_usuario) VALUES
(11, '2026-04-15', 127000.00, 1, 4, 1);

INSERT INTO Detalle_venta (codigo_detalle_venta, cantidad, precio_unitario, subtotal, productos_codigo_producto, ventas_codigo_venta) VALUES
(11, 1, 85000.00, 85000.00, 1, 11),
(12, 1, 42000.00, 42000.00, 3, 11);

