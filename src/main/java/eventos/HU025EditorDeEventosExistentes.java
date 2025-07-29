package eventos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class HU025EditorDeEventosExistentes {
    // Clase que representa un evento con nombre y fecha
    static class Evento {
        String nombre;
        LocalDateTime fecha;

        // Constructor del evento: asigna nombre y fecha
        Evento(String nombre, LocalDateTime fecha) {
            this.nombre = nombre;
            this.fecha = fecha;
        }

        // Método que devuelve el evento en formato de texto legible
        String mostrar() {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return "Evento: " + nombre + " | Fecha: " + fecha.format(formato);
        }
    }

    // Método principal del programa
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lista de eventos
        ArrayList<Evento> eventos = new ArrayList<>();
        eventos.add(new Evento("Taller de Control Emocional", LocalDateTime.now().plusDays(5)));
        eventos.add(new Evento("Seminario sobre Trastorno Límite de la Personalidad (TLP)", LocalDateTime.now().plusWeeks(1)));
        eventos.add(new Evento("Jornada de Mindfulness y Ansiedad", LocalDateTime.now().plusMonths(1)));
        eventos.add(new Evento("Charla sobre Depresión y Autoestima", LocalDateTime.now().plusDays(15)));
        eventos.add(new Evento("Grupo de Apoyo para Jóvenes con Ansiedad", LocalDateTime.now().plusWeeks(2)));
        eventos.add(new Evento("Conferencia: Salud Mental en el Trabajo", LocalDateTime.now().plusDays(10)));


        //Busca y edita eventos o salir :)
        boolean continuar = true;
        while (continuar) {
            System.out.print("\nIngresa el nombre del evento que deseas buscar: ");
            String busqueda = scanner.nextLine().toLowerCase();

            boolean encontrado = false;

            // Buscar coincidencias por nombre en la lista de eventos
            for (Evento ev : eventos) {
                if (ev.nombre.toLowerCase().contains(busqueda)) {
                    System.out.println("Evento encontrado:");
                    System.out.println(ev.mostrar());

                    System.out.print("¿Deseas editar este evento? (si/no): ");
                    String respuesta = scanner.nextLine().toLowerCase();

                    if (respuesta.equals("si")) {
                        // Permitir modificar el nombre del evento
                        System.out.print("Nuevo nombre (deja vacío si no deseas cambiarlo): ");
                        String nuevoNombre = scanner.nextLine();
                        if (!nuevoNombre.isEmpty()) {
                            ev.nombre = nuevoNombre;
                        }

                        // Permitir modificar la fecha
                        System.out.print("Nueva fecha (formato: yyyy-MM-dd HH:mm) o deja vacío: ");
                        String nuevaFechaTexto = scanner.nextLine();
                        if (!nuevaFechaTexto.isEmpty()) {
                            try {
                                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                LocalDateTime nuevaFecha = LocalDateTime.parse(nuevaFechaTexto, formato);
                                ev.fecha = nuevaFecha;
                            } catch (Exception e) {
                                System.out.println("Fecha inválida. No se modificó.");
                            }
                        }

                        System.out.println("Evento actualizado:");
                        System.out.println(ev.mostrar());
                    }

                    // Si desea editar otro evento o finalizar
                    System.out.print("¿Deseas editar otro evento o finalizar? (otro/finalizar): ");
                    String seguir = scanner.nextLine().toLowerCase();
                    if (seguir.equals("finalizar")) {
                        continuar = false;
                    }
                    encontrado = true;
                    break;
                }
            }

            // Si no se encuentra el evento, dar opción de buscar otro o finalizar
            if (!encontrado) {
                System.out.println("Evento no encontrado.");
                System.out.print("¿Deseas buscar otro evento o finalizar? (otro/finalizar): ");
                String seguir = scanner.nextLine().toLowerCase();
                if (seguir.equals("finalizar")) {
                    continuar = false;
                }
            }
        }
        System.out.println("\nPrograma finalizado.");
        scanner.close();
    }
}