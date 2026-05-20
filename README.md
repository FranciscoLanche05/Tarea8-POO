# 🎓 Sistema de Gestión Académica — POO Java

> Práctica integradora de Programación Orientada a Objetos  
> **Asignatura:** Programación Orientada a Objetos · **Período:** 2026-A  
> **Institución:** Escuela Politécnica Nacional — ESFOT

---

## 📋 Descripción

Aplicación de consola en Java que gestiona registros de **estudiantes** y **docentes** de una institución educativa. Implementa un menú CRUD interactivo con validaciones completas, aplicando los cuatro pilares de la Programación Orientada a Objetos.

---

## 🗂️ Estructura del proyecto

```
📦 poo-crud-java/
 ┣ 📄 src/
 ┃ ┣ 📄 Persona.java        ← Clase padre abstracta
 ┃ ┣ 📄 Estudiante.java     ← Subclase (hereda de Persona)
 ┃ ┣ 📄 Docente.java        ← Subclase (hereda de Persona)
 ┃ ┗ 📄 Main.java           ← Menú CRUD + validaciones
 ┗ 📄 README.md
```

---

## 🧱 Diagrama de clases (UML)

```
              ┌─────────────────────────┐
              │        Persona          │  << abstracta >>
              │─────────────────────────│
              │ - cedula: String        │
              │ - nombreCompleto: String│
              │ - edad: int             │
              │─────────────────────────│
              │ + getCedula(): String   │
              │ + getNombre(): String   │
              │ + getEdad(): int        │
              │ + mostrarDatos(): void  │  << abstracto >>
              └────────────┬────────────┘
                           │  herencia
              ┌────────────┴────────────┐
              │                         │
 ┌────────────▼────────────┐  ┌─────────▼────────────────┐
 │       Estudiante        │  │         Docente           │
 │─────────────────────────│  │──────────────────────────│
 │ - carrera: String       │  │ - asignatura: String     │
 │─────────────────────────│  │──────────────────────────│
 │ + getCarrera(): String  │  │ + getAsignatura(): String│
 │ + mostrarDatos(): void  │  │ + mostrarDatos(): void   │
 └─────────────────────────┘  └──────────────────────────┘
```

---

## ⚙️ Conceptos POO aplicados

### 🔷 Herencia
La clase `Persona` actúa como clase padre y concentra los atributos comunes (`cédula`, `nombre completo`, `edad`). Las subclases `Estudiante` y `Docente` **heredan** esos atributos usando `extends` y agregan sus propios datos específicos (`carrera` y `asignatura`), evitando duplicación de código.

```java
public class Estudiante extends Persona {
    private String carrera;
    // ...
}
```

### 🔷 Encapsulamiento
Todos los atributos son `private`. El acceso y modificación se realiza exclusivamente a través de métodos **getters** y **setters**, protegiendo la integridad de los datos.

```java
private String cedula;         // atributo privado
public String getCedula() { return cedula; }  // acceso controlado
```

### 🔷 Polimorfismo y @Override
Cada subclase sobreescribe el método `mostrarDatos()` declarado como abstracto en `Persona`. Al recorrer el `ArrayList<Persona>`, Java invoca automáticamente la versión correcta según el tipo real del objeto.

```java
@Override
public void mostrarDatos() {
    System.out.println("  [ESTUDIANTE]");
    System.out.println("  Carrera: " + carrera);
    // ...
}
```

### 🔷 Abstracción
`Persona` es una clase **abstracta** que no puede instanciarse directamente. Define el contrato `mostrarDatos()` que cada subclase está obligada a implementar.

---

## 🗄️ CRUD con ArrayList

El sistema utiliza `ArrayList<Persona>` como estructura dinámica de almacenamiento. No se usan arreglos de tamaño fijo.

| Operación | Método ArrayList | Descripción |
|-----------|-----------------|-------------|
| **CREATE** | `add()` | Registra un nuevo estudiante o docente |
| **READ** | `get()` + `for` | Muestra todos los registros con su posición |
| **UPDATE** | `set()` | Reemplaza un objeto en la posición indicada |
| **DELETE** | `remove()` | Elimina el objeto de la posición indicada |

```java
// Ejemplo CREATE
personas.add(new Estudiante(cedula, nombre, edad, carrera));

// Ejemplo READ
for (int i = 0; i < personas.size(); i++) {
    personas.get(i).mostrarDatos();
}

// Ejemplo UPDATE
personas.set(pos, new Estudiante(cedula, nombre, edad, carrera));

// Ejemplo DELETE
personas.remove(pos);
```

---

## 🛡️ Validaciones y manejo de excepciones (try-catch)

El sistema **nunca se cierra** por errores del usuario. Cada tipo de error tiene su propio mensaje y permite continuar la ejecución.

```java
private static int leerEntero(String mensaje) {
    while (true) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("  Error: debe ingresar solo numeros.");
        }
    }
}
```

| Situación | Mensaje mostrado |
|-----------|-----------------|
| Opción de menú inválida (`8`, `-1`, `hola`) | `Error: opción inválida. Intente nuevamente.` |
| Entrada no numérica (`veinte`, `abc`) | `Error: debe ingresar solo números.` |
| Posición inexistente (`99`) | `Error: Registro no encontrado.` |
| Campo vacío (nombre, cédula, carrera) | `Error: campo obligatorio.` |
| Cédula ya registrada | `Error: ya existe una persona con esa cédula.` |

---

## ⭐ Retos adicionales implementados

- ✅ **Cédula única:** impide registrar dos personas con la misma cédula
- ✅ **Confirmación antes de eliminar:** solicita `s/n` antes de borrar un registro
- ✅ **Conteo por tipo:** muestra cuántos estudiantes y docentes hay tras cada registro

---

## 🖥️ Ejemplo de ejecución

```
========================================
  SISTEMA DE GESTION ACADEMICA
========================================
  1. Registrar persona
  2. Mostrar registros
  3. Actualizar registro
  4. Eliminar registro
  5. Salir
========================================
Ingrese una opcion: 1

-- REGISTRAR PERSONA --
  Seleccione tipo:
  1. Estudiante
  2. Docente
  Opcion: 1
  Ingrese cedula: 1723456789
  Ingrese nombre completo: Ana Lopez
  Ingrese edad: 20
  Ingrese carrera: Software
  Registro agregado correctamente.
  Total registros -> Estudiantes: 1  |  Docentes: 0
```

---

## 🚀 Cómo ejecutar el proyecto

### Requisitos
- JDK 21 o superior → [Descargar aquí](https://www.oracle.com/java/technologies/downloads/)

### Pasos

```bash
# 1. Clonar el repositorio
git clone https://github.com/tu-usuario/poo-crud-java.git
cd poo-crud-java

# 2. Compilar las clases
javac -d out src/*.java

# 3. Ejecutar el programa
java -cp out src.Main
```
