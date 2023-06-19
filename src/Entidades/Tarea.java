package Entidades;

import java.time.LocalDate;

public class Tarea {

    private int idTarea;
    private EquipoMiembro miembroEqId;
    private String nombre;
    private LocalDate fechaCreacion;
    private LocalDate fechaCierre;
    private int estado;

    public Tarea() {
    }
    
    public Tarea(EquipoMiembro idMiembroEq, String nombre, LocalDate fechaCierre, LocalDate fechaCreacion, int estado) {
        this.miembroEqId = idMiembroEq;
        this.nombre = nombre;
        this.fechaCierre = fechaCierre;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public Tarea(int idTarea, EquipoMiembro idMiembroEq, String nombre, LocalDate fechaCierre, LocalDate fechaCreacion, int estado) {
        this.idTarea = idTarea;
        this.miembroEqId = idMiembroEq;
        this.nombre = nombre;
        this.fechaCierre = fechaCierre;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public EquipoMiembro getMiembroEqId() {
        return miembroEqId;
    }

    public void setMiembroEqId(EquipoMiembro miembroEqId) {
        this.miembroEqId = miembroEqId;
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

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    

    @Override
    public String toString() {
        return  "idTarea= " + idTarea + ", idMiembroEq= " + miembroEqId.getIdMiembroEq() + ", nombre= " + nombre + ", fechaCreacion= " + fechaCreacion + ", fechaCierre= " + fechaCierre + ", estado= " + estado;
    }

}
