package edu.curso.goodooit.domain.model;

public class Lista {
    private Integer ID;
    private String titulo;

    public Lista() {}

    public Lista(Integer ID, String titulo) {
        this.ID = ID;
        this.titulo = titulo;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
