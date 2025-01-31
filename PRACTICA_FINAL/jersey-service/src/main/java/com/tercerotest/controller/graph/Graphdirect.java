package com.tercerotest.controller.graph;

import com.tercerotest.controller.tda.list.LinkedList;
import com.tercerotest.controller.excepcion.OverFlowException;

public class Graphdirect extends Graph {
    protected Integer nro_vertices;
    private Integer nro_edges;
    protected LinkedList<Adyacencia> listAdyacencias[];

    public Graphdirect(Integer nro_vertices) {
        this.nro_vertices = nro_vertices;
        this.nro_edges = 0;
        listAdyacencias = new LinkedList[nro_vertices + 1];
        for (int i = 1; i <= nro_vertices; i++) {
            listAdyacencias[i] = new LinkedList<>();
        }
    }

    public Integer nro_edges() {
        return this.nro_edges;
    }

    public Integer nro_vertices() {
        return this.nro_vertices;
    }

    public Boolean is_edge(Integer v1, Integer v2) throws Exception {
        Boolean band = false;
        if (v1.intValue() <= nro_vertices() && v2.intValue() <= nro_vertices()) {
            LinkedList<Adyacencia> lista = listAdyacencias[v1];
            if (!lista.isEmpty()) {
                Adyacencia[] matrix = lista.toArray();
                for (int i = 0; i < matrix.length; i++) {
                    Adyacencia aux = matrix[i];
                    if (aux.getDestino().intValue() == v2.intValue()) {
                        band = true;
                        break;
                    }
                }

            }
        } else {
            throw new OverFlowException("Vertices fuera de rango");
        }
        return band;
    }

    public Float wiegth_edge(Integer v1, Integer v2) throws Exception {
        Float wiegth = Float.NaN;
        if (is_edge(v1, v2)) { 
            LinkedList<Adyacencia> lista = listAdyacencias[v1];
            if (lista.isEmpty()) {
                Adyacencia[] matrix = lista.toArray();
                for (int i = 0; i < matrix.length; i++) {
                    Adyacencia aux = matrix[i];
                    if (aux.getDestino().intValue() == v2.intValue()) {
                        wiegth = aux.getWeigth();
                        break;
                    }
                }
            }
        }
        return wiegth;
    }

    public void  add_edge(Integer v1, Integer v2, Float wiegth) throws Exception {
        // System.out.println(v1 + " -- " + v2 + " -- " + nro_vertices);
        if (v1.intValue() <= nro_vertices && v2.intValue() <= nro_vertices) {
            if (!is_edge(v1, v2)) {
                nro_edges++;
                Adyacencia aux = new Adyacencia();
                aux.setWeigth(wiegth);
                aux.setDestino(v2);
                listAdyacencias[v1].add(aux);
            }
        } else {
            throw new OverFlowException("Vertices fuera de rango");
        }
    }

    public void add_edge(Integer v1, Integer v2) throws Exception {
        add_edge(v1, v2, Float.NaN);
    }

    public LinkedList<Adyacencia> adyacencias(Integer vi) {
        return listAdyacencias[vi];
    }

    // // getters and setters

    public LinkedList<Adyacencia>[] getListAdyacencias() {
        return this.listAdyacencias;
    };

    public void setListAdyacencias(LinkedList<Adyacencia>[] listAdyacencias) {
    this.listAdyacencias = listAdyacencias;
    }

    public void setNro_edges(Integer nro_edges) {
        this.nro_edges = nro_edges;
    }

}
