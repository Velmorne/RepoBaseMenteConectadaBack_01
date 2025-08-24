package eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Representa un evento con su nombre, código y contador de asistentes.
 */
class Evento {
    private String nombre;
    private String codigo;
    private int asistentes;

    public Evento(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.asistentes = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getAsistentes() {
        return asistentes;
    }

    public void incrementarAsistencia() {
        this.asistentes++;
    }
}

/**
 * Clase principal para la simulación de registro de asistencia a eventos.
 */
public class HU032C3SimularAsistenciaAlEvento {

    private static final List<Evento> eventos = new ArrayList<>(List.of(
            new Evento("Taller sobre control emocional", "EMOCION"),
            new Evento("Charla sobre depresión", "DEPRESION"),
            new Evento("Conferencia sobre ansiedad", "ANSIEDAD"),
            new Evento("Taller de autoestima", "AUTOESTIMA"),
            new Evento("Charla sobre estrés laboral", "ESTRES"),
            new Evento("Seminario de salud mental", "SALUD")
    ));

    public static void mostrarEventos() {
        System.out.println("--- Lista de Eventos Disponibles ---");
        for (Evento evento : eventos) {
            System.out.println("Código: " + evento.getCodigo() + " - " + evento.getNombre());
        }
        System.out.println("-------------------------------------");
    }

    public static void registrarAsistencia() {
        mostrarEventos();
        System.out.println("\nPara salir, escribe 'salir'.");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("\nIngrese el código del evento: ");
                String codigoIngresado = scanner.nextLine().toUpperCase();

                if (codigoIngresado.equals("SALIR")) {
                    System.out.println("Saliendo del registro de asistencia. ¡Hasta pronto!");
                    break; // Sale del bucle
                }

                boolean encontrado = false;
                for (Evento evento : eventos) {
                    if (evento.getCodigo().equals(codigoIngresado)) {
                        evento.incrementarAsistencia();
                        System.out.println("✅ Asistencia registrada para el evento: " + evento.getNombre() + ".");
                        System.out.println("Total de asistentes: " + evento.getAsistentes());
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    System.out.println("❌ Código de evento no válido. Por favor, intente de nuevo.");
                }
            }
        }
    }

    public static void main(String[] args) {
        registrarAsistencia();
    }
}