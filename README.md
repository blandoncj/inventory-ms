# Microservicio CRUD con Spring Boot

Este proyecto es un microservicio desarrollado en **Spring Boot** que implementa un CRUD sobre una entidad de ejemplo, siguiendo una arquitectura en capas. Además, incluye pruebas unitarias e integración, contenerización con Docker y orquestación con Docker Compose.

El objetivo principal es demostrar buenas prácticas en el desarrollo de microservicios, pruebas y despliegue en contenedores.

---

## 📋 Características del Proyecto

1. **CRUD Completo**: Implementación de un CRUD sobre una entidad de ejemplo.
2. **Arquitectura en Capas**:
   - **Controladores**: Endpoints REST.
   - **Servicios**: Lógica de negocio.
   - **DAO**: Interacción con la base de datos usando Spring Data JPA.
3. **Base de Datos**:
   - MariaDB ejecutándose en un contenedor Docker.
   - Volumen persistente para evitar pérdida de datos.
   - Respaldo de la base de datos con nombre que incluye la fecha.
4. **Pruebas**:
   - Pruebas unitarias con JUnit y Mockito.
   - Pruebas de integración para verificar interacción entre capas.
   - Reporte de cobertura de código con JaCoCo.
5. **Contenerización**:
   - Dockerfile con Multi-Stage Build.
   - Buenas prácticas en la construcción de imágenes.
6. **Orquestación**:
   - Gestión de servicios con Docker Compose.
7. **Publicación**:
   - Imagen del microservicio publicada en Docker Hub.
8. **(Opcional)** Automatización:
   - Script para construir la imagen y ejecutar contenedores automáticamente.

---

## 🚀 Requisitos Previos

Antes de comenzar, asegurate de tener instalado:

- **Java 17** o superior.
- **Maven** o **Gradle** (según el gestor de dependencias que uses).
- **Docker** y **Docker Compose**.
- **Postman** (para pruebas de endpoints).
- **Git** (para clonar el repositorio).

---

## ⚙️ Configuración del Proyecto

### 1. Clonar el Repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
cd <NOMBRE_DEL_PROYECTO>
```

### 2. Configurar la Base de Datos

El archivo `application.properties` ya está configurado para conectarse a un contenedor de MariaDB. Asegurate de que Docker esté corriendo y ejecutá:

```bash
docker-compose up -d
```

Esto levantará:

- Un contenedor con MariaDB en el puerto `3306`.
- Un volumen persistente para los datos.

### 3. Construir y Ejecutar el Microservicio

#### Usando Maven

```bash
mvn clean package
java -jar target/<NOMBRE_DEL_JAR>.jar
```

#### Usando Docker

```bash
docker build -t <NOMBRE_DE_LA_IMAGEN> .
docker run -p 8080:8080 <NOMBRE_DE_LA_IMAGEN>
```

---

## 🧪 Pruebas

### 1. Pruebas Unitarias e Integración

Ejecutá las pruebas con Maven:

```bash
mvn test
```

Esto generará un reporte de cobertura en la carpeta `target/site/jacoco`.

### 2. Pruebas con Postman

Importá la colección de Postman (`postman_collection.json`) incluida en el repositorio y ejecutá las pruebas para verificar el correcto funcionamiento del CRUD.

---

## 📦 Contenerización y Orquestación

### 1. Construir la Imagen Docker

El Dockerfile utiliza Multi-Stage Build para optimizar la imagen. Construí la imagen con:

```bash
docker build -t <NOMBRE_DE_LA_IMAGEN> .
```

### 2. Orquestación con Docker Compose

El archivo `docker-compose.yml` permite levantar tanto el microservicio como la base de datos. Ejecutalo con:

```bash
docker-compose up -d
```

Esto levantará:

- El microservicio en el puerto `8080`.
- MariaDB en el puerto `3306`.

### 3. Publicar en Docker Hub

Subí la imagen a Docker Hub con:

```bash
docker tag <NOMBRE_DE_LA_IMAGEN> <USUARIO_DOCKER_HUB>/<NOMBRE_REPOSITORIO>
docker push <USUARIO_DOCKER_HUB>/<NOMBRE_REPOSITORIO>
```

---

## 🛠️ Automatización (Opcional)

Incluí un script (`build_and_run.sh`) que automatiza la construcción de la imagen y la ejecución de los contenedores. Ejecutalo con:

```bash
bash build_and_run.sh
```

---

## 📂 Estructura del Proyecto

```plaintext
src/
├── main/
│   ├── java/
│   │   ├── com.example.controller/   # Controladores REST
│   │   ├── com.example.service/      # Lógica de negocio
│   │   ├── com.example.dao/          # Acceso a datos
│   │   └── com.example.entity/       # Entidades JPA
│   └── resources/
│       ├── application.properties    # Configuración de la app
│       └── data.sql                  # Datos iniciales (opcional)
├── test/                             # Pruebas unitarias e integración
Dockerfile                            # Contenerización
docker-compose.yml                    # Orquestación
postman_collection.json               # Pruebas Postman
build_and_run.sh                      # Script de automatización (opcional)
```

---

## 📜 Referencias

- [Guía de Spring Boot](https://spring.io/projects/spring-boot)
- [Documentación de Docker](https://docs.docker.com/)
- [JaCoCo - Cobertura de Código](https://www.jacoco.org/jacoco/)

---

## 📧 Contacto

Si tenés dudas o sugerencias, no dudes en contactarme. 😊

---
