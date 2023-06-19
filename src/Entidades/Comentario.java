package Entidades;

import java.time.LocalDate;

public class Comentario {

    private int idCometario;
    private String comentario;
    private LocalDate fechaAvance;
    private Tarea tareaId;

    public Comentario(String comentario, LocalDate fechaAvance, Tarea idTarea) {
        this.comentario = comentario;
        this.fechaAvance = fechaAvance;
        this.tareaId = idTarea;
    }

    public Comentario(int idCometario, String comentario, LocalDate fechaAvance, Tarea idTarea) {
        this.idCometario = idCometario;
        this.comentario = comentario;
        this.fechaAvance = fechaAvance;
        this.tareaId = idTarea;
    }

    public Comentario() {
    }

    public int getIdCometario() {
        return idCometario;
    }

    public void setIdCometario(int idCometario) {
        this.idCometario = idCometario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFechaAvance() {
        return fechaAvance;
    }

    public void setFechaAvance(LocalDate fechaAvance) {
        this.fechaAvance = fechaAvance;
    }

    public Tarea getTareaId() {
        return tareaId;
    }

    public void setTareaId(Tarea tareaId) {
        this.tareaId = tareaId;
    }

    

    @Override
    public String toString() {
        return  "idCometario= " + idCometario + ", comentario= " + comentario + ", fechaAvance= " + fechaAvance + ", idTarea= " + tareaId.getIdTarea();
    }

}
