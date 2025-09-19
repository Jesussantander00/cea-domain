package com.cea.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RepositorioCursosMemoria implements RepositorioCursos {

    private final Map<String, Curso> cursos = new HashMap<>();

    @Override
    public void guardar(Curso curso) {
        cursos.put(curso.getId(), curso);
    }

    @Override
    public Optional<Curso> buscarPorId(String id) {
        return Optional.ofNullable(cursos.get(id));
    }
}
