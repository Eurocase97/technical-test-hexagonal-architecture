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
- **Adaptadores de entrada (Driving)**: API REST
- **Adaptadores de salida (Driven)**: Base de datos

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


