package com.example.boredapi.model;

import com.google.gson.annotations.SerializedName;

public class Registro {

    @SerializedName("TURNO")
    private int idTurno;
    @SerializedName("STATUS")
    private int idStatus;
    @SerializedName("ID_MAQUINA")
    private int idMaquina;
    @SerializedName("ID_OPERADOR")
    private int idOperador;
    @SerializedName("OFS")
    private String ofs;

    public Registro(int idTurno, int idStatus, int idMaquina, int idOperador) {
        this.idTurno = idTurno;
        this.idStatus = idStatus;
        this.idMaquina = idMaquina;
        this.idOperador = idOperador;
    }

    public Registro(int idTurno, int idStatus, int idMaquina, int idOperador, String ofs) {
        this.idTurno = idTurno;
        this.idStatus = idStatus;
        this.idMaquina = idMaquina;
        this.idOperador = idOperador;
        this.ofs = ofs;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public int getIdOperador() {
        return idOperador;
    }

    public String getOfs() {
        return ofs;
    }
}
