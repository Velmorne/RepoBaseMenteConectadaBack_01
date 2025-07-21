package org.example;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Evento> eventos = new ArrayList<>();  // Lista para almacenar eventos

            while (true) {
            System.out.println("===Agregar Eventos===");
            System.out.println("Agrega el nombre del evento:");
            String nombreEvento = scanner.nextLine();

            System.out.println("Agrega la fecha del evento (ej. 2025-07-20):");
            String fechaEvento = scanner.nextLine();

            System.out.println("Agrega el tipo de evento (Charla, taller, etc):");
            String tipoEvento = scanner.nextLine();

            if (!nombreEvento.trim().isEmpty() && !fechaEvento.trim().isEmpty() && !tipoEvento.trim().isEmpty()) {
                Evento evento = new Evento(nombreEvento, fechaEvento, tipoEvento);
                eventos.add(evento);  // Guardamos el evento en la lista
                System.out.println("¡Evento agregado correctamente!");
            } else {
                System.out.println("Por favor, complete todos los campos.");
            }

            System.out.println("¿Quieres agregar otro evento? (sí/no):");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (!respuesta.equals("sí") && !respuesta.equals("si")) {
                break; // Salir del ciclo si la respuesta no es sí
            }
        }

        // Mostrar todos los eventos guardados
        System.out.println("\n===Eventos Registrados===\n");
        //Recorre la lista de eventos con un ciclo
            for (Evento e : eventos) {
            System.out.println(e);
        }
        Integer cantidad = eventos.size();
        if (cantidad == 0) //Se muestra mensaje si no hay eventos registrados
            System.out.println("No se han registrado eventos, pronto se agregarán más.");
        else
            System.out.println("Se han registrado " + cantidad + " eventos hasta ahora");

    }
}
