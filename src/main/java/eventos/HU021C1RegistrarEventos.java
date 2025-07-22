package eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HU021C1RegistrarEventos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Evento> eventos = new ArrayList<>(); // Lista para almacenar eventos

        while (true) {
            System.out.println("=== Agregar Evento ===");
            System.out.print("Agrega el nombre del evento: ");
            String nombreEvento = scanner.nextLine();

            System.out.print("Agrega la fecha del evento (ej. 2025-07-20): ");
            String fechaEvento = scanner.nextLine();

            System.out.print("Agrega el tipo de evento (Charla, taller, etc): ");
            String tipoEvento = scanner.nextLine();

            if (!nombreEvento.trim().isEmpty() && !fechaEvento.trim().isEmpty() && !tipoEvento.trim().isEmpty()) {
                Evento evento = new Evento(nombreEvento, fechaEvento, tipoEvento);
                eventos.add(evento); // Guardamos el evento en la lista
                System.out.println("¡Evento agregado correctamente!");
            } else {
                System.out.println("Por favor, complete todos los campos.");
            }

            System.out.print("¿Quieres agregar otro evento? (si/no): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (!respuesta.equals("si") && !respuesta.equals("sí")) {
                break; // Salir del ciclo si la respuesta no es sí
            }
        }

        System.out.println("\n=== Lista de eventos registrados ===");
        for (Evento e : eventos) {
            System.out.println(e);
        }
    }

    // Clase interna Evento
    static class Evento {
        private String nombre;
        private String fecha;
        private String tipo;

        public Evento(String nombre, String fecha, String tipo) {
            this.nombre = nombre;
            this.fecha = fecha;
            this.tipo = tipo;
        }

        @Override
        public String toString() {
            return "• " + nombre + " — " + fecha + " @ " + tipo;
        }
    }
}


