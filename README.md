# 📚 Proyecto CEA - Arquitectura Hexagonal

Este proyecto implementa un dominio de ejemplo para un **Centro de Enseñanza y Aprendizaje (CEA)** siguiendo los principios de **Arquitectura Hexagonal (Ports & Adapters)** y **Domain-Driven Design (DDD)**.  

El núcleo de negocio se mantiene **independiente de frameworks e infraestructura**, lo que facilita **pruebas, mantenibilidad y extensibilidad**.

---

## ⚡ Arquitectura Hexagonal (Preguntas 1 - 15)

### Definición
La **Arquitectura Hexagonal** es un estilo donde el dominio está **aislado de la infraestructura**. Su propósito es que la lógica de negocio no dependa de marcos ni tecnologías externas.

### Puertos y Adaptadores
Se llama así porque el dominio se conecta con el exterior mediante:
- **Puertos (interfaces)** → Definen la comunicación.  
- **Adaptadores (implementaciones concretas)** → Conectan el dominio con bases de datos, APIs, frameworks, etc.

### Comparación con arquitectura en 3 capas
- En capas tradicionales, la lógica de negocio depende de la infraestructura.  
- En hexagonal, **la dependencia siempre va hacia adentro** (hacia el dominio).  
- Ambas pueden complicarse si no se respetan los límites.

### Principio de dependencias hacia adentro
Todas las dependencias apuntan al dominio.  
El dominio **no depende de nada externo**.

### Relación con DIP (SOLID)
Cumple el **principio de Inversión de Dependencias** porque:
- El dominio depende de **abstracciones (interfaces)**.
- No depende de **implementaciones concretas**.

### Dominio / Aplicación / Infraestructura
- **Dominio**: Reglas de negocio.  
- **Aplicación**: Orquesta casos de uso.  
- **Infraestructura**: Bases de datos, frameworks, UI, APIs externas.  

### Puertos y Adaptadores
- **Puerto IN**: interfaz que define casos de uso (ej. inscribir alumno).  
- **Puerto OUT**: interfaz que define operaciones hacia infraestructura (ej. guardar curso).  
- **Adaptador IN**: implementación de entrada (ej. controlador REST, formulario web).  
- **Adaptador OUT**: implementación de salida (ej. repositorio JDBC).  

### Capa de Anticorrupción (ACL)
Traductor entre el dominio y sistemas externos.  
Evita **contaminar el modelo de negocio** con dependencias externas.  
Sin ACL, el dominio estaría lleno de dependencias de terceros.

### Entidad / VO / DTO / Mapeador
- **Entidad**: Tiene identidad (ej. Alumno).  
- **VO (Value Object)**: Inmutable, sin identidad (ej. Email, Precio).  
- **DTO**: Objeto para transportar datos (ej. respuesta API).  
- **Mapper**: Convierte entre dominio ↔ DTO / JPA.  

### Lenguaje Ubicuo
Lenguaje común entre **negocio y desarrolladores**.  
Evita ambigüedades.  
Ejemplo: Alumno, Curso, Inscripción, Profesor.  

### DDD y Hexagonal
Son **complementarios**:  
- **Hexagonal** define la arquitectura.  
- **DDD** define el diseño del dominio.  

### Patrones de diseño usados
- **Fábrica**: creación de entidades con validaciones.  
- **Especificación**: validar condiciones de negocio.  
- **Servicio de Dominio**: reglas que involucran a varias entidades.  

### Ventajas / Desventajas
✅ Dominio independiente.  
✅ Fácil de probar.  
✅ Permite cambiar tecnología sin reescribir reglas.  
⚠️ Mayor curva de aprendizaje.  
⚠️ Más clases e interfaces.  
⚠️ Requiere disciplina.  

### Pruebas
- Favorece pruebas **unitarias** porque el dominio no depende de infraestructura.  
- Facilita pruebas de **integración** aislando adaptadores.  

### Cambio de tecnologías externas
Ejemplo: puedes cambiar la base de datos de **MySQL a MongoDB** sin tocar el dominio, solo cambiando el adaptador OUT.

---

## 🏗️ Diseño Conceptual del Dominio (16 - 25)

- **Glosario (Lenguaje Ubicuo):** Alumno, Profesor, Curso, Inscripción, Precio, Email, Evento.  
- **Entidades:** Alumno, Profesor, Curso.  
- **Objetos de Valor:** Email, Precio.  
- **Agregado raíz:** Curso (asegura consistencia en inscripciones).  
- **Eventos de dominio:** `CursoCreado`, `AlumnoInscrito`.  
- **Servicios de dominio:** `InscripcionService`.  
- **Patrones aplicados:**  
  - Factory para crear cursos.  
  - Especificación para validar inscripciones.  

---

## 💻 Implementación en Java (26 - 35)

- **Entidades con invariantes:** `Curso`, `Alumno`, `Profesor`.  
- **Objetos de valor inmutables:** `Email`, `Precio`.  
- **Agregado raíz:** `Curso` controla sus inscripciones.  
- **Servicios de dominio:** `InscripcionService`.  
- **Eventos de dominio:** `EventoDominio`.  
- **Excepciones específicas:** `EmailInvalidoException`, `PrecioInvalidoException`.  
- **Patrones aplicados:** Factory, Specification, Domain Service.  
- **Independencia:** El dominio no importa librerías externas, solo Java puro.  

---

## 🔌 Puertos y Adaptadores (36 - 47)

### Puerto IN (ejemplo)
```java
interface InscribirAlumnoUseCase {
    void inscribir(String alumnoId, String cursoId);
}
### Puerto OUT (ejemplo)
import java.util.Optional;

interface RepositorioCursos {
    void guardar(Curso curso);
    Optional<Curso> buscarPorId(String id);
}
