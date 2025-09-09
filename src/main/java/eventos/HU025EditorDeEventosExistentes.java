package eventos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class HU025EditorDeEventosExistentes {

    // Clase que representa un evento con nombre, fecha y suscriptores
    static class Evento {
        String nombre;
        LocalDateTime fecha;
        int suscriptores;

        // Constructor del evento
        Evento(String nombre, LocalDateTime fecha) {
            this.nombre = nombre;
            this.fecha = fecha;
            this.suscriptores = 0;
        }

        // Método para mostrar información del evento
        String mostrar() {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return "Evento: " + nombre + " | Fecha: " + fecha.format(formato) + " | Suscriptores: " + suscriptores;
        }

        // Método para suscribirse
        void suscribirse() {
            suscriptores++;
        }
    }

    // Método principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lista de eventos iniciales
        ArrayList<Evento> eventos = new ArrayList<>();
        eventos.add(new Evento("Taller de Control Emocional", LocalDateTime.now().plusDays(5)));
        eventos.add(new Evento("Seminario sobre Trastorno Límite de la Personalidad (TLP)", LocalDateTime.now().plusWeeks(1)));
        eventos.add(new Evento("Jornada de Mindfulness y Ansiedad", LocalDateTime.now().plusMonths(1)));
        eventos.add(new Evento("Charla sobre Depresión y Autoestima", LocalDateTime.now().plusDays(15)));
        eventos.add(new Evento("Grupo de Apoyo para Jóvenes con Ansiedad", LocalDateTime.now().plusWeeks(2)));
        eventos.add(new Evento("Conferencia: Salud Mental en el Trabajo", LocalDateTime.now().plusDays(10)));

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Crear evento");
            System.out.println("2. Ver eventos");
            System.out.println("3. Editar evento");
            System.out.println("4. Suscribirse a un evento");
            System.out.println("5. Eliminar evento");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre del nuevo evento: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Fecha del evento (yyyy-MM-dd HH:mm): ");
                    String fechaTexto = scanner.nextLine();
                    try {
                        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime fecha = LocalDateTime.parse(fechaTexto, formato);
                        eventos.add(new Evento(nombre, fecha));
                        System.out.println("✅ Evento creado exitosamente.");
                    } catch (Exception e) {
                        System.out.println("❌ Fecha inválida. No se creó el evento.");
                    }
                }
                case 2 -> {
                    if (eventos.isEmpty()) {
                        System.out.println("No hay eventos para mostrar.");
                    } else {
                        System.out.println("\n===== LISTA DE EVENTOS =====");
                        for (int i = 0; i < eventos.size(); i++) {
                            System.out.println((i + 1) + ". " + eventos.get(i).mostrar());
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Ingrese palabra clave del evento a editar: ");
                    String palabraClave = scanner.nextLine().toLowerCase();

                    boolean encontrado = false;
                    for (Evento ev : eventos) {
                        if (ev.nombre.toLowerCase().contains(palabraClave)) {
                            System.out.println("Evento encontrado:");
                            System.out.println(ev.mostrar());

                            System.out.print("Nuevo nombre (deja vacío si no deseas cambiarlo): ");
                            String nuevoNombre = scanner.nextLine();
                            if (!nuevoNombre.isEmpty()) {
                                ev.nombre = nuevoNombre;
                            }

                            System.out.print("Nueva fecha (yyyy-MM-dd HH:mm) o deja vacío: ");
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

                            System.out.println("✅ Evento actualizado.");
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("❌ Evento no encontrado.");
                    }
                }
                case 4 -> {
                    // Suscribirse por palabra clave
                    if (eventos.isEmpty()) {
                        System.out.println("No hay eventos disponibles para suscribirse.");
                        break;
                    }

                    System.out.print("Ingrese una palabra clave del evento al que desea suscribirse: ");
                    String palabraClave = scanner.nextLine().toLowerCase();

                    ArrayList<Evento> coincidencias = new ArrayList<>();
                    for (Evento ev : eventos) {
                        if (ev.nombre.toLowerCase().contains(palabraClave)) {
                            coincidencias.add(ev);
                        }
                    }

                    if (coincidencias.isEmpty()) {
                        System.out.println("❌ No se encontraron eventos con esa palabra.");
                    } else {
                        System.out.println("\nEventos encontrados:");
                        for (int i = 0; i < coincidencias.size(); i++) {
                            System.out.println((i + 1) + ". " + coincidencias.get(i).mostrar());
                        }

                        System.out.print("Elige el número del evento para suscribirte: ");
                        int numElegido = scanner.nextInt();
                        scanner.nextLine();

                        if (numElegido > 0 && numElegido <= coincidencias.size()) {
                            Evento seleccionado = coincidencias.get(numElegido - 1);
                            seleccionado.suscribirse();
                            System.out.println("✅ Te has suscrito a: " + seleccionado.nombre);
                            System.out.println("📊 Total suscriptores: " + seleccionado.suscriptores);
                        } else {
                            System.out.println("Número inválido.");
                        }
                    }
                }
                case 5 -> {
                    System.out.print("Ingrese palabra clave del evento a eliminar: ");
                    String palabraClave = scanner.nextLine().toLowerCase();

                    Evento eventoAEliminar = null;
                    for (Evento ev : eventos) {
                        if (ev.nombre.toLowerCase().contains(palabraClave)) {
                            eventoAEliminar = ev;
                            break;
                        }
                    }

                    if (eventoAEliminar != null) {
                        eventos.remove(eventoAEliminar);
                        System.out.println("✅ Evento eliminado.");
                    } else {
                        System.out.println("❌ Evento no encontrado.");
                    }
                }
                case 6 -> {
                    continuar = false;
                    System.out.println("👋 Programa finalizado.");
                }
                default -> System.out.println("Opción inválida.");
            }
        }

        scanner.close();
    }
}
