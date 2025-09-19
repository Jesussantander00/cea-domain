package com.cea.domain;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Crear un Profesor
        Profesor profesor = new Profesor("p1", "Jhony Gutiérrez", new Email("jhony@institucion.com"));

        // Crear un Curso con capacidad de 2 alumnos
        Curso curso = new Curso("c1", "Desarrollo Web", new Precio(500.0), 2);

        // Crear alumnos
        Alumno alumno1 = new Alumno("a1", "Jesús Quintana", new Email("jesus@correo.com"));
        Alumno alumno2 = new Alumno("a2", "Daniel Santander", new Email("daniel@correo.com"));

        // Servicio de inscripción
        InscripcionService servicio = new InscripcionService();

        // Inscribir alumnos
        servicio.inscribir(curso, alumno1, LocalDate.now());
        servicio.inscribir(curso, alumno2, LocalDate.now());

        // Intentar inscribir un tercero (debe fallar por invariante)
        try {
            Alumno alumno3 = new Alumno("a3", "Andrea Pérez", new Email("andrea@correo.com"));
            servicio.inscribir(curso, alumno3, LocalDate.now());
        } catch (Exception e) {
            System.out.println("⚠️ Error esperado: " + e.getMessage());
        }

        // Crear evento de dominio
        EventoDominio evento = new EventoDominio("Curso creado");
        System.out.println("✅ Evento registrado: " + evento.getNombre() + " en " + evento.getFecha());
    }
}
