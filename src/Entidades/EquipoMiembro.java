package Entidades;

import java.time.LocalDate;

public class EquipoMiembro {

    private int idMiembroEq;
    private Equipo equipoId;
    private Miembro miembroId;
    private LocalDate fechaIncorporacion;

    public EquipoMiembro() {
    }
    
    public EquipoMiembro(Equipo idEquipo, Miembro idMiembro, LocalDate fechaIncorporacion) {
        this.equipoId = idEquipo;
        this.miembroId = idMiembro;
        this.fechaIncorporacion = fechaIncorporacion;
    }
    
    public EquipoMiembro(int idMiembroEq, Equipo idEquipo, Miembro idMiembro, LocalDate fechaIncorporacion) {
        this.idMiembroEq = idMiembroEq;
        this.equipoId = idEquipo;
        this.miembroId = idMiembro;
        this.fechaIncorporacion = fechaIncorporacion;
    }

    public int getIdMiembroEq() {
        return idMiembroEq;
    }

    public void setIdMiembroEq(int idMiembroEq) {
        this.idMiembroEq = idMiembroEq;
    }

    public Equipo getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Equipo equipoId) {
        this.equipoId = equipoId;
    }

    public Miembro getMiembroId() {
        return miembroId;
    }

    public void setMiembroId(Miembro miembroId) {
        this.miembroId = miembroId;
    }

    public LocalDate getFechaIncorporacion() {
        return fechaIncorporacion;
    }

    public void setFechaIncorporacion(LocalDate fechaIncorporacion) {
        this.fechaIncorporacion = fechaIncorporacion;
    }
    
    

    @Override
    public String toString() {
        return "idMiembroEq= " + idMiembroEq + ", idEquipo= " + equipoId.getIdEquipo() + ", idMiembro= " + miembroId.getIdMiembro() + ", fechaIncorporacion= " + fechaIncorporacion;
    }

}
