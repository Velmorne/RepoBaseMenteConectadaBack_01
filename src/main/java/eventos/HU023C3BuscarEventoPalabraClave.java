package eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class HU023C3BuscarEventoPalabraClave {
    // Modelo de evento
    private record Evento(String titulo, String lugar, String fecha) {
        @Override
        public String toString() {
            return "• " + titulo + " — " + fecha + " @ " + lugar;
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("es", "CO"));

        // Lista de eventos sobre salud mental
        List<Evento> eventos = List.of(
                new Evento("Taller de Control Emocional", "Centro de Bienestar", "22-julio"),
                new Evento("Seminario sobre Trastorno Límite de la Personalidad (TLP)", "Auditorio U. Nacional", "25-julio"),
                new Evento("Jornada de Mindfulness y Ansiedad", "Parque de los Deseos", "30-julio"),
                new Evento("Charla sobre Depresión y Autoestima", "Fundación Mente Sana", "5-agosto"),
                new Evento("Grupo de Apoyo para Jóvenes con Ansiedad", "Centro Psicológico Zenith", "10-agosto"),
                new Evento("Conferencia: Salud Mental en el Trabajo", "Cámara de Comercio", "15-agosto"),
                new Evento("Conferencia: Salud Mental en la Universidad", "Cámara de Comercio", "20-agosto")
        );

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== Buscador de eventos de salud mental ===");
                System.out.print("Escribe palabra clave (o ENTER para salir): ");
                String termino = sc.nextLine().trim().toLowerCase(); //"término" referente a "Palabra"

                if (termino.isEmpty()) {
                    System.out.println("¡Gracias por usar el buscador! 🧠");
                    break;
                }

                List<Evento> coincidencias = filtrar(eventos, termino);

                if (coincidencias.isEmpty()) {
                    System.out.println("No se encontraron eventos para \"" + termino + "\".");
                } else {
                    System.out.println("Resultados encontrados:");
                    coincidencias.forEach(System.out::println);
                }
            }
        }
    }

    private static List<Evento> filtrar(List<Evento> eventos, String termino) {
        List<Evento> resultado = new ArrayList<>();
        for (Evento e : eventos) {
            if (e.titulo.toLowerCase().contains(termino)) {
                resultado.add(e);
            }
        }
        return resultado;
    }
}
