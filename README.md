# Parking Céntrico 24/7

Sistema de gestión de estacionamiento desarrollado en **Java**. Permite gestionar la operación completa de un estacionamiento: clientes, empleados, vehículos, entradas y salidas, contratos mensuales y servicios adicionales, además de contar con un módulo de reportes y persistencia de datos.

> Proyecto grupal realizado para **Programación 2** (2° semestre) Ingeniería Electrónica - ORT Uruguay. 

---

## Screenshots

| Gestión de clientes y vehículos | Servicios adicionales  |
|---|---|
| ![Client and vehicle panels](screenshots/clients-vehicles.png) | ![Additional services panel](screenshots/services.png) |

| Gestión de empleados | Diagrama de clases UML |
|---|---|
| ![Employee panel](screenshots/employees.png) | ![UML diagram](screenshots/uml.png) |

---

## Funcionalidades

- **Clientes**: registro, edición y eliminación con validación de cédula
- **Empleados**: registro y seguimiento de movimientos
- **Vehículos**: registro y seguimiento de estado
- **Entradas y salidas**: registro de movimientos, duración y observaciones
- **Contratos mensuales**: asociación de contratos con clientes y vehículos
- **Servicios adicionales**: registro y seguimiento de costos
- **Reportes**: movimientos, clientes, empleados y estadías
- **Tema oscuro / claro**: configuración persistente entre sesiones
- **Persistencia**: almacenamiento mediante serialización de objetos de Java
  
---

## Arquitectura

El proyecto implementa una arquitectura **MVC (Model-View-Controller)**:

    src/
    ├── dominio/                    # Modelo de dominio: Cliente, Empleado, Vehiculo,
    │                               #   Contrato, Movimiento, Entrada, Salida,
    │                               #   ServicioAdicional, Sistema
    ├── interfaz/                   # Interfaz gráfica Swing - un panel por módulo
    └── parkingcentrico24_7/
        ├── Main.java               # Punto de entrada
        ├── GestorSistema.java      # Capa de persistencia - serializa/deserializa
        │                           #   el estado completo del sistema en disco
        └── MiniJuego.java          # Minijuego integrado

**Decisiones principales de POO:**

- `Persona` es una clase abstracta extendida por `Cliente` y `Empleado`.
- `Movimiento` es una clase abstracta extendida por `Entrada` y `Salida`.
- `Sistema` actúa como modelo central, almacenando las listas de entidades y exponiendo la lógica de negocio del sistema.
- `GestorSistema` gestiona todas las operaciones de entrada/salida; el resto de la aplicación no accede directamente al sistema de archivos.

El diagrama completo de clases UML se encuentra en `screenshots/uml.png`.

---

## Tecnologías

| Tecnología | Uso |
|---|---|
| **Java 24** | Lenguaje de programación |
| **Swing** | Interfaz gráfica |
| **Java Serialization** | Persistencia de datos |
| **JDatePicker** | Selector de fechas |
| **Apache Ant** | Sistema de construcción (`build.xml`) |

---

## Cómo ejecutar

**Requisito:** Java 24 o superior.

Descargar `ParkingCentrico24_7_FAT.jar` desde [Releases](../../releases) y ejecutar:

    java -jar ParkingCentrico24_7_FAT.jar

No requiere instalación. Los datos se guardan automáticamente en un archivo local al salir de la aplicación.

---

## Autores

- **Nyah Rüting**
- **Facundo Esquivel**

ORT Uruguay - Ingeniería Electrónica / Ingeniería de Sistemas
