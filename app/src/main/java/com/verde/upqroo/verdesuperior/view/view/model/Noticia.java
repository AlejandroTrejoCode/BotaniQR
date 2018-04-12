package com.verde.upqroo.verdesuperior.view.view.model;

/**
 * Created by pprm2 on 11/04/2018.
 */

public class Noticia {
    private String Nombre;
    private String Descripcion;
    private String Url;

    public Noticia(String nombre, String descripcion, String url) {
        this.Nombre = nombre;
        this.Descripcion = descripcion;
        this.Url = url;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        this.Url = url;
    }
}
