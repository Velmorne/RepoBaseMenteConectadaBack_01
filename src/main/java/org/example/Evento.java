package org.example;

public class Evento {
    private String nombre;
    private String fecha;
    private String tipo;

    public Evento(String nombre, String fecha, String tipo) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "nombre='" + nombre + '\'' +
                ", fecha='" + fecha + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
