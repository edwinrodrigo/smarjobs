
# Evaluación JAVA - SPRING BOOT: Gestión de Usuarios con sus teléfonos

Este proyecto consiste en el desarrollo de una solución que permita gestionar usuarios y sus respectivos teléfonos, incluyendo la capacidad de crear, buscar, actualizar y eliminar usuarios.

## Objetivo
Desarrollar una API RESTful utilizando Spring Boot que gestione usuarios y teléfonos. El proyecto debe seguir buenas prácticas de desarrollo, aplicar principios SOLID, y utilizar las tecnologías especificadas a continuación.

## Requisitos Técnicos

### Java
- Utilizar **Java 17** para la implementación.
- Aprovechar las características avanzadas de Java 17, como lambdas y streams, cuando sea apropiado.
- Utilizar **Maven** como gestor de dependencias.

### Spring Boot
- Construir la aplicación utilizando la **última versión** disponible de Spring Boot.

### Base de Datos
- Utilizar una base de datos **H2** en memoria para almacenamiento temporal.
- Crear dos tablas: `users` y `phones`.

### JPA
- Implementar una capa de persistencia utilizando **JPA** para manejar el almacenamiento y la recuperación de usuarios y teléfonos.

### JWT (JSON Web Token)
- Implementar **JWT** para la autenticación y generación de tokens de usuario.

### OpenAPI y Swagger
- Documentar la API utilizando **OpenAPI** y **Swagger**.

## Funcionalidades

### Guardar Usuario
Implementar un endpoint para crear un usuario con la siguiente estructura JSON:

**Endpoint:** `POST /manager-api/user/save`

**Request:**
```json
{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.com",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```

**Response:**
```json
{
    "id": "bc57e03d-dfbf-4fbc-b9ef-d5f47f6e77db",
    "created": "2024-08-22 02:58:51",
    "modified": "2024-08-22 02:58:51",
    "lastLogin": "2024-08-22 02:58:51",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5jb20iLCJpYXQiOjE3MjQyOTU1MzEsImV4cCI6MTcyNDMwOTkzMX0.g49dycJcZijrFoFXcN9vcU6WI9PnUa1b4sB4xqqrZ2pc4uJfZQdgSQZehMK7qml22fMYy0_gOLq08gQ8pPlC4g",
    "isactive": true
}
```

### Buscar Usuario
Implementar un endpoint para buscar un usuario por su UUID.

**Endpoint:** `GET /manager-api/user/find/{uuid}`

**Response:**
```json
{
    "id": "bc57e03d-dfbf-4fbc-b9ef-d5f47f6e77db",
    "email": "juan@rodriguez.com",
    "created": "2024-08-21 05:00:00",
    "modified": "2024-08-21 05:00:00",
    "lastLogin": "2024-08-21 05:00:00",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5jb20iLCJpYXQiOjE3MjQyOTU1MzEsImV4cCI6MTcyNDMwOTkzMX0.g49dycJcZijrFoFXcN9vcU6WI9PnUa1b4sB4xqqrZ2pc4uJfZQdgSQZehMK7qml22fMYy0_gOLq08gQ8pPlC4g",
    "isactive": false,
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```

### Actualizar Usuario
Implementar un endpoint para actualizar la información de un usuario existente.

**Endpoint:** `PUT /manager-api/user/update`

**Request:**
```json
{
    "id": "bc57e03d-dfbf-4fbc-b9ef-d5f47f6e77db",
    "name": "Juana aaaa",
    "email": "juan@rodriguez.com"
}
```

**Response:**
```json
{
    "id": "bc57e03d-dfbf-4fbc-b9ef-d5f47f6e77db",
    "email": "juan@rodriguez.com",
    "created": "2024-08-21 05:00:00",
    "modified": "2024-08-22 02:58:56",
    "lastLogin": "2024-08-22 02:58:56",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5jb20iLCJpYXQiOjE3MjQyOTU1MzEsImV4cCI6MTcyNDMwOTkzMX0.g49dycJcZijrFoFXcN9vcU6WI9PnUa1b4sB4xqqrZ2pc4uJfZQdgSQZehMK7qml22fMYy0_gOLq08gQ8pPlC4g",
    "isactive": true,
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```

### Eliminar Usuario
Implementar un endpoint para eliminar un usuario por su UUID.

**Endpoint:** `DELETE /manager-api/user/delete/{uuid}`

**Response:** HTTP 200 OK

## Documentación
- Se utilizo **OpenAPI** y **Swagger** para documentar de manera clara la API.

## Entregables

### Repositorio en GitHub
-El proyecto esta compartido en el siguiente direccion de github

### Documentación
- Incluir instrucciones claras sobre cómo ejecutar y probar la aplicación.
- **Incluir ejemplos de JSON para pruebas en un archivo de texto o mediante un proyecto en Postman.** Nota: Si esto falta, se restarán puntos de la evaluación.
