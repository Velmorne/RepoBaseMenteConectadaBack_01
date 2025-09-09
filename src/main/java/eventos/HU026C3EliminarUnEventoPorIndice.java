package eventos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

public class HU026C3EliminarUnEventoPorIndice {

    // Usamos 'record' para tener una clase de datos inmutable y concisa
    private record Evento(String titulo) {
        @Override
        public String toString() {
            return titulo;
        }
    }

    public static void main(String[] args) {
        List<Evento> eventos = new ArrayList<>(List.of(
                new Evento("Taller sobre control emocional"),
                new Evento("Charla sobre depresión"),
                new Evento("Conferencia sobre ansiedad"),
                new Evento("Taller de autoestima"),
                new Evento("Charla sobre estrés laboral"),
                new Evento("Seminario de salud mental")
        ));

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n--- Menú de gestión de eventos ---");
                mostrarEventosConIndice(eventos);

                System.out.println("\nEscribe el índice del evento a eliminar o 's' para salir:");
                String entrada = scanner.nextLine().trim();

                if (entrada.equalsIgnoreCase("s")) {
                    System.out.println("\uD83D\uDE0A¡Gracias por usar el gestor de eventos!\uD83D\uDE0A");
                    break;
                }

                try {
                    int indice = Integer.parseInt(entrada);
                    eliminarEventoPorIndice(eventos, indice);
                } catch (NumberFormatException e) {
                    System.out.println("\n❌ Error: Entrada no válida. Por favor, introduce un número o 's'.");
                }
            }
        }
    }

    private static void mostrarEventosConIndice(List<Evento> listaEventos) {
        if (listaEventos.isEmpty()) {
            System.out.println("La lista de eventos está vacía.");
        } else {
            System.out.println("\n➖➖➖ Lista de eventos ➖➖➖");
            for (int i = 0; i < listaEventos.size(); i++) {
                System.out.println("Índice " + i + ": " + listaEventos.get(i));
            }
        }
    }

    private static void eliminarEventoPorIndice(List<Evento> listaEventos, int indice) {
        if (indice >= 0 && indice < listaEventos.size()) {
            Evento eventoEliminado = listaEventos.remove(indice);
            System.out.println("\n✅ El evento '" + eventoEliminado + "' ha sido eliminado con éxito.");
        } else {
            System.out.println("\n❌ Error: El índice introducido (" + indice + ") no es válido.");
        }
    }
}