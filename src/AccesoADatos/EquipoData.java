package AccesoADatos;

import Entidades.Equipo;
import Entidades.EquipoMiembro;
import Entidades.Miembro;
import Entidades.Proyecto;
import Entidades.Tarea;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class EquipoData {
    private Connection con = null;
    private Conexion conexion;

    public EquipoData() {

        con = Conexion.getConexion();
        
    }

    public void guardarEquipo(Equipo equipo) {
        try {
            String sql = "INSERT INTO equipo(idProyecto, nombreE, fechaCreacion, estado) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, equipo.getProyectoId().getIdProyecto());
            ps.setString(2, equipo.getNombre());
            ps.setDate(3, Date.valueOf(equipo.getFechaCreacion()));
            ps.setBoolean(4, equipo.isEstado());
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Equipo creado con exitoso");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                equipo.setIdEquipo(rs.getInt(1));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla Equipo " + ex.getMessage());
        }
    }

    public void infoDeEquipo(int idEq) {
        EquipoMiembro eqm;
        Miembro miembro;
        Tarea tarea;
        String sql = "SELECT M.nombreM AS 'Nombre del Miembro', M.apellido AS 'Apellido', M.dni AS 'DNI',\n"
                + "       GROUP_CONCAT(T.nombreT  SEPARATOR ' -  ') AS 'Tareas asignadas', E.fechaCreacion AS 'Fecha de Creación',\n"
                + "       EM.fechaIncorporacion AS 'Fecha de Incorporación'\n"
                + "FROM equipo AS E\n"
                + "JOIN equipomiembros AS EM ON E.idEquipo = EM.idEquipo\n"
                + "JOIN miembro AS M ON EM.idMiembro = M.idMiembro\n"
                + "JOIN tarea AS T ON EM.idMiembroEq = T.idMiembroEq\n"
                + "WHERE E.idEquipo = ?\n"
                + "GROUP BY M.idMiembro;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idEq);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                miembro = new Miembro();
                miembro.setNombre(rs.getString("nombreM"));
                miembro.setApellido(rs.getString("apellido"));
                miembro.setDni(rs.getInt("dni"));
                tarea = new Tarea();
                tarea.setNombre(rs.getString("Tareas asignadas"));
                eqm = new EquipoMiembro();
                eqm.setFechaIncorporacion(rs.getDate("fechaIncorporacion").toLocalDate());
                System.out.println("Nombre del Miembro:       " + miembro.getNombre());
                System.out.println("Apellido del Miembro:     " + miembro.getApellido());
                System.out.println("DNI del Miembro:          " + miembro.getDni());
                System.out.println("Tareas asignadas:         " + tarea.getNombre());
                System.out.println("Fecha de Incorporación:   " + eqm.getFechaIncorporacion());
                System.out.println("");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public List<Equipo> listaEquipo() {
        List<Equipo> listaEq = new ArrayList<>();
        ProyectoData proyD = new ProyectoData();
        Proyecto proy;
        try {
            String sql = "SELECT * FROM equipo";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Equipo equipo = new Equipo();
                proy = proyD.buscarProyectoPorId(rs.getInt("idProyecto"));
                equipo.setProyectoId(proy);
                equipo.setIdEquipo(rs.getInt("idEquipo"));
                equipo.setNombre(rs.getString("nombreE"));
                equipo.setFechaCreacion(rs.getDate("fechaCreacion").toLocalDate());
                equipo.setEstado(rs.getBoolean("estado"));
                listaEq.add(equipo);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        return listaEq;
    }

    public Equipo buscarEquipoPorId(int ide) {
        Equipo equipo = new Equipo();
        ProyectoData proD = new ProyectoData();
        Proyecto proyecto;
        String sql = "SELECT * FROM equipo WHERE idEquipo=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, ide);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                proyecto = proD.buscarProyectoPorId(rs.getInt("idProyecto"));
                equipo.setProyectoId(proyecto);
                equipo.setIdEquipo(rs.getInt("idEquipo"));
                equipo.setNombre(rs.getString("nombreE"));
                equipo.setFechaCreacion(rs.getDate("fechaCreacion").toLocalDate());
                equipo.setEstado(rs.getBoolean("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe el equipo");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla Equipo" + ex.getMessage());
        }
        return equipo;
    }

    public void activarEquipo(int id) {
        try {
            String sql = "UPDATE equipo SET estado = 1 WHERE idEquipo = ? AND estado = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setBoolean(2, false);
            int fila = ps.executeUpdate();
            ps.close();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, " Se activó el equipo.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Equipo " + e.getMessage());
        }
    }

    public void desactivarEquipo(int id) {
        try {
            String sql = "UPDATE equipo SET estado = 0 WHERE idEquipo = ? AND estado = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setBoolean(2, true);
            int fila = ps.executeUpdate();
            ps.close();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, " Se desactivó el equipo.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Equipo " + e.getMessage());
        }
    }

    public void activarDesactivar(int id) {
        Equipo equipo = buscarEquipoPorId(id);
        if (equipo.isEstado()) {
            desactivarEquipo(id);
        } else {
            activarEquipo(id);
        }
    }

    public List<String> informeDeEquipo(int idEq) {
        List<String> resultados = new ArrayList<>();
        EquipoMiembro eqm;
        Miembro miembro;
        Tarea tarea;
        String sql = "SELECT M.nombreM AS 'Nombre del Miembro', M.apellido AS 'Apellido', M.dni AS 'DNI',\n"
                + "       GROUP_CONCAT(T.nombreT  SEPARATOR ' -  ') AS 'Tareas asignadas', E.fechaCreacion AS 'Fecha de Creación',\n"
                + "       EM.fechaIncorporacion AS 'Fecha de Incorporación'\n"
                + "FROM equipo AS E\n"
                + "JOIN equipomiembros AS EM ON E.idEquipo = EM.idEquipo\n"
                + "JOIN miembro AS M ON EM.idMiembro = M.idMiembro\n"
                + "JOIN tarea AS T ON EM.idMiembroEq = T.idMiembroEq\n"
                + "WHERE E.idEquipo = ?\n"
                + "GROUP BY M.idMiembro;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idEq);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                miembro = new Miembro();
                miembro.setNombre(rs.getString("nombreM"));
                miembro.setApellido(rs.getString("apellido"));
                miembro.setDni(rs.getInt("dni"));
                tarea = new Tarea();
                tarea.setNombre(rs.getString("Tareas asignadas"));
                eqm = new EquipoMiembro();
                eqm.setFechaIncorporacion(rs.getDate("fechaIncorporacion").toLocalDate());

                String resultado
                        = "Nombre del Miembro:       " + miembro.getNombre() + "\n"
                        + "Apellido del Miembro:        " + miembro.getApellido() + "\n"
                        + "DNI del Miembro:                " + miembro.getDni() + "\n"
                        + "Tareas asignadas:             " + tarea.getNombre() + "\n"
                        + "Fecha de Incorporación:    " + eqm.getFechaIncorporacion() + "\n";

                resultados.add(resultado);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        return resultados;
    }
    
    public Equipo devolverEq(String nombre){
        Equipo equipo = new Equipo();
        ProyectoData proD = new ProyectoData();
        Proyecto proyecto = new Proyecto();
        String sql = "SELECT * FROM equipo WHERE nombreE = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                proyecto = proD.buscarProyectoPorId(rs.getInt("idProyecto"));
                equipo.setProyectoId(proyecto);
                equipo.setIdEquipo(rs.getInt("idEquipo"));
                equipo.setNombre(rs.getString("nombreE"));
                equipo.setFechaCreacion(rs.getDate("fechaCreacion").toLocalDate());
                equipo.setEstado(rs.getBoolean("estado"));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        return equipo;
    }
}
