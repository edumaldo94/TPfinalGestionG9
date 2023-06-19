package Entidades;

public class Miembro {

    private int idMiembro;
    private int dni;
    private String nombre;
    private String apellido;
    private boolean estado;

    public Miembro(int dni, String nombre, String apellido, boolean estado) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
    }
    
    public Miembro(int idMiembro, int dni, String nombre, String apellido, boolean estado) {
        this.idMiembro = idMiembro;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
    }

    public Miembro() {
    }

    public int getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(int idMiembro) {
        this.idMiembro = idMiembro;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "idMiembro= " + idMiembro + ", dni= " + dni + ", nombre= " + nombre + ", apellido= " + apellido + ", estado= " + estado ;
    }

}
