package eventos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HU039C3VerEventosOrdenCronologico {

    private record Evento(String titulo, LocalDate fecha) {
        @Override
        public String toString() {
            return "Fecha: " + fecha + " | Evento: " + titulo;
        }
    }
    public static void main(String[] args) {
        List<Evento> eventos = new ArrayList<>(List.of(
                new Evento("Taller sobre control emocional", LocalDate.of(2025, 10, 20)),
                new Evento("Charla sobre depresión", LocalDate.of(2025, 8, 15)),
                new Evento("Conferencia sobre ansiedad", LocalDate.of(2026, 1, 30)),
                new Evento("Taller de autoestima", LocalDate.of(2025, 9, 5)),
                new Evento("Charla sobre estrés laboral", LocalDate.of(2025, 11, 12)),
                new Evento("Seminario de salud mental", LocalDate.of(2025, 7, 22)),
                new Evento("Seminario de salud mental", LocalDate.of(2025, 12, 01)),
                new Evento("Seminario de salud mental", LocalDate.of(2025, 07, 16))
        ));

        System.out.println("--- Buscando próximos eventos ---");
        mostrarEventosFuturosOrdenados(eventos);
    }

    private static void mostrarEventosFuturosOrdenados(List<Evento> listaEventos) {
        LocalDate hoy = LocalDate.now();

        List<Evento> eventosFuturos = listaEventos.stream()
                .filter(evento -> evento.fecha().isAfter(hoy))
                .sorted(Comparator.comparing(Evento::fecha))
                .collect(Collectors.toList());

        if (eventosFuturos.isEmpty()) {
            System.out.println("\n❌ No se encontraron eventos futuros.");
        } else {
            System.out.println("\n🗓️ Próximos eventos ordenados cronológicamente:");
            for (Evento evento : eventosFuturos) {
                System.out.println("- " + evento);
            }
        }

        System.out.println("\n📊 Total de eventos futuros encontrados: " + eventosFuturos.size());
    }
}

