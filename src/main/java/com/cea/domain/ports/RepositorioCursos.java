package com.cea.domain.ports;

import com.cea.domain.Curso;

import java.util.Optional;

/**
 * Puerto de salida (OUT) que define cómo el dominio persiste cursos.
 */
public interface RepositorioCursos {
    void guardar(Curso curso);
    Optional<Curso> buscarPorId(String id);
}
