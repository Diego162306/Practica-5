package com.tercerotest.controller.graph;

public class Adyacencia {
    private Integer destino;
    private Float weigth;

    //constructors
    public Adyacencia(Integer destino, Float weigth) {
        this.destino = destino;
        this.weigth = weigth;
    }

    public Adyacencia() {
    }

    //getters and setters

    public Integer getDestino() {
        return destino;
    }

    public void setDestino(Integer destino) {
        this.destino = destino;
    }

    public Float getWeigth() {
        return weigth;
    }

    public void setWeigth(Float weigth) {
        this.weigth = weigth;
    }
}
