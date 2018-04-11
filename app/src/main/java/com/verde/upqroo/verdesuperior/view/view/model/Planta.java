package com.verde.upqroo.verdesuperior.view.view.model;

/**
 * Created by pprm2 on 10/04/2018.
 */

public class Planta {

    private String nombre;
    private String nombre_cientifico;
    private String descripcion;
    private String taxonomia;
    private String aplicaciones;
    private String imagen_url;

    public Planta(){}
    public Planta(String nombre, String nombre_cientifico, String descripcion, String taxonomia, String aplicaciones, String imagen_url){
        this.descripcion= descripcion;
        this.nombre = nombre;
        this.nombre_cientifico = nombre_cientifico;
        this.taxonomia = taxonomia;
        this.aplicaciones = aplicaciones;
        this.imagen_url = imagen_url;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre_cientifico() {
        return nombre_cientifico;
    }

    public void setNombre_cientifico(String nombre_cientifico) {
        this.nombre_cientifico = nombre_cientifico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTaxonomia() {
        return taxonomia;
    }

    public void setTaxonomia(String taxonomia) {
        this.taxonomia = taxonomia;
    }

    public String getAplicaciones() {
        return aplicaciones;
    }

    public void setAplicaciones(String aplicaciones) {
        this.aplicaciones = aplicaciones;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }
}
