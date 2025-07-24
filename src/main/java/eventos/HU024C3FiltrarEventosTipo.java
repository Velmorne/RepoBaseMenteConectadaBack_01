package eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class HU024C3FiltrarEventosTipo {
    // Modelo de evento con tipo
    private record Evento(String titulo, String lugar, String fecha, String tipo) {
        @Override
        public String toString() {
            return "• [" + tipo + "] " + titulo + " — " + fecha + " @ " + lugar;
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("es", "CO"));

        // Lista de eventos
        List<Evento> eventos = List.of(
                new Evento("Taller de Control Emocional", "Centro de Bienestar", "22-julio", "taller"),
                new Evento("Charla sobre Depresión", "Fundación Mente Sana", "25-julio", "charla"),
                new Evento("Conferencia sobre Ansiedad", "Auditorio U. Nacional", "30-julio", "conferencia"),
                new Evento("Taller de Autoestima", "Centro Psicológico Zenith", "2-agosto", "taller"),
                new Evento("Charla sobre Estrés Laboral", "Cámara de Comercio", "5-agosto", "charla"),
                new Evento("Seminario de Salud Mental", "Universidad Central", "10-agosto", "seminario")
        );

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== Filtrar eventos por tipo ===");
                System.out.print("Escribe el tipo de evento a buscar (charla, taller, etc.) o ENTER para salir: ");
                String tipo = sc.nextLine().trim().toLowerCase();

                if (tipo.isEmpty()) {
                    System.out.println("¡Gracias por usar el filtro de eventos!");
                    break;
                }

                List<Evento> filtrados = filtrarPorTipo(eventos, tipo);

                if (filtrados.isEmpty()) {
                    System.out.println("No se encontraron eventos del tipo \"" + tipo + "\".");
                } else {
                    System.out.println("Eventos encontrados:");
                    filtrados.forEach(System.out::println);
                }
            }
        }
    }

    private static List<Evento> filtrarPorTipo(List<Evento> eventos, String tipoBuscado) {
        List<Evento> resultado = new ArrayList<>();
        for (Evento e : eventos) {
            if (e.tipo.toLowerCase().equals(tipoBuscado)) {
                resultado.add(e);
            }
        }
        return resultado;
    }
}
