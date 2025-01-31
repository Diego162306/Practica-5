package com.tercerotest.controller.graph;

import com.tercerotest.controller.tda.list.LinkedList;
import com.tercerotest.controller.excepcion.OverFlowException;

public abstract class Graph {
    public abstract Integer nro_vertices();
    public abstract Integer nro_edges();
    public abstract Boolean is_edge(Integer v1, Integer v2) throws Exception;
    public abstract Float wiegth_edge(Integer v1, Integer v2) throws Exception;
    public abstract void add_edge(Integer v1, Integer v2) throws Exception;
    public abstract void add_edge(Integer v1, Integer v2, Float wiegth) throws Exception;

    public abstract LinkedList<Adyacencia> adyacencias(Integer vi);

    @Override
    public String toString(){
        String grafo= "";
        try {
            for(int i = 0; i <= this.nro_vertices(); i++){
                grafo += "V"+i+"\n";
                LinkedList<Adyacencia> lista = adyacencias(i);
                if (lista.isEmpty()) {
                    Adyacencia[] ady = lista.toArray();
                    for (int j = 0; j < ady.length; j++) {
                        Adyacencia a = ady[j];
                        grafo+="ady"+"V"+a.getDestino()+"weigt:"+a.getWeigth()+"\n";
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return grafo;
    }
}
