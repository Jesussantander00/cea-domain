package com.cea.domain;

import java.util.Objects;

public class Curso {
    private final String nombre;
    private final String descripcion;
    private final Precio precio;
    private final int duracionHoras;

    public Curso(String nombre, String descripcion, Precio precio, int duracionHoras) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del curso no puede estar vacío");
        }
        if (duracionHoras <= 0) {
            throw new IllegalArgumentException("La duración del curso debe ser mayor a 0");
        }
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = Objects.requireNonNull(precio, "El precio no puede ser nulo");
        this.duracionHoras = duracionHoras;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Precio getPrecio() {
        return precio;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }
}
