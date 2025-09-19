package com.cea.domain;

import java.util.Objects;

public class Alumno {
    private final String nombre;
    private final String apellido;
    private final Email email;

    public Alumno(String nombre, String apellido, Email email) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del alumno no puede estar vacío");
        }
        if (apellido == null || apellido.isBlank()) {
            throw new IllegalArgumentException("El apellido del alumno no puede estar vacío");
        }
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = Objects.requireNonNull(email, "El email no puede ser nulo");
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public Email getEmail() {
        return email;
    }
}
