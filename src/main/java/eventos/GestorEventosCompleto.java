package eventos;

import java.util.*;

/**
 * Gestor de eventos que permite:
 * - Buscar eventos
 * - Editar eventos existentes
 * - Guardar lo editado
 * - Crear nuevos eventos
 * - Validación de datos con ciclos de reingreso
 */
public class GestorEventosCompleto {
    
    // Lista principal para almacenar todos los eventos
    private static List<Evento> eventos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Clase que representa un evento
     */
    static class Evento {
        private String nombre;       // Nombre del evento
        private String fecha;        // Fecha del evento
        private String tipo;         // Tipo del evento
        private String lugar;        // Lugar del evento
        
        // Constructor para crear un nuevo evento
        public Evento(String nombre, String fecha, String tipo, String lugar) {
            this.nombre = nombre;
            this.fecha = fecha;
            this.tipo = tipo;
            this.lugar = lugar;
        }
        
        // Getters y setters para acceder y modificar los atributos
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getFecha() { return fecha; }
        public void setFecha(String fecha) { this.fecha = fecha; }
        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }
        public String getLugar() { return lugar; }
        public void setLugar(String lugar) { this.lugar = lugar; }
        
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
        // Inicializar con algunos eventos de ejemplo
        inicializarEventosEjemplo();
        
        // Mostrar menú principal
        mostrarMenuPrincipal();
    }
    
    /**
     * Inicializa la aplicación con eventos de ejemplo
     */
    private static void inicializarEventosEjemplo() {
        eventos.add(new Evento("Taller de Control Emocional", "22-julio-2024", "taller", "Centro de Bienestar"));
        eventos.add(new Evento("Seminario sobre Depresión", "25-julio-2024", "seminario", "Auditorio U. Nacional"));
        eventos.add(new Evento("Jornada de Mindfulness", "30-julio-2024", "jornada", "Parque de los Deseos"));
        eventos.add(new Evento("Charla sobre Ansiedad", "5-agosto-2024", "charla", "Fundación Mente Sana"));
    }
    
    /**
     * Muestra el menú principal y maneja las opciones del usuario
     */
    private static void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("\n=== GESTOR DE EVENTOS ===");
            System.out.println("1. Buscar eventos");
            System.out.println("2. Crear nuevo evento");
            System.out.println("3. Editar evento existente");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            
            String opcion = scanner.nextLine().trim();
            
            switch (opcion) {
                case "1" -> buscarEventos();
                case "2" -> crearNuevoEvento();
                case "3" -> editarEvento();
                case "0" -> {
                    System.out.println("¡Gracias por usar el Gestor de Eventos!");
                    return;
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }
    
    /**
     * Busca eventos por palabra clave
     */
    private static void buscarEventos() {
        System.out.println("\n=== BUSCAR EVENTOS ===");
        System.out.print("Ingresa palabra clave para buscar: ");
        String termino = scanner.nextLine().trim().toLowerCase();
        
        // Si no ingresa nada, regresa al menú
        if (termino.isEmpty()) {
            System.out.println("No se ingresó término de búsqueda.");
            return;
        }
        
        // Lista para almacenar eventos encontrados
        List<Evento> eventosEncontrados = new ArrayList<>();
        
        // Buscar en todos los eventos
        for (Evento evento : eventos) {
            // Busca si el término aparece en el texto completo del evento
            if (evento.getTextoCompleto().toLowerCase().contains(termino)) {
                eventosEncontrados.add(evento);
            }
        }
        
        // Mostrar resultados
        if (eventosEncontrados.isEmpty()) {
            System.out.println("No se encontraron eventos que contengan: \"" + termino + "\"");
        } else {
            System.out.println("Eventos encontrados:");
            for (int i = 0; i < eventosEncontrados.size(); i++) {
                System.out.println((i + 1) + ". " + eventosEncontrados.get(i));
            }
        }
    }
    
    /**
     * Crea un nuevo evento solicitando todos los datos
     */
    private static void crearNuevoEvento() {
        System.out.println("\n=== CREAR NUEVO EVENTO ===");
        
        // Solicitar datos del evento con validación
        String nombre = solicitarDatoValido("Nombre del evento");
        String fecha = solicitarDatoValido("Fecha del evento (ej. 15-marzo-2024)");
        String tipo = solicitarDatoValido("Tipo de evento (charla, taller, seminario, etc.)");
        String lugar = solicitarDatoValido("Lugar del evento");
        
        // Crear y agregar el nuevo evento
        Evento nuevoEvento = new Evento(nombre, fecha, tipo, lugar);
        eventos.add(nuevoEvento);
        
        System.out.println("¡Evento creado exitosamente!");
        System.out.println("Evento creado: " + nuevoEvento);
    }
    
    /**
     * Solicita un dato al usuario hasta que ingrese información válida
     * Ciclo que se repite si el usuario ingresa datos incorrectos o vacíos
     */
    private static String solicitarDatoValido(String nombreCampo) {
        while (true) {
            System.out.print(nombreCampo + ": ");
            String dato = scanner.nextLine().trim();
            
            // Validar que no esté vacío
            if (!dato.isEmpty()) {
                return dato;
            }
            
            // Si está vacío, pedir de nuevo
            System.out.println("Error: Este campo no puede estar vacío. Por favor, ingresa un valor válido.");
        }
    }
    
    /**
     * Permite editar un evento existente
     */
    private static void editarEvento() {
        // Verificar si hay eventos para editar
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos registrados para editar.");
            return;
        }
        
        System.out.println("\n=== EDITAR EVENTO EXISTENTE ===");
        
        // Mostrar lista de eventos disponibles
        mostrarListaEventos();
        
        // Solicitar selección del evento con validación
        int indiceEvento = solicitarSeleccionEvento();
        
        // Si cancela, regresar
        if (indiceEvento == -1) {
            System.out.println("Operación cancelada.");
            return;
        }
        
        // Obtener el evento seleccionado
        Evento evento = eventos.get(indiceEvento);
        
        // Editar los campos del evento
        editarCamposEvento(evento);
        
        System.out.println("¡Evento editado y guardado exitosamente!");
        System.out.println("Evento actualizado: " + evento);
    }
    
    /**
     * Muestra la lista numerada de todos los eventos
     */
    private static void mostrarListaEventos() {
        System.out.println("Lista de eventos:");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println((i + 1) + ". " + eventos.get(i));
        }
    }
    
    /**
     * Solicita al usuario seleccionar un evento de la lista
     * Ciclo que se repite si ingresa un número inválido
     */
    private static int solicitarSeleccionEvento() {
        while (true) {
            System.out.print("Selecciona el número del evento a editar (0 para cancelar): ");
            String entrada = scanner.nextLine().trim();
            
            try {
                int numero = Integer.parseInt(entrada);
                
                // Si ingresa 0, cancelar operación
                if (numero == 0) {
                    return -1;
                }
                
                // Validar que el número esté en el rango válido
                if (numero >= 1 && numero <= eventos.size()) {
                    return numero - 1; // Convertir a índice (base 0)
                } else {
                    System.out.println("Error: Número fuera de rango. Ingresa un número entre 1 y " + eventos.size() + " (o 0 para cancelar).");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido.");
            }
        }
    }
    
    /**
     * Permite editar cada campo del evento
     * Los cambios se guardan automáticamente en el objeto
     */
    private static void editarCamposEvento(Evento evento) {
        System.out.println("\nEditando evento: " + evento.getNombre());
        System.out.println("Presiona ENTER para mantener el valor actual");
        
        // Editar nombre
        String nuevoNombre = editarCampo("Nombre", evento.getNombre());
        if (!nuevoNombre.isEmpty()) {
            evento.setNombre(nuevoNombre);
        }
        
        // Editar fecha
        String nuevaFecha = editarCampo("Fecha", evento.getFecha());
        if (!nuevaFecha.isEmpty()) {
            evento.setFecha(nuevaFecha);
        }
        
        // Editar tipo
        String nuevoTipo = editarCampo("Tipo", evento.getTipo());
        if (!nuevoTipo.isEmpty()) {
            evento.setTipo(nuevoTipo);
        }
        
        // Editar lugar
        String nuevoLugar = editarCampo("Lugar", evento.getLugar());
        if (!nuevoLugar.isEmpty()) {
            evento.setLugar(nuevoLugar);
        }
    }
    
    /**
     * Solicita al usuario editar un campo específico
     * Muestra el valor actual y permite mantenerlo o cambiarlo
     */
    private static String editarCampo(String nombreCampo, String valorActual) {
        System.out.print(nombreCampo + " [" + valorActual + "]: ");
        return scanner.nextLine().trim();
    }
}