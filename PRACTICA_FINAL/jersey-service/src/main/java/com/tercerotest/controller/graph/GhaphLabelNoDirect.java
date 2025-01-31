package com.tercerotest.controller.graph;

import com.tercerotest.controller.excepcion.LabelException;

public class GhaphLabelNoDirect<E> extends GraphLabelDirect<E> { 
    
    public GhaphLabelNoDirect(Integer nro_vertices, Class<E> clazz) {
        super(nro_vertices, clazz);
    }

    public void insertEdgeL(E v1, E v2, Float weigth) throws Exception {
        if (isLabelsGraph()) {
            add_edge(getVeticeL(v1), getVeticeL(v2), weigth);
            add_edge(getVeticeL(v2), getVeticeL(v1), weigth);
        } else {
            throw new LabelException("El grafo no esta etiquetado");
        }
    }
}
