package com.cea.domain;

import java.time.LocalDate;

public class EventoDominio {
    private String nombre;
    private LocalDate fecha;

    public EventoDominio(String nombre) {
        this.nombre = nombre;
        this.fecha = LocalDate.now(); // asigna la fecha automáticamente
    }

    public EventoDominio(String nombre, LocalDate fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
