package eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID; // Para generar códigos únicos

public class HU030C3AsignarCodigoUnico {
    public static void main(String[] args) {
        // 1. Declaración e inicialización de una lista de eventos
        List<String> eventos = new ArrayList<>();

        // 2. Agregando algunos eventos a la lista
        eventos.add("Taller de Control Emocional");
        eventos.add("Seminario sobre Trastorno Límite de la Personalidad (TLP)");
        eventos.add("Jornada de Mindfulness y Ansiedad");
        eventos.add("Charla sobre Depresión y Autoestima");
        eventos.add("Grupo de Apoyo para Jóvenes con Ansiedad");
        eventos.add("Conferencia: Salud Mental en el Trabajo");

        // 3. Permitir al usuario agregar un evento manualmente
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese un nuevo evento: ");
        String nuevoEvento = sc.nextLine();
        if (!nuevoEvento.isBlank()) {
            eventos.add(nuevoEvento);
        }

        // --- Validar si la lista está vacía ---
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos registrados en este momento.");
        } else {
            System.out.println("--- Eventos con Código Único 🧩 ---\n");

            // --- Requisito: Recorrer lista con for-each y asignar código ---
            int secuencia = 1; // Código incremental opcional
            for (String eventoRegistrado : eventos) {
                // Generar UUID simulado + número incremental
                String codigoUnico = "EVENTO-" + secuencia + "-" + UUID.randomUUID().toString().substring(0, 6);

                // Mostrar en consola
                System.out.println(codigoUnico + " → " + eventoRegistrado);

                secuencia++;
            }

            System.out.println("\n---  Fin de la lista de eventos  ---");
        }
    }
}
