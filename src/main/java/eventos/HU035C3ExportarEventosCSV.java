package eventos;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class EventoSimulacion {
    private String nombre;
    private String descripcion;
    private String fecha;
    private String link;

    public EventoSimulacion(String nombre, String descripcion, String fecha, String link) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.link = link;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "EventoSimulacion{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha='" + fecha + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}

public class HU035C3ExportarEventosCSV {

    public static void main(String[] args) {

        List<EventoSimulacion> eventos = new ArrayList<>();
        eventos.add(new EventoSimulacion("Conferencia Java", "Aprende sobre Java", "2025-09-01", "https://evento-java.com"));
        eventos.add(new EventoSimulacion("Taller Spring Boot", "Desarrollo backend", "2025-09-05", "https://spring-boot-taller.com"));
        eventos.add(new EventoSimulacion("Webinar IA", "Inteligencia Artificial", "2025-09-10", "https://ia-webinar.com"));

        // Exportar a CSV
        String rutaArchivo = "eventos.csv";
        exportarEventosCSV(eventos, rutaArchivo);
        System.out.println("✅ Archivo CSV generado en: " + rutaArchivo);
    }

    public static void exportarEventosCSV(List<EventoSimulacion> eventos, String rutaArchivo) {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            // Encabezado CSV
            writer.append("Nombre,Descripcion,Fecha,Link\n");

            // Datos de eventos
            for (EventoSimulacion evento : eventos) {
                writer.append(evento.getNombre()).append(",")
                        .append(evento.getDescripcion()).append(",")
                        .append(evento.getFecha()).append(",")
                        .append(evento.getLink()).append("\n");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("❌ Error al exportar CSV: " + e.getMessage());
        }
    }
}


