![](https://raw.githubusercontent.com/David-Albarracin/README_MATERIALS/main/spring.png)

# Spring Boot API

Desarrollar una API REST utilizando Spring Boot que gestione una entidad de "Productos". La aplicación deberá permitir realizar operaciones CRUD, validar datos y manejar relaciones con otra entidad llamada "Categorías".

## Instalación

1. **Versiones**

   ````java
   -- Java Version 17 -- Requiere JDK
   -- Postgres SQL 17 -- Requiere Manejador Postgres
   -- Spring Boot 3.4.0
   -- Maven
   -- Packaging Jar
    
   ````

2. **Clonamos el repositorio**

   ````cmd
   git clone https://github.com/David-Albarracin/
   ````

3. **Iniciamos nu**





## Requerimientos

1. **Comandos DDL para construir la estructura de la base de datos**

   - ```sqla
     --CREATE DATABASE inventory;
     
     CREATE TABLE categories (
         id SERIAL PRIMARY KEY,
         nombre VARCHAR(50) NOT NULL UNIQUE CHECK (LENGTH(nombre) BETWEEN 3 AND 50)
     );
     
     CREATE TABLE products (
         id SERIAL PRIMARY KEY,
         nombre VARCHAR(50) NOT NULL CHECK (LENGTH(nombre) BETWEEN 3 AND 50), 
         descripcion VARCHAR(255),
         precio DECIMAL(10, 2) NOT NULL CHECK (precio > 0),
         categoria_id INT REFERENCES categories(id) ON DELETE SET NULL
     );
     
     ```
     
   
2. 