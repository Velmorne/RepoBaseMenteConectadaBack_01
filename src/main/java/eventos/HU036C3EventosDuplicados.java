package eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HU036C3EventosDuplicados {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Evento> eventos = new ArrayList<>();

        while (true) {
            System.out.println("=== Agregar Evento ===");
            System.out.print("Agrega el nombre del evento: ");
            String nombreEvento = scanner.nextLine().trim();

            System.out.print("Agrega la fecha del evento (ej. 2025-07-20): ");
            String fechaEvento = scanner.nextLine().trim();

            System.out.print("Agrega el tipo de evento (Charla, taller, etc): ");
            String tipoEvento = scanner.nextLine().trim();

            if (!nombreEvento.isEmpty() && !fechaEvento.isEmpty() && !tipoEvento.isEmpty()) {


                boolean existeDuplicado = false;
                for (Evento e : eventos) {
                    if (e.getNombre().equalsIgnoreCase(nombreEvento)) {
                        existeDuplicado = true;
                        break;
                    }
                }

                if (existeDuplicado) {
                    System.out.println("⚠️ Ya existe un evento con ese nombre. No se agregó.");
                } else {
                    Evento evento = new Evento(nombreEvento, fechaEvento, tipoEvento);
                    eventos.add(evento);
                    System.out.println("¡Evento agregado correctamente!");
                }

            } else {
                System.out.println("⚠️ Por favor, complete todos los campos.");
            }
        }
    }

    public static class Evento {
        private String nombre;
        private String fecha;
        private String tipo;

        public Evento(String nombre, String fecha, String tipo) {
            this.nombre = nombre;
            this.fecha = fecha;
            this.tipo = tipo;
        }

        public String getNombre() {
            return nombre;
        }

        public String getFecha() {
            return fecha;
        }

        public String getTipo() {
            return tipo;
        }
    }
}
