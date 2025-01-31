package com.tercerotest.controller.graph;

import com.tercerotest.controller.tda.list.LinkedList;
import com.tercerotest.models.Cajero;

public class GraphAlgorithmTester {

    public static void main(String[] args) throws Exception {
        int[] sizes = { 10, 20, 30 };
        LinkedList<Long> floydTimes = new LinkedList<>();
        LinkedList<Long> bellmanTimes = new LinkedList<>();

        System.out.println("Tamaño del Grafo | Floyd-Warshall (ms) | Bellman-Ford (ms) | Algoritmo Más Rápido");
        System.out.println("-----------------------------------------------------------------------------------");

        // Primero medimos los tiempos de Floyd-Warshall
        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            GhaphLabelNoDirect<Cajero> graph = new GhaphLabelNoDirect<>(size, Cajero.class);
            Cajero[] m = new Cajero[size];

            // Simular los vértices y agregar etiquetas
            for (int j = 0; j < size; j++) {
                Cajero s = new Cajero(); // Puedes personalizar este objeto
                m[j] = s;
                graph.labelsVertices(j + 1, s);
            }

            // Agregar aristas de ejemplo (puedes modificar esta parte para mayor aleatoriedad)
            for (int j = 0; j < size - 1; j++) {
                graph.insertEdgeL(m[j], m[j + 1], (j + 1) * 2.0f);
            }

            // Medir tiempo de Floyd-Warshall
            long startTime = System.currentTimeMillis(); // Usamos milisegundos
            try {
                graph.floydWarshall();
            } catch (Exception e) {
                System.err.println("Error en Floyd-Warshall para tamaño " + size);
            }
            floydTimes.add(System.currentTimeMillis() - startTime); // Agregar tiempo a la lista

            // Mostrar el tiempo de Floyd-Warshall
            System.out.printf("%15d | %17d |", size, floydTimes.get(i)); // Usar get para obtener el valor en la lista
        }

        System.out.println(); // Salto de línea entre las dos mediciones

        // Luego medimos los tiempos de Bellman-Ford
        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            GhaphLabelNoDirect<Cajero> graph = new GhaphLabelNoDirect<>(size, Cajero.class);
            Cajero[] m = new Cajero[size];

            // Simular los vértices y agregar etiquetas
            for (int j = 0; j < size; j++) {
                Cajero s = new Cajero(); // Puedes personalizar este objeto
                m[j] = s;
                graph.labelsVertices(j + 1, s);
            }

            // Agregar aristas de ejemplo
            for (int j = 0; j < size - 1; j++) {
                graph.insertEdgeL(m[j], m[j + 1], (j + 1) * 2.0f);
            }

            // Medir tiempo de Bellman-Ford
            long startTime = System.currentTimeMillis(); // Usamos milisegundos
            try {
                graph.bellmanFord(1); // Usando el vértice 1 como origen
            } catch (Exception e) {
                System.err.println("Error en Bellman-Ford para tamaño " + size);
            }
            bellmanTimes.add(System.currentTimeMillis() - startTime); // Agregar tiempo a la lista

            // Mostrar el tiempo de Bellman-Ford y determinar el más rápido
            String fasterAlgorithm = floydTimes.get(i) < bellmanTimes.get(i) ? "Floyd-Warshall" : "Bellman-Ford";
            System.out.printf(" %17d | %20s\n", bellmanTimes.get(i), fasterAlgorithm);
        }

        System.out.println("-----------------------------------------------------------------------------------");
    }
}
