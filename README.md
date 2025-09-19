# 📚 Proyecto CEA - Arquitectura Hexagonal

Este proyecto implementa un dominio de ejemplo para un **Centro de Enseñanza y Aprendizaje (CEA)** siguiendo los principios de **Arquitectura Hexagonal** (Ports & Adapters) y **Domain-Driven Design (DDD)**.  
El núcleo de negocio se mantiene independiente de frameworks e infraestructura, facilitando pruebas, mantenibilidad y extensibilidad.

---

## ⚡ Arquitectura Hexagonal (Preguntas 1 - 15)

1. **Definición:**  
   La Arquitectura Hexagonal es un estilo donde el **dominio** está aislado de la infraestructura. Su propósito es que la lógica de negocio no dependa de frameworks ni tecnologías externas.

2. **Puertos y Adaptadores:**  
   Se llama así porque el dominio se conecta con el exterior mediante **puertos (interfaces)** y **adaptadores (implementaciones concretas)**.

3. **Comparación con 3 capas:**  
   - En capas tradicionales, la lógica depende de la infraestructura.  
   - En hexagonal, la dependencia siempre va **hacia adentro** (al dominio).  
   - Ambas pueden complicarse si no se respetan los límites.

4. **Principio de dependencias hacia adentro:**  
   Todas las dependencias apuntan al dominio. El dominio no depende de nada externo.

5. **Relación con DIP (SOLID):**  
   Cumple el principio de Inversión de Dependencias porque el dominio depende de **abstracciones (interfaces)** y no de implementaciones concretas.

6. **Domino / Aplicación / Infraestructura:**  
   - **Dominio:** Reglas de negocio.  
   - **Aplicación:** Orquesta casos de uso.  
   - **Infraestructura:** Bases de datos, frameworks, UI, APIs externas.

7. **Puertos y Adaptadores:**  
   - **Puerto IN:** interfaz que define casos de uso (ej. inscribir alumno).  
   - **Puerto OUT:** interfaz que define operaciones hacia infraestructura (ej. guardar curso).  
   - **Adaptador IN:** implementación de entrada (ej. formulario web).  
   - **Adaptador OUT:** implementación de salida (ej. repositorio JDBC).

8. **Capa de Anticorrupción (ACL):**  
   Traductor entre el dominio y sistemas externos. Evita contaminar el modelo de negocio.  
   Sin ACL, el dominio estaría lleno de dependencias de terceros.

9. **Entidad / VO / DTO / Mapper:**  
   - **Entidad:** Tiene identidad (ej. Alumno).  
   - **VO:** Inmutable, sin identidad (ej. Email, Precio).  
   - **DTO:** Objeto para transportar datos (ej. respuesta API).  
   - **Mapper:** Convierte entre dominio ↔ DTO / JPA.

10. **Lenguaje Ubicuo:**  
   Lenguaje común entre negocio y desarrolladores. Evita ambigüedades.  
   Ejemplo: *Alumno, Curso, Inscripción, Profesor*.

11. **DDD y Hexagonal:**  
   Son complementarios. Hexagonal define la arquitectura, DDD define el diseño del dominio.

12. **Patrones de diseño usados:**  
   - **Factory:** creación de entidades con validaciones.  
   - **Specification:** validar condiciones de negocio.  
   - **Domain Service:** reglas que involucran varias entidades.

13. **Ventajas / Retos:**  
   - ✅ Dominio independiente.  
   - ✅ Fácil de probar.  
   - ✅ Cambiar tecnología sin reescribir reglas.  
   - ⚠️ Mayor curva de aprendizaje.  
   - ⚠️ Más clases e interfaces.  
   - ⚠️ Requiere disciplina.

14. **Pruebas:**  
   Hexagonal favorece unit testing porque el dominio no depende de infraestructura. También facilita integration testing aislando adaptadores.

15. **Cambio de tecnologías externas:**  
   Ejemplo: puedes cambiar la base de datos de MySQL a MongoDB sin tocar el dominio, solo cambiando el adaptador OUT.

---

## 🏗️ Diseño Conceptual del Dominio (16 - 25)

- **Glosario (Lenguaje Ubicuo):** Alumno, Profesor, Curso, Inscripción, Precio, Email, Evento.  
- **Entidades:** Alumno, Profesor, Curso.  
- **Value Objects:** Email, Precio.  
- **Agregado raíz:** Curso (asegura consistencia en inscripciones).  
- **Eventos de dominio:** `CursoCreado`, `AlumnoInscrito`.  
- **Servicios de dominio:** `InscripcionService`.  
- **Patrones aplicados:** Factory para crear cursos, Specification para validar inscripciones.

---

## 💻 Implementación en Java (26 - 35)

- **Entidades con invariantes:** `Curso`, `Alumno`, `Profesor`.  
- **Value Objects inmutables:** `Email`, `Precio`.  
- **Agregado raíz:** `Curso` controla sus inscripciones.  
- **Servicios de dominio:** `InscripcionService`.  
- **Eventos de dominio:** `EventoDominio`.  
- **Excepciones específicas:** `EmailInvalidoException`, `PrecioInvalidoException`.  
- **Patrones aplicados:** Factory, Specification, Domain Service.  
- **Independencia:** El dominio no importa librerías externas, solo Java puro.  

---

## 🔌 Puertos y Adaptadores (36 - 47)

- **Puertos IN (ejemplos):**  
  ```java
  interface InscribirAlumnoUseCase {
      void inscribir(String alumnoId, String cursoId);
  }
