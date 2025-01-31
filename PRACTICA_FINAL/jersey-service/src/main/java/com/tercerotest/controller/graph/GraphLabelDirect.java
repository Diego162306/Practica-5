package com.tercerotest.controller.graph;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

import com.tercerotest.controller.excepcion.LabelException;
import com.tercerotest.controller.tda.list.LinkedList;

public class GraphLabelDirect<E> extends Graphdirect {
    protected E labels[];
    protected HashMap<E, Integer> dictVertices;
    private Class<E> clazz;

    public GraphLabelDirect(Integer nro_vertices, Class<E> clazzs) {
        super(nro_vertices);
        this.clazz = clazzs;
        this.labels = (E[]) Array.newInstance(clazzs, nro_vertices + 1);
        dictVertices = new HashMap<>();
    }

    public Boolean is_edgeLabel(E v1, E v2) throws Exception {
        if (isLabelsGraph()) {
            return is_edge(getVeticeL(v1), getVeticeL(v2));
        } else {
            throw new LabelException("Grafo no etiquetado");
        }
    }

    public void insertEdgeL(E v1, E v2, Double lat1, Double lon1, Double lat2, Double lon2) throws Exception {
        if (isLabelsGraph()) {
            add_edge(getVeticeL(v1), getVeticeL(v2) ); // Insertamos la arista con el peso calculado
        } else {
            throw new LabelException("Grafo no etiquetado");
        }
    }

    public void insertEdgeL(E v1, E v2) throws Exception {
        if (isLabelsGraph()) {
            insertEdgeL(v1, v2, 0.0, 0.0, 0.0, 0.0); // Si no se pasan coordenadas, insertamos sin peso
        } else {
            throw new LabelException("Grafo no etiquetado");
        }
    }

    public LinkedList<Adyacencia> adyacenciasL(E v) throws Exception {
        if (isLabelsGraph()) {
            return adyacencias(getVeticeL(v));
        } else {
            throw new LabelException("El grafo no esta etiquetado");
        }
    }

    public LinkedList<Adyacencia> adyacencias(int i) {
        LinkedList<Adyacencia> adyacencias = super.adyacencias(i); // Llama al método de la clase base
        if (adyacencias == null) {
            adyacencias = new LinkedList<>(); // Devuelve una lista vacía en lugar de null
        }
        return adyacencias;
    }

    public void labelsVertices(Integer v, E data) {
        labels[v] = data;
        dictVertices.put(data, v);
    }

    public Boolean isLabelsGraph() {
        Boolean band = true;
        for (int i = 1; i < labels.length; i++) {
            if (labels[i] == null) {
                band = false;
                break;
            }
        }
        return band;
    }

    public Integer getVeticeL(E label) {
        return dictVertices.get(label);
    }

    public E getLabelL(Integer v1) {
        return labels[v1];
    }

    //flooyd

    public float[][] floydWarshall() {
        int n = nro_vertices();
        float[][] dist = new float[n + 1][n + 1];
    
        // Inicializar la matriz con infinito
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = Float.POSITIVE_INFINITY;
                }
            }
        }
    
        // Rellenar la matriz con los pesos de las aristas
        for (int i = 1; i <= n; i++) {
            LinkedList<Adyacencia> adyacencias = adyacencias(i);
    
            if (adyacencias == null || adyacencias.isEmpty()) {
                System.err.println("Advertencia: adyacencias(" + i + ") es null o está vacía");
                continue; // Salta este nodo si no tiene adyacencias
            }
    
            for (Adyacencia ady : adyacencias.toArray()) {
                if (ady == null) {
                    System.err.println("Advertencia: adyacencia nula en el nodo " + i);
                    continue;
                }
                dist[i][ady.getDestino()] = ady.getWeigth();
            }
        }
    
    
        // Algoritmo de Floyd-Warshall
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] != Float.POSITIVE_INFINITY && dist[k][j] != Float.POSITIVE_INFINITY) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
    
        return dist;
    }


    //bellman
    public Float[] bellmanFord(int verticeOrigen) throws Exception {
		Float n_infinity = Float.MAX_VALUE;
		Float[] dist = (Float[]) Array.newInstance(Float.class, nro_vertices() + 1);

		for (int k = 1; k <= nro_vertices(); k++) {
			dist[k] = n_infinity;
		}

		dist[verticeOrigen] = 0.0f;

		// Relajación de aristas
		for (int i = 1; i <= nro_vertices(); i++) {
			for (int j = 1; j <= nro_vertices(); j++) {
				LinkedList<Adyacencia> ady = adyacencias(j);
				if (!ady.isEmpty()) {
					Adyacencia[] arr_ady = ady.toArray();
					for (Adyacencia aux : arr_ady) {
						int v = aux.getDestino();
						float weight = aux.getWeigth();
						// System.out.println("V" + j + ", v:" + v + ", nVert" + nro_vertices());
						if (dist[j] != Float.POSITIVE_INFINITY && dist[i] + weight < dist[v]) {
							dist[v] = dist[j] + weight;
						}
					}
				}

			}

		}
		if (!tieneCicloNegativo(dist)) { // si no hay ciclos negativos retorna las distancias minimas
			return dist;
		}
		return null;
	}
    //verificar si hay ciclos negativos
	private boolean tieneCicloNegativo(Float[] dist) {
	
		for (int i = 1; i <= nro_vertices(); i++) {
			LinkedList<Adyacencia> ady = adyacencias(i);
			if (!ady.isEmpty()) {
				Adyacencia[] matrix_ady = ady.toArray();
				for (Adyacencia aux : matrix_ady) {
					int v = aux.getDestino();
					float weight = aux.getWeigth();
					if (dist[i] != Float.POSITIVE_INFINITY && dist[i] + weight < dist[v]) {
						return true;
					}
				}
			}
		}
		return false;
	}

    @Override
    public String toString() {
        String grafo = "";
        try {
            for (int i = 0; i <= this.nro_vertices(); i++) {
                grafo += "V" + i + "[" + getLabelL(i).toString() + "]" + "\n";
                LinkedList<Adyacencia> lista = adyacencias(i);
                if (lista.isEmpty()) {
                    Adyacencia[] ady = lista.toArray();
                    for (int j = 0; j < ady.length; j++) {
                        Adyacencia a = ady[j];
                        grafo += "ady" + "V" + a.getDestino() + "weigt:" + a.getWeigth() + "["
                                + getLabelL(a.getDestino()).toString() + "]" + "\n";
                    }
                }
            }
        } catch (Exception e) {
        }
        return grafo;
    }

    // public String drawGhaph() {
    // StringBuilder grafo = new StringBuilder();

    // try {
    // //Creacion de
    // for (int i = 1; i <= this.nro_vertices(); i++) { // Asegura que los índices
    // son correctos
    // grafo.append("{id:").append(i)
    // .append(", label: \"").append(getLabelL(i)).append("\"},\n");
    // }
    // grafo.append("]);\n");

    // grafo.append("var edges = new vis.DataSet([\n");

    // // Agregamos las aristas
    // for (int i = 1; i <= this.nro_vertices(); i++) {
    // LinkedList<Adyacencia> lista = adyacencias(i);
    // for (int j = 0; j < lista.getSize(); j++) {
    // Adyacencia a = lista.get(j); // Usar get() si es una lista
    // grafo.append("{from:").append(i)
    // .append(", to:").append(a.getDestino())
    // .append(", label:\"").append(a.getWeigth()).append("\"")
    // .append(", arrows: \"to\", color: \"red\"},\n");
    // }

    // }

    // grafo.append("]);\n");

    // grafo.append("var container = document.getElementById('mynetwork');\n");
    // grafo.append("var data = { nodes: nodes, edges: edges };\n");
    // grafo.append("var options = {};\n");
    // grafo.append("var network = new vis.Network(container, data, options);\n");

    // String path =
    // "/home/juan/Documentos/PRACTICA_FINAL/FRONT/static/js/graph.js";
    // FileWriter file = new FileWriter(path);
    // file.write(grafo.toString());
    // file.flush();
    // file.close();

    // } catch (Exception e) {
    // System.out.println("Error al generar el gráfico: " + e.getMessage());
    // }

    // return grafo.toString();
    // }

    public String drawGraph() {
        try {

            // Crear conexiones entre nodos con las coordenadas de los cajeros
            insertEdgeL(getLabelL(1), getLabelL(2), -3.9876, -79.2045, -4.5478, -79.1212);
            insertEdgeL(getLabelL(1), getLabelL(3), -3.9876, -79.2045, 6.9876, 9.2045);
            insertEdgeL(getLabelL(2), getLabelL(4), -4.5478, -79.1212, 5.9876, 8.2045);
            insertEdgeL(getLabelL(2), getLabelL(5), -4.5478, -79.1212, 4.9876, 7.2045);
            insertEdgeL(getLabelL(3), getLabelL(6), 6.9876, 9.2045, 3.9876, 6.2045);
            insertEdgeL(getLabelL(3), getLabelL(7), 6.9876, 9.2045, 2.9876, 5.2045);
            insertEdgeL(getLabelL(4), getLabelL(8), 5.9876, 8.2045, 1.9876, 4.2045);
            insertEdgeL(getLabelL(5), getLabelL(9), 4.9876, 7.2045, 0.9876, 3.2045);
            insertEdgeL(getLabelL(6), getLabelL(10), 3.9876, 6.2045, -0.9876, 2.2045);

            // Construcción del archivo JSON
            StringBuilder grafo = new StringBuilder();
            grafo.append("[");
            int edgeId = 1;

            for (int i = 1; i <= this.nro_vertices(); i++) {
                LinkedList<Adyacencia> lista = adyacencias(i);
                if (!lista.isEmpty()) {
                    for (Adyacencia a : lista.toArray()) {
                        grafo.append("{")
                                .append("\"id\": ").append(edgeId).append(", ")
                                .append("\"origen\": ").append(i).append(", ")
                                .append("\"destino\": ").append(a.getDestino())
                                .append("},\n");
                        edgeId++;
                    }
                }
            }

            grafo.append("]");

            // Guardar en un archivo JSON
            String path = "/home/juan/Documentos/PRACTICA_FINAL/FRONT/static/js/graph.json";
            FileWriter jsonFile = new FileWriter(path);
            jsonFile.write(grafo.toString());
            jsonFile.flush();
            jsonFile.close();

            return grafo.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
