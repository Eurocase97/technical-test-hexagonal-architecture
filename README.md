# Technical Test - Hexagonal Architecture


## 📋 Descripción

Este proyecto es una prueba técnica que implementa **Arquitectura Hexagonal** (también conocida como Ports & Adapters), un patrón arquitectónico que promueve la separación de preocupaciones y la independencia del dominio de negocio respecto a detalles técnicos e infraestructura.

### ¿Qué es la Arquitectura Hexagonal?

La Arquitectura Hexagonal, propuesta por Alistair Cockburn, permite que una aplicación sea igualmente conducida por usuarios, programas, tests automatizados o scripts batch, y que pueda ser desarrollada y testeada de forma aislada de sus dispositivos de runtime y bases de datos.

#### Principios Fundamentales

- **Independencia del dominio**: La lógica de negocio no depende de frameworks, UI o bases de datos
- **Testabilidad**: El código de dominio puede ser testeado sin necesidad de infraestructura externa
- **Flexibilidad**: Los adaptadores pueden ser intercambiados sin afectar el núcleo de la aplicación
- **Mantenibilidad**: Separación clara de responsabilidades facilita el mantenimiento

## 🏗️ Estructura del Proyecto

```
technical-test-hexagonal-architecture/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── [company]/
│   │   │           └── [project]/
│   │   │               ├── domain/              # 🔵 Dominio (Núcleo del hexágono)
│   │   │               │   ├── model/           # Entidades y Value Objects
│   │   │               │   ├── port/            # Puertos (Interfaces)
│   │   │               │   │   ├── in/          # Casos de uso (entrada)
│   │   │               │   │   └── out/         # Repositorios/Servicios externos (salida)
│   │   │               │   └── service/         # Servicios de dominio
│   │   │               │
│   │   │               ├── application/         # 🟢 Capa de aplicación
│   │   │               │   └── service/         # Implementación de casos de uso
│   │   │               │
│   │   │               └── infrastructure/      # 🟡 Adaptadores
│   │   │                   ├── adapter/
│   │   │                   │   ├── in/          # Adaptadores de entrada
│   │   │                   │   │   └── web/     # Controladores REST
│   │   │                   │   └── out/         # Adaptadores de salida
│   │   │                   │       └── persistence/  # JPA, MongoDB, etc.
│   │   │                   └── config/          # Configuración de Spring
│   │   │
│   │   └── resources/
│   │       ├── application.yml
│   │       └── application-dev.yml
│   │
│   └── test/
│       ├── java/
│       │   └── [unit & integration tests]
│       └── resources/
│
├── pom.xml
└── README.md
```

### 📦 Capas de la Arquitectura

#### 1. 🔵 Dominio (Domain)
El corazón del hexágono. Contiene:
- **Entidades**: Objetos con identidad única
- **Value Objects**: Objetos inmutables definidos por sus atributos
- **Puertos**: Interfaces que definen contratos
  - **Puertos de entrada (in)**: Casos de uso que la aplicación ofrece
  - **Puertos de salida (out)**: Operaciones que la aplicación necesita del exterior

#### 2. 🟢 Aplicación (Application)
Orquesta el flujo de la aplicación:
- Implementa los casos de uso definidos en los puertos de entrada
- Utiliza los puertos de salida para interactuar con el mundo exterior
- No contiene lógica de negocio, solo coordinación

#### 3. 🟡 Infraestructura (Infrastructure)
Los adaptadores que conectan el dominio con el mundo exterior:
- **Adaptadores de entrada (Driving)**: API REST, CLI, mensajería
- **Adaptadores de salida (Driven)**: Base de datos, APIs externas, colas de mensajes

## 🚀 Tecnologías Utilizadas

- **Java 11+**
- **Spring Boot** - Framework de aplicación
- **Spring Data JPA** - Persistencia de datos
- **Maven** - Gestión de dependencias
- **JUnit 5** - Testing unitario
- **Mockito** - Mocking en tests
- **H2/PostgreSQL** - Base de datos
- **Lombok** - Reducción de código boilerplate

## ⚙️ Requisitos Previos

- Java JDK 11 o superior
- Maven 3.6 o superior
- IDE (IntelliJ IDEA, Eclipse, VS Code)
- Git

## 🔧 Instalación y Configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/Eurocase97/technical-test-hexagonal-architecture.git
cd technical-test-hexagonal-architecture
```

### 2. Compilar el proyecto

```bash
mvn clean install
```

### 3. Ejecutar tests

```bash
mvn test
```

### 4. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

## 📝 Configuración

### Perfiles de Spring

El proyecto utiliza perfiles de Spring para diferentes entornos:

- **default**: Configuración básica
- **dev**: Desarrollo local (H2 in-memory)
- **prod**: Producción (PostgreSQL)

Para ejecutar con un perfil específico:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Variables de Entorno

```properties
# Database
DB_URL=jdbc:postgresql://localhost:5432/dbname
DB_USERNAME=user
DB_PASSWORD=password

# Server
SERVER_PORT=8080
```


## 📡 API Endpoints

### Ejemplo de Endpoints

```http
# Crear recurso
POST /api/v1/resource
Content-Type: application/json

{
  "field": "value"
}

# Obtener recurso
GET /api/v1/resource/{id}

# Listar recursos
GET /api/v1/resources?page=0&size=10

```


## 🔄 Flujo de Dependencias

```
┌─────────────────────────────────────────────┐
│         Adaptador de Entrada (REST)         │
│              (Infrastructure)               │
└─────────────────┬───────────────────────────┘
                  │ Llama a
                  ▼
┌─────────────────────────────────────────────┐
│          Puerto de Entrada (Interface)      │
│                 (Domain)                    │
└─────────────────┬───────────────────────────┘
                  │ Implementado por
                  ▼
┌─────────────────────────────────────────────┐
│        Servicio de Aplicación (UseCase)     │
│              (Application)                  │
└─────┬─────────────────────────────────┬─────┘
      │ Usa                             │ Usa
      ▼                                 ▼
┌──────────────┐              ┌──────────────────┐
│   Entidades  │              │ Puerto de Salida │
│   (Domain)   │              │   (Interface)    │
└──────────────┘              └────────┬─────────┘
                                       │ Implementado por
                                       ▼
                              ┌──────────────────┐
                              │ Adaptador Salida │
                              │ (Infrastructure) │
                              └──────────────────┘
```

## 🎓 Conceptos Clave

### Puertos (Ports)
Interfaces que definen puntos de entrada y salida del dominio.

### Adaptadores (Adapters)
Implementaciones concretas que conectan el dominio con tecnologías específicas.

### Principio de Inversión de Dependencias
El dominio no depende de la infraestructura; la infraestructura depende del dominio.

### Separación de Preocupaciones
Cada capa tiene una responsabilidad única y bien definida.

---

## 👤 Autor

**Eurocase97**
- GitHub: [@Eurocase97](https://github.com/Eurocase97)

