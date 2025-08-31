package eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class HU034C3MostrarEventosPorFecha {

    private static class Evento {
        private final String titulo;
        private final String lugar;
        private final String fecha;
        private final String link;

        public Evento(String titulo, String lugar, String fecha, String link) {
            if (link == null || link.isBlank()) {
                throw new IllegalArgumentException("El enlace no puede estar vacío");
            }
            this.titulo = titulo;
            this.lugar = lugar;
            this.fecha = fecha;
            this.link = link;
        }

        @Override
        public String toString() {
            return "• " + titulo + " — " + fecha + " @ " + lugar + "\n   Link: " + link;
        }

        public String getFecha() {
            return fecha;
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("es", "CO"));

        List<Evento> eventos = new ArrayList<>(List.of(
                new Evento("Taller de Control Emocional", "Centro de Bienestar", "22-julio", "https://ejemplo.com/taller"),
                new Evento("Charla sobre Depresión", "Fundación Mente Sana", "25-julio", "https://ejemplo.com/charla"),
                new Evento("Conferencia sobre Ansiedad", "Auditorio U. Nacional", "30-julio", "https://ejemplo.com/conferencia"),
                new Evento("Taller de Autoestima", "Centro Psicológico Zenith", "2-agosto", "https://ejemplo.com/autoestima"),
                new Evento("Charla sobre Estrés Laboral", "Cámara de Comercio", "5-agosto", "https://ejemplo.com/estres"),
                new Evento("Seminario de Salud Mental", "Universidad Central", "10-agosto", "https://ejemplo.com/seminario")
        ));

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== Menú de eventos ===");
                System.out.println("1. Ver todos los eventos");
                System.out.println("2. Mostrar eventos por fecha");
                System.out.println("3. Salir");
                System.out.print("Elige una opción: ");
                String opcion = sc.nextLine().trim();

                switch (opcion) {
                    case "1" -> {
                        if (eventos.isEmpty()) {
                            System.out.println("No hay eventos registrados.");
                        } else {
                            System.out.println("\nLista completa de eventos:");
                            eventos.forEach(System.out::println);
                        }
                    }
                    case "2" -> {
                        System.out.print("Escribe la fecha a buscar (ejemplo: 25-julio): ");
                        String fecha = sc.nextLine().trim().toLowerCase();
                        List<Evento> encontrados = buscarPorFecha(eventos, fecha);

                        if (encontrados.isEmpty()) {
                            System.out.println("No hay eventos para la fecha \"" + fecha + "\".");
                        } else {
                            System.out.println("\nEventos encontrados:");
                            encontrados.forEach(System.out::println);
                        }
                    }
                    case "3" -> {
                        System.out.println("¡Gracias por usar el sistema de eventos!");
                        return;
                    }
                    default -> System.out.println("Opción no válida. Intenta de nuevo.");
                }
            }
        }
    }

    private static List<Evento> buscarPorFecha(List<Evento> eventos, String fecha) {
        List<Evento> resultado = new ArrayList<>();
        for (Evento e : eventos) {
            if (e.getFecha().equalsIgnoreCase(fecha)) {
                resultado.add(e);
            }
        }
        return resultado;
    }
}

