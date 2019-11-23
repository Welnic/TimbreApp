package com.exaample.timbre.models;

public class Timbru {

    //http://www.mocky.io/v2/5dd8e2843100007400055ef2

    private int id;
    private String serie;
    private int an;
    private String raritate;

    public Timbru() {
    }

    public Timbru(int id, String serie, int an, String raritate) {
        this.id = id;
        this.serie = serie;
        this.an = an;
        this.raritate = raritate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public String getRaritate() {
        return raritate;
    }

    public void setRaritate(String raritate) {
        this.raritate = raritate;
    }

    @Override
    public String toString() {
        return "TImbrul "+
                id +
                " cu seria "+ serie  +
                " este fabricat in anul " + an +
                " si este " + raritate;
    }
}
