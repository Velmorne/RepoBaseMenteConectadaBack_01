package eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HU038C3CambiarCategoriaEvento {

    static class Evento {
        private String titulo;
        private String lugar;
        private String fecha;
        private String categoria;
        private String link;

        public Evento(String titulo, String lugar, String fecha, String categoria, String link) {
            this.titulo = titulo;
            this.lugar = lugar;
            this.fecha = fecha;
            this.categoria = categoria;
            this.link = link;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public void setLink(String link) {
            this.link = link;
        }

        @Override
        public String toString() {
            return "Evento: " + titulo +
                    " | Fecha: " + fecha +
                    " | Lugar: " + lugar +
                    " | Categoría: " + categoria +
                    " | Link: " + link;
        }
    }

    public static void main(String[] args) {
        List<Evento> eventos = new ArrayList<>(List.of(
                new Evento("Taller de Control Emocional", "Centro de Bienestar", "22-julio", "Psicología", "https://link1.com"),
                new Evento("Charla sobre Depresión", "Fundación Mente Sana", "25-julio", "Salud Mental", "https://link2.com"),
                new Evento("Conferencia sobre Ansiedad", "Auditorio U. Nacional", "30-julio", "Bienestar", "https://link3.com"),
                new Evento("Taller de Autoestima", "Centro Psicológico Zenith", "2-agosto", "Crecimiento Personal", "https://link4.com")
        ));

        try (Scanner sc = new Scanner(System.in)) {
            boolean continuar = true;

            while (continuar) {
                System.out.println("\n=== MENÚ PRINCIPAL ===");
                System.out.println("1. Ver todos los eventos");
                System.out.println("2. Cambiar categoría de un evento");
                System.out.println("3. Salir");
                System.out.print("Selecciona una opción: ");
                String opcion = sc.nextLine().trim();

                switch (opcion) {
                    case "1":
                        System.out.println("\n=== Lista de eventos ===");
                        for (int i = 0; i < eventos.size(); i++) {
                            System.out.println((i + 1) + ". " + eventos.get(i));
                        }
                        break;

                    case "2":
                        mostrarEventos(eventos);
                        System.out.print("Selecciona el número del evento para cambiar la categoría: ");
                        int indiceCat = leerIndice(sc, eventos.size());
                        if (indiceCat != -1) {
                            System.out.print("Ingresa la nueva categoría: ");
                            String nuevaCategoria = sc.nextLine().trim();
                            if (!nuevaCategoria.isEmpty()) {
                                eventos.get(indiceCat).setCategoria(nuevaCategoria);
                                System.out.println("✅ Categoría actualizada con éxito.");
                            } else {
                                System.out.println("❌ Categoría no puede estar vacía.");
                            }
                        }
                        break;

                    case "3":
                        continuar = false;
                        System.out.println("👋 Saliendo del programa...");
                        break;

                    default:
                        System.out.println("❌ Opción inválida. Intenta nuevamente.");
                }
            }
        }
    }

    private static void mostrarEventos(List<Evento> eventos) {
        System.out.println("\n=== Lista de eventos ===");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println((i + 1) + ". " + eventos.get(i).getTitulo());
        }
    }

    private static int leerIndice(Scanner sc, int size) {
        try {
            int indice = Integer.parseInt(sc.nextLine().trim());
            if (indice >= 1 && indice <= size) {
                return indice - 1;
            } else {
                System.out.println("❌ Número fuera de rango.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida. Debes ingresar un número.");
        }
        return -1;
    }
}
