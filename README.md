# Franchise & Branch Product Management API

Este proyecto es una API reactiva construida con **Spring Boot WebFlux** para gestionar franquicias, sucursales y productos. Permite crear franquicias, agregar sucursales, registrar productos, modificar su stock y consultar el producto con mayor stock por sucursal.

---

## 🛠 Tecnologías principales
- **Java 21**
- **Spring Boot 3.5.4**
- **Spring WebFlux** (programación reactiva)
- **MongoDB** (base de datos NoSQL)
- **JUnit 5** + **WebTestClient** (para pruebas)
- **Mockito** (para mocks)
- **Gradle** (gestión del proyecto)

---

## 📦 Arquitectura del proyecto
El proyecto sigue **Clean Architecture** con las siguientes capas:

- **Domain**: Entidades del negocio y casos de uso.
- **Use Cases**: Contienen la lógica de negocio.
- **Infrastructure**:
  - **Driven Adapters**: MongoDB Repository.
  - **Entry Points**: API Reactiva con Spring WebFlux.
- **Configuration**: Beans, routing y settings generales.

---

## 📬 Endpoints principales

### 1. Crear una franquicia
```bash
curl -X POST http://localhost:8080/api/franchises \
  -H "Content-Type: application/json" \
  -d '{"id": "1", "name": "Franchise1", "branches": []}'

## 🧩 Consideraciones de diseño

Clean Architecture

Lógica de negocio aislada de infraestructura.

Facilita testeo unitario y reemplazo de tecnologías.

Programación Reactiva

Uso de Mono y Flux para peticiones no bloqueantes.

Ideal para sistemas con alta concurrencia.

Test Driven Development (TDD)

Pruebas con WebTestClient y Mockito.

Garantiza cobertura y calidad del código.

Extensibilidad

Fácil agregar nuevos casos de uso y endpoints.
