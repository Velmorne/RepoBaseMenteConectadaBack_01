package eventos;

import java.util.*;

/**
 * Buscador de eventos que permite buscar por palabra clave
 */
public class BuscadorEventos {
    
    // Lista de eventos disponibles
    private static List<Evento> eventos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Clase que representa un evento
     */
    static class Evento {
        private String nombre;
        private String fecha;
        private String tipo;
        private String lugar;
        
        public Evento(String nombre, String fecha, String tipo, String lugar) {
            this.nombre = nombre;
            this.fecha = fecha;
            this.tipo = tipo;
            this.lugar = lugar;
        }
        
        // Método para obtener todo el texto del evento para búsquedas
        public String getTextoCompleto() {
            return nombre + " " + tipo + " " + lugar;
        }
        
        @Override
        public String toString() {
            return "• " + nombre + " - " + fecha + " @ " + lugar + " [" + tipo + "]";
        }
    }
    
    public static void main(String[] args) {
        // Inicializar eventos de ejemplo
        inicializarEventos();
        
        // Ejecutar buscador
        ejecutarBuscador();
    }
    
    /**
     * Inicializa eventos de ejemplo
     */
    private static void inicializarEventos() {
        eventos.add(new Evento("Taller de Control Emocional", "22-julio-2024", "taller", "Centro de Bienestar"));
        eventos.add(new Evento("Seminario sobre Depresión", "25-julio-2024", "seminario", "Auditorio U. Nacional"));
        eventos.add(new Evento("Jornada de Mindfulness", "30-julio-2024", "jornada", "Parque de los Deseos"));
        eventos.add(new Evento("Charla sobre Ansiedad", "5-agosto-2024", "charla", "Fundación Mente Sana"));
        eventos.add(new Evento("Conferencia de Salud Mental", "15-agosto-2024", "conferencia", "Cámara de Comercio"));
        eventos.add(new Evento("Grupo de Apoyo", "20-agosto-2024", "grupo", "Centro Psicológico"));
    }
    
    /**
     * Ejecuta el buscador en bucle hasta que el usuario decida salir
     */
    private static void ejecutarBuscador() {
        while (true) {
            System.out.println("\n=== BUSCADOR DE EVENTOS ===");
            System.out.print("Ingresa palabra clave para buscar (o 'salir' para terminar): ");
            String termino = scanner.nextLine().trim();
            
            // Si el usuario quiere salir
            if (termino.equalsIgnoreCase("salir")) {
                System.out.println("¡Gracias por usar el buscador!");
                break;
            }
            
            // Si no ingresa nada, continuar
            if (termino.isEmpty()) {
                System.out.println("No se ingresó término de búsqueda.");
                continue;
            }
            
            // Buscar eventos
            buscarEventos(termino);
        }
    }
    
    /**
     * Busca eventos que contengan la palabra clave
     */
    private static void buscarEventos(String termino) {
        List<Evento> eventosEncontrados = new ArrayList<>();
        String terminoLower = termino.toLowerCase();
        
        // Buscar en todos los eventos
        for (Evento evento : eventos) {
            if (evento.getTextoCompleto().toLowerCase().contains(terminoLower)) {
                eventosEncontrados.add(evento);
            }
        }
        
        // Mostrar resultados
        if (eventosEncontrados.isEmpty()) {
            System.out.println("No se encontraron eventos que contengan: \"" + termino + "\"");
        } else {
            System.out.println("\nEventos encontrados (" + eventosEncontrados.size() + " resultados):");
            for (int i = 0; i < eventosEncontrados.size(); i++) {
                System.out.println((i + 1) + ". " + eventosEncontrados.get(i));
            }
        }
    }
}