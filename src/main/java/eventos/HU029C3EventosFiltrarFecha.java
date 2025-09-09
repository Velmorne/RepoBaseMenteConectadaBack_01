package eventos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HU029C3EventosFiltrarFecha {

    // Clase interna para representar un Evento
    static class Evento {
        private String nombre;
        private LocalDateTime fecha;
        private String descripcion;

        public Evento(String nombre, LocalDateTime fecha, String descripcion) {
            this.nombre = nombre;
            this.fecha = fecha;
            this.descripcion = descripcion;
        }

        // Getters y Setters
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public LocalDateTime getFecha() { return fecha; }
        public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return String.format("Evento: %s | Fecha: %s | Descripción: %s",
                    nombre, fecha.format(formatter), descripcion);
        }
    }

    private List<Evento> eventos;

    public HU029C3EventosFiltrarFecha() {
        this.eventos = new ArrayList<>();
    }

    /**
     * Añade un evento a la lista
     */
    public void añadirEvento(String nombre, LocalDateTime fecha, String descripcion) {
        eventos.add(new Evento(nombre, fecha, descripcion));
    }

    /**
     * Ordena los eventos por fecha de forma ascendente
     * Usa método de ordenamiento simple (Collections.sort)
     */
    public void ordenarEventosPorFechaAscendente() {
        Collections.sort(eventos, new Comparator<Evento>() {
            @Override
            public int compare(Evento e1, Evento e2) {
                return e1.getFecha().compareTo(e2.getFecha());
            }
        });
    }

    public void ordenarEventosSimple() {
        eventos.sort((e1, e2) -> e1.getFecha().compareTo(e2.getFecha()));
    }

    public void imprimirListaOrdenada() {
        System.out.println("=== EVENTOS ORDENADOS POR FECHA (ASCENDENTE) ===");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println((i + 1) + ". " + eventos.get(i));
        }
    }


    public boolean verificarOrdenCorrecto() {
        for (int i = 0; i < eventos.size() - 1; i++) {
            if (eventos.get(i).getFecha().isAfter(eventos.get(i + 1).getFecha())) {
                return false;
            }
        }
        return true;
    }


    public List<Evento> getEventos() {
        return new ArrayList<>(eventos);
    }

    public void limpiarEventos() {
        eventos.clear();
    }

    public void imprimirEventos() {
        System.out.println("=== LISTA DE EVENTOS ACTUAL ===");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println((i + 1) + ". " + eventos.get(i));
        }
    }

    public static void main(String[] args) {
        HU029C3EventosFiltrarFecha hu029c3Eventos = new HU029C3EventosFiltrarFecha();

        // Añadir algunos eventos de ejemplo
        hu029c3Eventos.añadirEvento("Reunión equipo",
                LocalDateTime.of(2024, 3, 15, 10, 30),
                "Reunión mensual del equipo");

        hu029c3Eventos.añadirEvento("Presentación proyecto",
                LocalDateTime.of(2024, 2, 28, 14, 0),
                "Presentación final del proyecto");

        hu029c3Eventos.añadirEvento("Capacitación Java",
                LocalDateTime.of(2024, 4, 5, 9, 0),
                "Curso avanzado de Java");

        hu029c3Eventos.añadirEvento("Entrega documentación",
                LocalDateTime.of(2024, 1, 20, 17, 0),
                "Entrega de documentos técnicos");

        // Imprimir eventos actuales (sin ordenar)
        hu029c3Eventos.imprimirEventos();

        System.out.println("\n=== APLICANDO ORDENAMIENTO ===");

        hu029c3Eventos.ordenarEventosPorFechaAscendente();

        hu029c3Eventos.imprimirListaOrdenada();

        System.out.println("\n=== VERIFICACIÓN ===");
        if (hu029c3Eventos.verificarOrdenCorrecto()) {
            System.out.println("✓ La lista está correctamente ordenada por fecha ascendente");
        } else {
            System.out.println("✗ Error: La lista no está ordenada correctamente");
        }


    }
}