package com.tercerotest.controller.graph;

import java.io.File;
import java.io.FileWriter;
import com.tercerotest.controller.excepcion.ListEmptyException;

/**
 * Clase que se encarga de crear el archivo .js que contiene el grafo
 */
public class GraphGenerator {
    
    private static final String FILE_PATH = "MVIS" + File.separator + "grafo.js";

    public void generateGraph(GhaphLabelNoDirect graph) throws ListEmptyException {
        StringBuilder nodeData = new StringBuilder("var nodes = new vis.DataSet([\n");
        StringBuilder edgeData = new StringBuilder("var edges = new vis.DataSet([\n");

        for (int nodeId = 1; nodeId <= graph.nro_vertices(); nodeId++) {
            nodeData.append(String.format("\t{ id: %d, label: \" %s\" },\n", nodeId, graph.getLabelL(nodeId)));
            
            for (Adyacencia adjacency : (Iterable<Adyacencia>) graph.adyacencias(nodeId)) {
                edgeData.append(String.format("\t{ from: %d, to: %d, label: \"\" },\n", nodeId, adjacency.getDestino()));
            }
        }

        nodeData.append("]);");
        edgeData.append("]);");

        String scriptConfig = "var container = document.getElementById(\"mynetwork\");\n"
                + "var data = { nodes: nodes, edges: edges };\n"
                + "var options = {};\n"
                + "var network = new vis.Network(container, data, options);";

        writeToFile(nodeData.toString(), edgeData.toString(), scriptConfig);
    }

    private void writeToFile(String nodes, String edges, String script) {
        try {
            File fileDir = new File(FILE_PATH).getParentFile();
            if (!fileDir.exists()) fileDir.mkdirs();

            try (FileWriter writer = new FileWriter(FILE_PATH)) {
                writer.write(nodes + "\n" + edges + "\n" + script);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


