package com.example.boredapi.model;

import com.google.gson.annotations.SerializedName;

public class Maquina {

    @SerializedName("ID_MAQ")
    private int idMaq;
    @SerializedName("DESQ_MAQ")
    private String descMaq;

    public Maquina() {
    }

    public Maquina(int idMaq, String descMaq) {
        this.idMaq = idMaq;
        this.descMaq = descMaq != null ? descMaq.trim() : " - ";
    }

    public int getIdMaq() {
        return idMaq;
    }

    public String getDescMaq() {
        return descMaq;
    }

    @Override
    public String toString() {
        return "Maquina{" +
                "idMaq=" + getIdMaq() +
                ", descMaq='" + getDescMaq() + '\'' +
                '}';
    }
}
