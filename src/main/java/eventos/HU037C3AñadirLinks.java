package eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class HU037C3AñadirLinks {

    private static class Evento {
        private final String titulo;
        private final String lugar;
        private final String fecha;
        private String link;

        public Evento(String titulo, String lugar, String fecha, String link) {
            if (link == null || link.isBlank()) {
                throw new IllegalArgumentException("El enlace no puede estar vacío");
            }
            this.titulo = titulo;
            this.lugar = lugar;
            this.fecha = fecha;
            this.link = link;
        }

        public void setLink(String nuevoLink) {
            if (nuevoLink == null || nuevoLink.isBlank()) {
                throw new IllegalArgumentException("El enlace no puede estar vacío");
            }
            this.link = nuevoLink;
        }

        @Override
        public String toString() {
            return "• " + titulo + " — " + fecha + " @ " + lugar + "\n   Link: " + link;
        }

        public String getTitulo() {
            return titulo;
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("es", "CO"));

        // Lista de eventos
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
                System.out.println("1. Mostrar Eventos");
                System.out.println("2. Editar link de un evento");
                System.out.println("3. Salir");
                System.out.print("Elige una opción: ");
                String opcion = sc.nextLine().trim();

                switch (opcion) {
                    case "1" -> {
                        System.out.println("\n=== Lista de eventos ===");
                        for (int i = 0; i < eventos.size(); i++) {
                            System.out.println((i + 1) + ". " + eventos.get(i));
                        }
                    }
                    case "2" -> {
                        System.out.print("Escribe el título del evento para editar su link: ");
                        String titulo = sc.nextLine().trim().toLowerCase();
                        Evento evento = buscarEvento(eventos, titulo);

                        if (evento == null) {
                            System.out.println("No se encontró un evento con ese título.");
                        } else {
                            System.out.println("Evento encontrado:");
                            System.out.println(evento);
                            System.out.print("Escribe el nuevo link: ");
                            String nuevoLink = sc.nextLine().trim();

                            try {
                                evento.setLink(nuevoLink);
                                System.out.println("✅ Link actualizado correctamente.");
                            } catch (IllegalArgumentException e) {
                                System.out.println("❌ Error: " + e.getMessage());
                            }
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

    private static void mostrarEventos(List<HU038C3CambiarCategoriaEvento.Evento> eventos) {
        System.out.println("\n=== Lista de eventos ===");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println((i + 1) + ". " + eventos.get(i).getTitulo());
        }
    }

    private static List<Evento> filtrarPorTitulo(List<Evento> eventos, String tituloBuscado) {
        List<Evento> resultado = new ArrayList<>();
        for (Evento e : eventos) {
            if (e.getTitulo().toLowerCase().contains(tituloBuscado)) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    private static Evento buscarEvento(List<Evento> eventos, String tituloBuscado) {
        for (Evento e : eventos) {
            if (e.getTitulo().toLowerCase().contains(tituloBuscado)) {
                return e;
            }
        }
        return null;
    }
}

