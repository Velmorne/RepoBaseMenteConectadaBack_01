package org.example;

import java.util.ArrayList;
import java.util.List;

public class HU027C3ContadorEventos {

    public static void main(String[] args) {

        List<String> eventos = new ArrayList<>();

         eventos.add("Charla sobre manejo del estrés");
         eventos.add("Taller de mindfulness");
         eventos.add("Sesión grupal de apoyo emocional");

        if (eventos.isEmpty()) {
            System.out.println("No hay eventos publicados.");
        } else {
            System.out.println("Total de eventos publicados: " + eventos.size());
        }
    }
}

