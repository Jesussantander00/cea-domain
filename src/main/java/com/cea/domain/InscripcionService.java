package com.cea.domain;

import java.time.LocalDate;

public class InscripcionService {

    public EventoDominio inscribir(Curso curso, Alumno alumno, LocalDate fecha) {
        if (curso == null || alumno == null || fecha == null) {
            throw new IllegalArgumentException("Curso, alumno y fecha no pueden ser nulos");
        }
        String nombreEvento = "Inscripción de " + alumno.getNombreCompleto() +
                              " al curso " + curso.getNombre();
        return new EventoDominio(nombreEvento, fecha);
    }
}
