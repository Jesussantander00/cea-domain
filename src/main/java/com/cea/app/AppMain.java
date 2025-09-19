package com.cea.app;

import com.cea.domain.*;

public class AppMain {
    public static void main(String[] args) {
        // Crear un repositorio en memoria (adaptador OUT)
        RepositorioCursos repositorio = new RepositorioCursosMemoria();

        // Crear un curso usando el dominio
        Curso curso = new Curso("Arquitectura Hexagonal", 
                                "Curso práctico de DDD y Ports & Adapters",
                                new Precio(1200.0),
                                40);

        // Guardar curso en el repositorio
        repositorio.guardar(curso);

        // Recuperar curso por ID
        repositorio.buscarPorId(curso.getId()).ifPresent(c -> {
            System.out.println("✅ Curso encontrado en el repositorio:");
            System.out.println("ID: " + c.getId());
            System.out.println("Nombre: " + c.getNombre());
            System.out.println("Precio: $" + c.getPrecio().getValor());
        });
    }
}
