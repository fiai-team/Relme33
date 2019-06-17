package com.relme33.fiai.uci.relme33.database;

public class Evento {
    private Integer id_evento = 0;
    private String descripcion = "";
    private String tiempo = "";
    private String ubicacion = "";
    private String modalidad = "";
    private String person = "";

    public Evento(){

    }



    public Evento(Integer id_evento, String descripcion, String tiempo, String ubicacion, String modalidad, String person) {
        this.id_evento = id_evento;
        this.descripcion = descripcion;
        this.tiempo = tiempo;
        this.ubicacion = ubicacion;
        this.modalidad = modalidad;
        this.person = person;
    }

    public Integer getId_evento() {
        return id_evento;
    }

    public void setId_evento(Integer id_evento) {
        this.id_evento = id_evento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return ubicacion;
    }
}
