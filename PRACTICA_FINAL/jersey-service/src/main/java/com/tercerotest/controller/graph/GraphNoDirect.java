package com.tercerotest.controller.graph;

import com.tercerotest.controller.excepcion.OverFlowException;

public class GraphNoDirect extends Graphdirect {
    public GraphNoDirect(Integer nro_vertices) {
        super(nro_vertices);
    }

    public void add_edge(Integer v1, Integer v2, Float weigth) throws Exception {
         if (v1.intValue() <= nro_vertices() && v2.intValue() <= nro_vertices()) {
           if (!is_edge(v1, v2)) {
            setNro_edges(nro_edges()+1);

            Adyacencia aux = new Adyacencia();
            aux.setWeigth(weigth);
            aux.setDestino(v2);
            getListAdyacencias()[v1].add(aux);

            Adyacencia auxD = new Adyacencia();
            auxD.setWeigth(weigth);
            auxD.setDestino(v1);
            getListAdyacencias()[v2].add(aux);
           }
        } else {
            throw new OverFlowException("Vertices fuera de rango");  
        }
        super.add_edge(v1, v2, weigth);
    }
}
