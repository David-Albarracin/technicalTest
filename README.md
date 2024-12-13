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

### **1. Acceder a PostgreSQL**

1. Abre la terminal y accede a PostgreSQL usando tu usuario:
   ```bash
   psql -U postgres
   ```
2. Ingresa tu contraseña si es necesario.

---

### **2. Crear la Base de Datos**

Ejecuta el siguiente comando para crear la base de datos:

```sql
CREATE DATABASE inventory;
```

Para usar esta base de datos:
```bash
\c inventory
```

---

### **3. Crear las Tablas**

Ejecuta los siguientes comandos para crear las tablas necesarias:

#### Crear la Tabla `categories`:
```sql
CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE CHECK (LENGTH(nombre) BETWEEN 3 AND 50)
);
```

#### Crear la Tabla `products`:
```sql
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
spring.datasource.username=your_username
spring.datasource.password=your_password
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

Utilities Esta carpeta incluye un archivo JSON compatible con Insomnia que contiene todos los endpoints preconfigurados para probar la API.

### **Categorías**

- **GET /api/categories**: Listar todas las categorías.
- **GET /api/categories/{id}**: Obtener una categoría por su ID.
- **POST /api/categories**: Crear una nueva categoría.
- **PUT /api/categories/{id}**: Actualizar una categoría existente.
- **DELETE /api/categories/{id}**: Eliminar una categoría por su ID (solo si no tiene productos asociados).

### **Productos**

- **GET /api/products**: Listar todos los productos (con su categoría).
- **GET /api/products/{id}**: Obtener un producto por su ID.
- **POST /api/products**: Crear un nuevo producto.
- **PUT /api/products/{id}**: Actualizar un producto existente.
- **DELETE /api/products/{id}**: Eliminar un producto por su ID.
- **GET /api/products/categoria/{categoriaId}**: Listar todos los productos de una categoría específica.



