package eventos;

import java.util.ArrayList;
import java.util.List; // Solo necesitamos List y ArrayList para este ejemplo

public class HU022C3VerEventosRegistrados {
    public static void main(String[] args) {
        // 1. Declaración e inicialización de una lista
        List<String> eventos = new ArrayList<>();

        // 2. Agregando algunos eventos a la lista
        eventos.add("Taller de Control Emocional");
        eventos.add("Seminario sobre Trastorno Límite de la Personalidad (TLP)");
        eventos.add("Jornada de Mindfulness y Ansiedad");
        eventos.add("Charla sobre Depresión y Autoestima");
        eventos.add("Grupo de Apoyo para Jóvenes con Ansiedad");
        eventos.add("Conferencia: Salud Mental en el Trabajo");
        // --- Requisito 3: Indicar si la lista está vacía ---
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos registrados en este momento.");
        } else {
            System.out.println("--- Eventos Registrados 🧠---\n");
            // --- Requisito 1: Recorrer la lista con ciclos (for-each loop) ---
            // --- Requisito 2: Mostrar detalles en consola ---
            int numeroEvento = 1; // Para numerar los eventos
            for (String eventoRegistrado : eventos) {
                System.out.println(numeroEvento + ". " + eventoRegistrado);
                numeroEvento++;
            }
            System.out.println("\n--- Fin de la lista de eventos ---");
        }
    }
}