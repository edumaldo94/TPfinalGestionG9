package Entidades;

import java.time.LocalDate;

public class Equipo {

    private Proyecto proyectoId;
    private int idEquipo;
    private String nombre;
    private LocalDate fechaCreacion;
    private boolean estado;

    public Equipo() {
    }

    public Equipo(Proyecto proyectoId, String nombre, LocalDate fechaCreacion, boolean estado) {
        this.proyectoId = proyectoId;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public Equipo(Proyecto proyectoId, int idEquipo, String nombre, LocalDate fechaCreacion, boolean estado) {
        this.proyectoId = proyectoId;
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public Proyecto getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Proyecto proyectoId) {
        this.proyectoId = proyectoId;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "ID Equipo: " + idEquipo + "/ Nombre: " + nombre + "/ Fecha de Creacion: " + fechaCreacion + "/ Estado: " + estado;
    }

}
