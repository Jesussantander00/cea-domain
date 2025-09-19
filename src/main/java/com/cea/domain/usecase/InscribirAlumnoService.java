package com.cea.domain.usecase;

import com.cea.domain.Alumno;
import com.cea.domain.Curso;
import com.cea.domain.ports.RepositorioCursos;

/**
 * Servicio de aplicación que implementa el caso de uso de inscribir alumno.
 * Orquesta entidades y delega en el repositorio.
 */
public class InscribirAlumnoService implements InscribirAlumnoUseCase {

    private final RepositorioCursos repositorioCursos;

    public InscribirAlumnoService(RepositorioCursos repositorioCursos) {
        this.repositorioCursos = repositorioCursos;
    }

    @Override
    public void inscribir(String alumnoId, String cursoId) {
        Curso curso = repositorioCursos.buscarPorId(cursoId)
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        Alumno alumno = new Alumno(alumnoId, "Nombre de ejemplo");
        curso.inscribir(alumno);

        repositorioCursos.guardar(curso);
    }
}
