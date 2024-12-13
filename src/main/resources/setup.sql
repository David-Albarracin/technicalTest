CREATE DATABASE inventory;

CREATE TABLE categorias (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE CHECK (LENGTH(nombre) BETWEEN 3 AND 50)
);

CREATE TABLE productos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL CHECK (LENGTH(nombre) BETWEEN 3 AND 50), 
    descripcion VARCHAR(255),
    precio DECIMAL(10, 2) NOT NULL CHECK (precio > 0),
    categoria_id INT REFERENCES categories(id) ON DELETE SET NULL
);