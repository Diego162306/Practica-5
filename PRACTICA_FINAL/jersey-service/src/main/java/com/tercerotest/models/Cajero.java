package com.tercerotest.models;

public class Cajero {
    private Integer id;
    private Double latitude;
    private Double longitude;
    private Double saldoDsp;
    private Boolean estado;
    private String banco;

    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getSaldoDsp() {
        return saldoDsp;
    }

    public void setSaldoDsp(Double saldoDsp) {
        this.saldoDsp = saldoDsp;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    //     // floydWarshall

    // public float[][] floydWarshall() {
    //     int n = nro_vertices();
    //     float[][] dist = new float[n + 1][n + 1];
    
    //     // Inicializar la matriz con infinito
    //     for (int i = 1; i <= n; i++) {
    //         for (int j = 1; j <= n; j++) {
    //             if (i == j) {
    //                 dist[i][j] = 0;
    //             } else {
    //                 dist[i][j] = Float.POSITIVE_INFINITY;
    //             }
    //         }
    //     }
    
    //     // Rellenar la matriz con los pesos de las aristas
    //     for (int i = 1; i <= n; i++) {
    //         LinkedList<Adyacencia> adyacencias = adyacencias(i);
    
    //         if (adyacencias == null || adyacencias.isEmpty()) {
    //             System.err.println("Advertencia: adyacencias(" + i + ") es null o está vacía");
    //             continue; // Salta este nodo si no tiene adyacencias
    //         }
    
    //         for (Adyacencia ady : adyacencias.toArray()) {
    //             if (ady == null) {
    //                 System.err.println("Advertencia: adyacencia nula en el nodo " + i);
    //                 continue;
    //             }
    //             dist[i][ady.getDestino()] = ady.getWeigth();
    //         }
    //     }
    
    //     // Algoritmo de Floyd-Warshall
    //     for (int k = 1; k <= n; k++) {
    //         for (int i = 1; i <= n; i++) {
    //             for (int j = 1; j <= n; j++) {
    //                 if (dist[i][k] != Float.POSITIVE_INFINITY && dist[k][j] != Float.POSITIVE_INFINITY) {
    //                     dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
    //                 }
    //             }
    //         }
    //     }
    
    //     return dist;
    // }

    // // bellmanFord
    
    // public Float[] bellmanFord(int verticeOrigen) throws Exception {
    //     int n = nro_vertices();
    //     Float[] dist = new Float[n + 1];
    //     Arrays.fill(dist, Float.POSITIVE_INFINITY);
    //     dist[verticeOrigen] = 0.0f;
    
    //     // Relajación de aristas
    //     for (int i = 1; i < n; i++) {
    //         for (int u = 1; u <= n; u++) {
    //             LinkedList<Adyacencia> adyacencias = adyacencias(u);
    
    //             if (adyacencias == null || adyacencias.isEmpty()) {
    //                 System.err.println("Advertencia: adyacencias(" + u + ") es null o está vacía");
    //                 continue; // Salta este nodo si no tiene adyacencias
    //             }
    
    //             for (Adyacencia ady : adyacencias.toArray()) {
    //                 if (ady == null) {
    //                     System.err.println("Advertencia: adyacencia nula en el nodo " + u);
    //                     continue;
    //                 }
    //                 int v = ady.getDestino();
    //                 float weight = ady.getWeigth();
    //                 if (dist[u] != Float.POSITIVE_INFINITY && dist[u] + weight < dist[v]) {
    //                     dist[v] = dist[u] + weight;
    //                 }
    //             }
    //         }
    //     }
    
    //     // Verificación de ciclos negativos
    //     for (int u = 1; u <= n; u++) {
    //         LinkedList<Adyacencia> adyacencias = adyacencias(u);
    
    //         if (adyacencias == null || adyacencias.isEmpty()) {
    //             continue; // Salta este nodo si no tiene adyacencias
    //         }
    
    //         for (Adyacencia ady : adyacencias.toArray()) {
    //             if (ady == null) {
    //                 continue;
    //             }
    //             int v = ady.getDestino();
    //             float weight = ady.getWeigth();
    //             if (dist[u] != Float.POSITIVE_INFINITY && dist[u] + weight < dist[v]) {
    //                 throw new Exception("El grafo contiene un ciclo negativo");
    //             }
    //         }
    //     }
    
    //     return dist;
    // }

    
}
