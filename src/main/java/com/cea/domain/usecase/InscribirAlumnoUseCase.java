package com.cea.domain.usecase;

/**
 * Puerto de entrada (IN) que define el caso de uso de inscribir un alumno en un curso.
 */
public interface InscribirAlumnoUseCase {
    void inscribir(String alumnoId, String cursoId);
}
