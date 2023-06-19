package AccesoADatos;

import Entidades.Comentario;
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

public class TareaData {

    private Connection con = null;
    private Conexion conexion;

    public TareaData() {

        con = Conexion.getConexion();

    }

    public void guardarTarea(Tarea tarea) {
        try {
            String sql = "INSERT INTO tarea(nombreT, fechaCreacion, fechaCierre, estado, idMiembroEq) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tarea.getNombre());
            ps.setDate(2, Date.valueOf(tarea.getFechaCreacion()));
            ps.setDate(3, Date.valueOf(tarea.getFechaCierre()));
            ps.setInt(4, tarea.getEstado());
            ps.setInt(5, tarea.getMiembroEqId().getIdMiembroEq());
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Tarea cargada con exito.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                tarea.setIdTarea(rs.getInt(1));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla Tarea" + ex.getMessage());
        }
    }

    public void actualizarEstadoTarea(int estado, int id) {
        try {
            String sql = "UPDATE tarea SET estado=? WHERE idTarea=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, estado);
            ps.setInt(2, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Estado modificado.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo modificar el estado.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR " + ex.getMessage());
        }
    }

    public void areasDeproyecto(int id) {
        Equipo equipo;
        EquipoMiembro eqm;
        Tarea tarea;
        Proyecto proy;
        MiembroData miembroD = new MiembroData();
        ComentarioData comentD = new ComentarioData();
        List<Comentario> listCo = new ArrayList<>();
        Miembro miembro;
        String estado = "";
        try {
            String sql = "SELECT  P.idProyecto AS 'Proyecto', P.nombreP AS 'Proyecto',T.nombreT AS 'Tarea', T.estado AS 'Estado', T.idTarea AS 'id', EM.idMiembro AS 'Miembro del Equipo', E.nombreE AS 'Equipo'\n"
                    + "                                         FROM proyecto AS P \n"
                    + "                                       JOIN equipo AS E ON P.idProyecto = E.idProyecto \n"
                    + "                                     JOIN equipomiembros AS EM ON E.idEquipo = EM.idEquipo \n"
                    + "                                      JOIN tarea AS T ON EM.idMiembroEq = T.idMiembroEq WHERE P.idProyecto= ? AND  T.estado=0 OR T.estado=1  ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                proy = new Proyecto();
                proy.setIdProyecto(rs.getInt("idProyecto"));
                proy.setNombre(rs.getString("nombreP"));
                if (proy.getIdProyecto() == id) {
                    tarea = new Tarea();
                    tarea.setNombre(rs.getString("nombreT"));
                    tarea.setEstado(rs.getInt("estado"));
                    tarea.setIdTarea(rs.getInt("idTarea"));
                    eqm = new EquipoMiembro();
                    miembro = miembroD.buscarMiembroPorId(rs.getInt("idMiembro"));
                    eqm.setMiembroId(miembro);
                    equipo = new Equipo();
                    equipo.setNombre(rs.getString("nombreE"));
                    System.out.println("Nombre del Proyecto:       " + proy.getNombre());
                    System.out.println("Tarea del Proyecto:        " + tarea.getNombre());
                    switch (tarea.getEstado()) {
                        case 0:
                            estado = "Pendiente";
                            break;
                        case 1:
                            estado = "En Proceso";
                            break;
                        case 2:
                            estado = "Completada";
                            break;
                        default:
                            throw new AssertionError();
                    }
                    System.out.println("Estado de la Tarea:        " + estado);
                    listCo = comentD.consultarComentarios(tarea.getIdTarea());
                    for (Comentario comentario : listCo) {
                        System.out.println("Comentario: " + comentario.getComentario());
                        System.out.println("Fecha de Avance: " + comentario.getFechaAvance());
                    }
                    System.out.println("ID de Miembro del Equipo:  " + eqm.getMiembroId().getIdMiembro());
                    System.out.println("Nombre de Equipo:          " + equipo.getNombre());
                    System.out.println("");
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public Tarea buscarTareaPorId(int idt) {
        Tarea tarea = new Tarea();
        EquipoMiembro equipoMiembro = new EquipoMiembro();
        EquipoMiembroData equipoMiembroD = new EquipoMiembroData();
        String sql = "SELECT * FROM tarea WHERE idTarea=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idt);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tarea.setEstado(rs.getInt("estado"));
                tarea.setFechaCierre(rs.getDate("fechaCierre").toLocalDate());
                tarea.setFechaCreacion(rs.getDate("fechaCreacion").toLocalDate());
                tarea.setIdTarea(rs.getInt("idTarea"));
                equipoMiembro = equipoMiembroD.buscarEquipoMiembroPorId(rs.getInt("idMiembroEq"));
                tarea.setMiembroEqId(equipoMiembro);
                tarea.setNombre(rs.getString("nombreT"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe la tarea");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla Tarea" + ex.getMessage());
        }
        return tarea;
    }

    public Tarea buscarTareaPorIdMiEq(int idMiEq) {
        Tarea tarea = new Tarea();
        EquipoMiembro equipoMiembro = new EquipoMiembro();
        EquipoMiembroData equipoMiembroD = new EquipoMiembroData();
        String sql = "SELECT * FROM tarea WHERE idMiembroEq=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idMiEq);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tarea.setEstado(rs.getInt("estado"));
                tarea.setFechaCierre(rs.getDate("fechaCierre").toLocalDate());
                tarea.setFechaCreacion(rs.getDate("fechaCreacion").toLocalDate());
                tarea.setIdTarea(rs.getInt("idTarea"));
                equipoMiembro = equipoMiembroD.buscarEquipoMiembroPorId(rs.getInt("idMiembroEq"));
                tarea.setMiembroEqId(equipoMiembro);
                tarea.setNombre(rs.getString("nombreT"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe la tarea");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla Tarea" + ex.getMessage());
        }
        return tarea;
    }

    public void borrarTarea(int idMiEq) {
        Tarea tarea;
        ComentarioData comD = new ComentarioData();
        try {
            tarea = buscarTareaPorIdMiEq(idMiEq);
            comD.borrarComentario(tarea.getIdTarea());
            String sql = "DELETE FROM tarea WHERE idMiembroEq = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMiEq);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Tarea eliminada.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo borrar la Tarea.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public List<String> areasDeproyectoParaVista(int id) {
        Equipo equipo;
        EquipoMiembro eqm;
        Tarea tarea;
        Proyecto proy;
        MiembroData miembroD = new MiembroData();
        ComentarioData comentD = new ComentarioData();
        List<Comentario> listCo = new ArrayList<>();
        Miembro miembro;
        String estado = "";
        List<String> resultados = new ArrayList<>();

        try {
            String sql = "SELECT  P.idProyecto AS 'Proyecto', P.nombreP AS 'Proyecto',T.nombreT AS 'Tarea', T.estado AS 'Estado', T.idTarea AS 'id', EM.idMiembro AS 'Miembro del Equipo', E.nombreE AS 'Equipo'\n"
                    + "FROM proyecto AS P \n"
                    + "JOIN equipo AS E ON P.idProyecto = E.idProyecto \n"
                    + "JOIN equipomiembros AS EM ON E.idEquipo = EM.idEquipo \n"
                    + "JOIN tarea AS T ON EM.idMiembroEq = T.idMiembroEq WHERE P.idProyecto= ? AND  T.estado=0 OR T.estado=1";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                proy = new Proyecto();
                proy.setIdProyecto(rs.getInt("idProyecto"));
                proy.setNombre(rs.getString("nombreP"));

                if (proy.getIdProyecto() == id) {
                    tarea = new Tarea();
                    tarea.setNombre(rs.getString("nombreT"));
                    tarea.setEstado(rs.getInt("estado"));
                    tarea.setIdTarea(rs.getInt("idTarea"));
                    eqm = new EquipoMiembro();
                    miembro = miembroD.buscarMiembroPorId(rs.getInt("idMiembro"));
                    eqm.setMiembroId(miembro);
                    equipo = new Equipo();
                    equipo.setNombre(rs.getString("nombreE"));

                    String resultado = "Nombre del Proyecto:       " + proy.getNombre() + "\n"
                            + "Tarea del Proyecto:        " + tarea.getNombre() + "\n";

                    switch (tarea.getEstado()) {
                        case 0:
                            estado = "Pendiente";
                            break;
                        case 1:
                            estado = "En Proceso";
                            break;
                        case 2:
                            estado = "Completada";
                            break;
                        default:
                            throw new AssertionError();
                    }
                    resultado += "Estado de la Tarea:        " + estado + "\n";

                    listCo = comentD.consultarComentarios(tarea.getIdTarea());
                    for (Comentario comentario : listCo) {
                        resultado += "Comentario: " + comentario.getComentario() + "\n"
                                + "Fecha de Avance: " + comentario.getFechaAvance() + "\n";
                    }

                    resultado += "ID de Miembro del Equipo:  " + eqm.getMiembroId().getIdMiembro() + "\n"
                            + "Nombre de Equipo:          " + equipo.getNombre() + "\n\n";

                    resultados.add(resultado);
                }
            }

            rs.close();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        return resultados;
    }
    
    public List<Tarea> listarTareas(){
        List<Tarea> lista = new ArrayList<>();
        EquipoMiembroData emD = new EquipoMiembroData();
        try{
            String sql = "SELECT * FROM tarea";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tarea tarea = new Tarea();
                tarea.setEstado(rs.getInt("estado"));
                tarea.setFechaCierre(rs.getDate("fechaCierre").toLocalDate());
                tarea.setFechaCreacion(rs.getDate("fechaCreacion").toLocalDate());
                tarea.setIdTarea(rs.getInt("idTarea"));
                EquipoMiembro em = emD.buscarEquipoMiembroPorId(rs.getInt("idMiembroEq"));
                tarea.setMiembroEqId(em);
                tarea.setNombre(rs.getString("nombreT"));
                lista.add(tarea);
            }
        }catch(SQLException e){
        
        }
        return lista;
    }

}
