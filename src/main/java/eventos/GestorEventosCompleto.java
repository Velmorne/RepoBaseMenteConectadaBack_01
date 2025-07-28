package eventos;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Gestor completo de eventos que permite:
 * - Buscar eventos con palabras similares (búsqueda fuzzy)
 * - Crear nuevos eventos
 * - Editar eventos existentes
 * - Guardar/eliminar eventos
 * - Ver todos los eventos registrados
 */
public class GestorEventosCompleto {
    
    // Lista principal para almacenar todos los eventos
    private static List<Evento> eventos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Clase que representa un evento con todos los atributos necesarios
     */
    static class Evento {
        private String id;           // Identificador único del evento
        private String nombre;       // Nombre del evento
        private String fecha;        // Fecha del evento
        private String tipo;         // Tipo (charla, taller, conferencia, etc.)
        private String lugar;        // Lugar donde se realizará
        private String descripcion;  // Descripción detallada del evento
        
        // Constructor para crear un nuevo evento
        public Evento(String nombre, String fecha, String tipo, String lugar, String descripcion) {
            this.id = generarId();
            this.nombre = nombre;
            this.fecha = fecha;
            this.tipo = tipo;
            this.lugar = lugar;
            this.descripcion = descripcion;
        }
        
        // Genera un ID único para cada evento
        private String generarId() {
            return "EVT-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 1000);
        }
        
        // Getters y setters para acceder y modificar los atributos
        public String getId() { return id; }
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getFecha() { return fecha; }
        public void setFecha(String fecha) { this.fecha = fecha; }
        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }
        public String getLugar() { return lugar; }
        public void setLugar(String lugar) { this.lugar = lugar; }
        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
        
        // Método que devuelve toda la información del evento como texto
        public String getTextoCompleto() {
            return nombre + " " + tipo + " " + lugar + " " + descripcion;
        }
        
        @Override
        public String toString() {
            return String.format("• [%s] %s - %s @ %s\n  ID: %s | Descripción: %s",
                    tipo, nombre, fecha, lugar, id, descripcion);
        }
    }
    
    public static void main(String[] args) {
        // Inicializar con algunos eventos de ejemplo
        inicializarEventosEjemplo();
        
        // Configurar idioma español
        Locale.setDefault(new Locale("es", "CO"));
        
        // Mostrar menú principal
        mostrarMenuPrincipal();
    }
    
    /**
     * Inicializa la aplicación con eventos de ejemplo para demostrar funcionalidad
     */
    private static void inicializarEventosEjemplo() {
        eventos.add(new Evento("Taller de Control Emocional", "22-julio-2024", "taller", 
                "Centro de Bienestar", "Aprende técnicas para manejar tus emociones"));
        eventos.add(new Evento("Seminario sobre Trastorno Límite de Personalidad", "25-julio-2024", "seminario",
                "Auditorio U. Nacional", "Información sobre TLP y estrategias de manejo"));
        eventos.add(new Evento("Jornada de Mindfulness y Ansiedad", "30-julio-2024", "jornada",
                "Parque de los Deseos", "Práctica de mindfulness para reducir ansiedad"));
        eventos.add(new Evento("Charla sobre Depresión y Autoestima", "5-agosto-2024", "charla",
                "Fundación Mente Sana", "Estrategias para mejorar autoestima y combatir depresión"));
        eventos.add(new Evento("Conferencia de Salud Mental en el Trabajo", "15-agosto-2024", "conferencia",
                "Cámara de Comercio", "Cómo mantener bienestar mental en ambiente laboral"));
    }
    
    /**
     * Muestra el menú principal y maneja las opciones del usuario
     */
    private static void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("🧠 GESTOR COMPLETO DE EVENTOS DE SALUD MENTAL 🧠");
            System.out.println("=".repeat(50));
            System.out.println("1. 🔍 Buscar eventos (búsqueda inteligente)");
            System.out.println("2. ➕ Crear nuevo evento");
            System.out.println("3. 📝 Editar evento existente");
            System.out.println("4. 🗑️  Eliminar evento");
            System.out.println("5. 👁️  Ver todos los eventos");
            System.out.println("6. 📊 Estadísticas de eventos");
            System.out.println("0. ❌ Salir");
            System.out.println("=".repeat(50));
            System.out.print("Selecciona una opción: ");
            
            String opcion = scanner.nextLine().trim();
            
            switch (opcion) {
                case "1" -> buscarEventos();
                case "2" -> crearNuevoEvento();
                case "3" -> editarEvento();
                case "4" -> eliminarEvento();
                case "5" -> verTodosLosEventos();
                case "6" -> mostrarEstadisticas();
                case "0" -> {
                    System.out.println("¡Gracias por usar el Gestor de Eventos! 🧠💚");
                    return;
                }
                default -> System.out.println("❌ Opción no válida. Por favor, intenta de nuevo.");
            }
        }
    }
    
    /**
     * Implementa búsqueda inteligente de eventos con coincidencias difusas
     * Busca en nombre, tipo, lugar y descripción
     */
    private static void buscarEventos() {
        System.out.println("\n🔍 BÚSQUEDA INTELIGENTE DE EVENTOS");
        System.out.println("-".repeat(40));
        System.out.print("Ingresa palabra(s) clave para buscar: ");
        String termino = scanner.nextLine().trim().toLowerCase();
        
        if (termino.isEmpty()) {
            System.out.println("❌ No se ingresó ningún término de búsqueda.");
            return;
        }
        
        // Lista para almacenar eventos encontrados con su puntuación de relevancia
        List<EventoConPuntuacion> eventosEncontrados = new ArrayList<>();
        
        // Buscar en todos los eventos
        for (Evento evento : eventos) {
            int puntuacion = calcularPuntuacionBusqueda(evento, termino);
            if (puntuacion > 0) {
                eventosEncontrados.add(new EventoConPuntuacion(evento, puntuacion));
            }
        }
        
        // Ordenar por relevancia (puntuación más alta primero)
        eventosEncontrados.sort((a, b) -> Integer.compare(b.puntuacion, a.puntuacion));
        
        // Mostrar resultados
        if (eventosEncontrados.isEmpty()) {
            System.out.println("❌ No se encontraron eventos que coincidan con: \"" + termino + "\"");
            sugerirEventosSimilares(termino);
        } else {
            System.out.println("✅ Eventos encontrados (" + eventosEncontrados.size() + " resultados):");
            System.out.println("-".repeat(60));
            
            for (int i = 0; i < eventosEncontrados.size(); i++) {
                EventoConPuntuacion ecp = eventosEncontrados.get(i);
                System.out.println((i + 1) + ". " + ecp.evento);
                System.out.println("   📊 Relevancia: " + ecp.puntuacion + "/100");
                System.out.println();
            }
        }
    }
    
    /**
     * Calcula qué tan relevante es un evento para una búsqueda específica
     * Retorna un puntaje de 0-100 basado en coincidencias exactas y similares
     */
    private static int calcularPuntuacionBusqueda(Evento evento, String termino) {
        String textoEvento = evento.getTextoCompleto().toLowerCase();
        String[] palabrasBusqueda = termino.split("\\s+");
        int puntuacionTotal = 0;
        
        for (String palabra : palabrasBusqueda) {
            // Coincidencia exacta vale más puntos
            if (textoEvento.contains(palabra)) {
                puntuacionTotal += 30;
            }
            // Búsqueda de palabras similares (contiene parte de la palabra)
            else if (contieneSubcadena(textoEvento, palabra)) {
                puntuacionTotal += 15;
            }
            // Búsqueda fonética básica (palabras que suenan similar)
            else if (esSimilarFonetica(textoEvento, palabra)) {
                puntuacionTotal += 10;
            }
        }
        
        // Bonus por coincidencia en el nombre (es más relevante)
        if (evento.getNombre().toLowerCase().contains(termino)) {
            puntuacionTotal += 20;
        }
        
        return Math.min(puntuacionTotal, 100); // Máximo 100 puntos
    }
    
    /**
     * Verifica si una cadena contiene una subcadena con tolerancia a errores
     */
    private static boolean contieneSubcadena(String texto, String busqueda) {
        if (busqueda.length() < 3) return false;
        
        // Buscar subcadenas de la palabra
        for (int i = 0; i <= busqueda.length() - 3; i++) {
            String subcadena = busqueda.substring(i, i + 3);
            if (texto.contains(subcadena)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Implementa una búsqueda fonética básica para palabras similares
     */
    private static boolean esSimilarFonetica(String texto, String palabra) {
        // Mapeo básico de sonidos similares en español
        Map<String, String[]> sonidosSimilares = Map.of(
            "c", new String[]{"k", "qu"},
            "s", new String[]{"z", "c"},
            "b", new String[]{"v"},
            "g", new String[]{"j"},
            "y", new String[]{"ll", "i"}
        );
        
        for (Map.Entry<String, String[]> entrada : sonidosSimilares.entrySet()) {
            String original = entrada.getKey();
            for (String similar : entrada.getValue()) {
                String palabraModificada = palabra.replace(original, similar);
                if (texto.contains(palabraModificada)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Sugiere eventos similares cuando no se encuentran coincidencias exactas
     */
    private static void sugerirEventosSimilares(String termino) {
        System.out.println("💡 ¿Quizás te interese alguno de estos eventos?");
        
        // Mostrar eventos por tipo similar
        Set<String> tiposEventos = eventos.stream()
                .map(e -> e.getTipo())
                .collect(Collectors.toSet());
        
        System.out.println("📋 Tipos de eventos disponibles: " + String.join(", ", tiposEventos));
    }
    
    /**
     * Permite crear un nuevo evento solicitando todos los datos necesarios
     */
    private static void crearNuevoEvento() {
        System.out.println("\n➕ CREAR NUEVO EVENTO");
        System.out.println("-".repeat(30));
        
        // Solicitar datos del evento con validación
        String nombre = solicitarDatoObligatorio("Nombre del evento");
        String fecha = solicitarDatoObligatorio("Fecha (ej. 15-marzo-2024)");
        String tipo = solicitarTipoEvento();
        String lugar = solicitarDatoObligatorio("Lugar del evento");
        String descripcion = solicitarDatoOpcional("Descripción del evento");
        
        // Crear y agregar el nuevo evento
        Evento nuevoEvento = new Evento(nombre, fecha, tipo, lugar, descripcion);
        eventos.add(nuevoEvento);
        
        System.out.println("✅ ¡Evento creado exitosamente!");
        System.out.println("📋 Detalles del evento:");
        System.out.println(nuevoEvento);
    }
    
    /**
     * Solicita al usuario un tipo de evento de una lista predefinida
     */
    private static String solicitarTipoEvento() {
        String[] tiposDisponibles = {"charla", "taller", "conferencia", "seminario", "jornada", "grupo de apoyo"};
        
        System.out.println("Tipos de evento disponibles:");
        for (int i = 0; i < tiposDisponibles.length; i++) {
            System.out.println((i + 1) + ". " + tiposDisponibles[i]);
        }
        System.out.println((tiposDisponibles.length + 1) + ". Otro (especificar)");
        
        while (true) {
            System.out.print("Selecciona el tipo de evento (1-" + (tiposDisponibles.length + 1) + "): ");
            String opcion = scanner.nextLine().trim();
            
            try {
                int indice = Integer.parseInt(opcion) - 1;
                if (indice >= 0 && indice < tiposDisponibles.length) {
                    return tiposDisponibles[indice];
                } else if (indice == tiposDisponibles.length) {
                    return solicitarDatoObligatorio("Especifica el tipo de evento");
                }
            } catch (NumberFormatException e) {
                // Continuar el bucle para solicitar entrada válida
            }
            
            System.out.println("❌ Opción no válida. Por favor, intenta de nuevo.");
        }
    }
    
    /**
     * Solicita un dato obligatorio al usuario y valida que no esté vacío
     */
    private static String solicitarDatoObligatorio(String nombreCampo) {
        while (true) {
            System.out.print(nombreCampo + ": ");
            String dato = scanner.nextLine().trim();
            if (!dato.isEmpty()) {
                return dato;
            }
            System.out.println("❌ Este campo es obligatorio. Por favor, ingresa un valor.");
        }
    }
    
    /**
     * Solicita un dato opcional al usuario
     */
    private static String solicitarDatoOpcional(String nombreCampo) {
        System.out.print(nombreCampo + " (opcional): ");
        return scanner.nextLine().trim();
    }
    
    /**
     * Permite editar un evento existente
     */
    private static void editarEvento() {
        if (eventos.isEmpty()) {
            System.out.println("❌ No hay eventos registrados para editar.");
            return;
        }
        
        System.out.println("\n📝 EDITAR EVENTO EXISTENTE");
        System.out.println("-".repeat(35));
        
        // Mostrar lista de eventos para seleccionar
        mostrarListaEventosNumerada();
        
        System.out.print("Selecciona el número del evento a editar (0 para cancelar): ");
        String opcion = scanner.nextLine().trim();
        
        try {
            int indice = Integer.parseInt(opcion);
            if (indice == 0) {
                System.out.println("❌ Operación cancelada.");
                return;
            }
            
            if (indice < 1 || indice > eventos.size()) {
                System.out.println("❌ Número de evento no válido.");
                return;
            }
            
            Evento evento = eventos.get(indice - 1);
            editarCamposEvento(evento);
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Por favor, ingresa un número válido.");
        }
    }
    
    /**
     * Permite editar campos específicos de un evento
     */
    private static void editarCamposEvento(Evento evento) {
        System.out.println("\n📋 Editando evento: " + evento.getNombre());
        System.out.println("💡 Presiona ENTER para mantener el valor actual");
        System.out.println("-".repeat(40));
        
        // Editar cada campo del evento
        String nuevoNombre = editarCampo("Nombre", evento.getNombre());
        if (!nuevoNombre.isEmpty()) evento.setNombre(nuevoNombre);
        
        String nuevaFecha = editarCampo("Fecha", evento.getFecha());
        if (!nuevaFecha.isEmpty()) evento.setFecha(nuevaFecha);
        
        String nuevoTipo = editarCampo("Tipo", evento.getTipo());
        if (!nuevoTipo.isEmpty()) evento.setTipo(nuevoTipo);
        
        String nuevoLugar = editarCampo("Lugar", evento.getLugar());
        if (!nuevoLugar.isEmpty()) evento.setLugar(nuevoLugar);
        
        String nuevaDescripcion = editarCampo("Descripción", evento.getDescripcion());
        if (!nuevaDescripcion.isEmpty()) evento.setDescripcion(nuevaDescripcion);
        
        System.out.println("✅ ¡Evento actualizado exitosamente!");
        System.out.println("📋 Datos actualizados:");
        System.out.println(evento);
    }
    
    /**
     * Solicita al usuario editar un campo específico
     */
    private static String editarCampo(String nombreCampo, String valorActual) {
        System.out.print(nombreCampo + " [" + valorActual + "]: ");
        return scanner.nextLine().trim();
    }
    
    /**
     * Permite eliminar un evento de la lista
     */
    private static void eliminarEvento() {
        if (eventos.isEmpty()) {
            System.out.println("❌ No hay eventos registrados para eliminar.");
            return;
        }
        
        System.out.println("\n🗑️ ELIMINAR EVENTO");
        System.out.println("-".repeat(25));
        
        mostrarListaEventosNumerada();
        
        System.out.print("Selecciona el número del evento a eliminar (0 para cancelar): ");
        String opcion = scanner.nextLine().trim();
        
        try {
            int indice = Integer.parseInt(opcion);
            if (indice == 0) {
                System.out.println("❌ Operación cancelada.");
                return;
            }
            
            if (indice < 1 || indice > eventos.size()) {
                System.out.println("❌ Número de evento no válido.");
                return;
            }
            
            Evento evento = eventos.get(indice - 1);
            
            // Confirmar eliminación
            System.out.print("⚠️ ¿Estás seguro de eliminar \"" + evento.getNombre() + "\"? (si/no): ");
            String confirmacion = scanner.nextLine().trim().toLowerCase();
            
            if (confirmacion.equals("si") || confirmacion.equals("sí")) {
                eventos.remove(indice - 1);
                System.out.println("✅ Evento eliminado exitosamente.");
            } else {
                System.out.println("❌ Eliminación cancelada.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Por favor, ingresa un número válido.");
        }
    }
    
    /**
     * Muestra todos los eventos registrados en el sistema
     */
    private static void verTodosLosEventos() {
        System.out.println("\n👁️ TODOS LOS EVENTOS REGISTRADOS");
        System.out.println("=".repeat(50));
        
        if (eventos.isEmpty()) {
            System.out.println("❌ No hay eventos registrados en este momento.");
            System.out.println("💡 Usa la opción 2 para crear tu primer evento.");
            return;
        }
        
        // Agrupar eventos por tipo para mejor organización
        Map<String, List<Evento>> eventosPorTipo = eventos.stream()
                .collect(Collectors.groupingBy(Evento::getTipo));
        
        int totalEventos = 0;
        for (Map.Entry<String, List<Evento>> grupo : eventosPorTipo.entrySet()) {
            String tipo = grupo.getKey().toUpperCase();
            List<Evento> eventosDelTipo = grupo.getValue();
            
            System.out.println("\n📂 " + tipo + " (" + eventosDelTipo.size() + " eventos)");
            System.out.println("-".repeat(30));
            
            for (Evento evento : eventosDelTipo) {
                System.out.println(evento);
                System.out.println();
                totalEventos++;
            }
        }
        
        System.out.println("📊 Total de eventos: " + totalEventos);
    }
    
    /**
     * Muestra una lista numerada de eventos para selección
     */
    private static void mostrarListaEventosNumerada() {
        System.out.println("📋 Lista de eventos:");
        for (int i = 0; i < eventos.size(); i++) {
            Evento evento = eventos.get(i);
            System.out.println((i + 1) + ". " + evento.getNombre() + " - " + evento.getFecha());
        }
        System.out.println();
    }
    
    /**
     * Muestra estadísticas generales sobre los eventos
     */
    private static void mostrarEstadisticas() {
        System.out.println("\n📊 ESTADÍSTICAS DE EVENTOS");
        System.out.println("=".repeat(40));
        
        if (eventos.isEmpty()) {
            System.out.println("❌ No hay eventos para mostrar estadísticas.");
            return;
        }
        
        // Estadísticas por tipo
        Map<String, Long> eventosPorTipo = eventos.stream()
                .collect(Collectors.groupingBy(Evento::getTipo, Collectors.counting()));
        
        System.out.println("📈 Eventos por tipo:");
        eventosPorTipo.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> 
                    System.out.println("  • " + entry.getKey() + ": " + entry.getValue() + " eventos"));
        
        // Estadísticas por mes (análisis básico)
        Map<String, Long> eventosPorMes = eventos.stream()
                .collect(Collectors.groupingBy(
                    evento -> extraerMes(evento.getFecha()), 
                    Collectors.counting()));
        
        System.out.println("\n📅 Eventos por mes:");
        eventosPorMes.forEach((mes, cantidad) -> 
            System.out.println("  • " + mes + ": " + cantidad + " eventos"));
        
        System.out.println("\n📊 Resumen general:");
        System.out.println("  • Total de eventos: " + eventos.size());
        System.out.println("  • Tipos diferentes: " + eventosPorTipo.size());
        System.out.println("  • Evento más común: " + 
            eventosPorTipo.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A"));
    }
    
    /**
     * Extrae el mes de una fecha en formato string
     */
    private static String extraerMes(String fecha) {
        // Análisis básico para extraer mes de formatos como "15-marzo-2024"
        String[] partes = fecha.split("-");
        if (partes.length >= 2) {
            return partes[1];
        }
        return "Desconocido";
    }
    
    /**
     * Clase auxiliar para almacenar eventos con su puntuación de búsqueda
     */
    private static class EventoConPuntuacion {
        Evento evento;
        int puntuacion;
        
        EventoConPuntuacion(Evento evento, int puntuacion) {
            this.evento = evento;
            this.puntuacion = puntuacion;
        }
    }
}