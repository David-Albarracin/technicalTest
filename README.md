![](https://raw.githubusercontent.com/David-Albarracin/README_MATERIALS/main/spring.png)

# Spring Boot API

Este proyecto consiste en una API REST desarrollada con **Spring Boot** que gestiona una entidad de **Productos**. La aplicación permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar), valida los datos de entrada y maneja relaciones con otra entidad llamada **Categorías**.

## Instalación

### 1. **Versiones Requeridas**

- **Java 17** (JDK)
- **PostgreSQL 17** (Gestor de base de datos)
- **Spring Boot 3.4.0**
- **Maven** (Herramienta de gestión de dependencias)
- **Empaquetado en formato JAR**

### 2. **Clonar el Repositorio**

Ejecuta el siguiente comando para clonar el repositorio:

```bash
git clone https://github.com/David-Albarracin/technicalTest
```

### 3. **Configuración de la Base de Datos**

Antes de ejecutar la aplicación, asegúrate de tener PostgreSQL configurado y en funcionamiento. A continuación, se proporcionan los comandos DDL necesarios para construir la estructura de la base de datos:

## Requerimientos

### 1. **Comandos DDL para la Base de Datos**

Ejecuta los siguientes comandos SQL para crear las tablas necesarias en la base de datos PostgreSQL:

```sql
-- Crear la base de datos
CREATE DATABASE inventory;

-- Crear la tabla de categorías
CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE CHECK (LENGTH(nombre) BETWEEN 3 AND 50)
);

-- Crear la tabla de productos
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL CHECK (LENGTH(nombre) BETWEEN 3 AND 50), 
    descripcion VARCHAR(255),
    precio DECIMAL(10, 2) NOT NULL CHECK (precio > 0),
    categoria_id INT REFERENCES categories(id) ON DELETE SET NULL
);
```

### 2. **Configuración del Archivo `application.properties`**

Asegúrate de configurar correctamente la conexión a tu base de datos en el archivo `application.properties` de la aplicación Spring Boot:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/inventory
spring.datasource.username=postgres
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=none
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql=true
```

### 3. **Compilar y Ejecutar la Aplicación**

Para compilar y ejecutar la aplicación, usa los siguientes comandos:

- **Compilar**:  
  ```bash
  mvn clean install
  ```

- **Ejecutar**:  
  ```bash
  mvn spring-boot:run
  ```

## Endpoints de la API

La API expone los siguientes endpoints para interactuar con los productos y categorías:

- **GET /api/categories**: Obtiene todas las categorías.
- **GET /api/categories/{id}**: Obtiene una categoría por su ID.
- **POST /api/categories**: Crea una nueva categoría.
- **PUT /api/categories/{id}**: Actualiza una categoría existente.
- **DELETE /api/categories/{id}**: Elimina una categoría por su ID.

- **GET /api/products**: Obtiene todos los productos.
- **GET /api/products/{id}**: Obtiene un producto por su ID.
- **POST /api/products**: Crea un nuevo producto.
- **PUT /api/products/{id}**: Actualiza un producto existente.
- **DELETE /api/products/{id}**: Elimina un producto por su ID.

## Consideraciones Finales

- Asegúrate de tener la base de datos correctamente configurada y en funcionamiento antes de ejecutar la aplicación.
- Los datos se validan en la API para asegurar que los valores sean correctos (por ejemplo, se valida el rango de los precios, la longitud de los nombres y descripciones, etc.).

