package AccesoADatos;

import Entidades.Comentario;
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

public class ComentarioData {

    private Connection con = null;
    private Conexion conexion;

    public ComentarioData() {
        con = Conexion.getConexion();
    }

    public void guardarComentario(Comentario comentario) {
        try {
            String sql = "INSERT INTO comentarios(comentario, fechaAvance, idTarea) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, comentario.getComentario());
            ps.setDate(2, Date.valueOf(comentario.getFechaAvance()));
            ps.setInt(3, comentario.getTareaId().getIdTarea());
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Comentario cargado exitosamente");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                comentario.setIdCometario(rs.getInt(1));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla Comentario "+ex.getMessage());
        }
    }
    
    public List<Comentario> consultarComentarios(int idTarea){
        List<Comentario> listaCo = new ArrayList<>();
        String sql = "SELECT * FROM comentarios WHERE idTarea=?";
        PreparedStatement ps = null;
        Tarea tarea;
        TareaData tareaD = new TareaData();
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, idTarea);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Comentario comentario = new Comentario();
                comentario.setComentario(rs.getString("comentario"));
                comentario.setFechaAvance(rs.getDate("fechaAvance").toLocalDate());
                comentario.setIdCometario(rs.getInt("idComentario"));
                tarea = tareaD.buscarTareaPorId(idTarea);
                comentario.setTareaId(tarea);
                listaCo.add(comentario);
            } else {
                JOptionPane.showMessageDialog(null, "No existen comentarios en la tarea "+idTarea);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla Comentario" + ex.getMessage());
        }
        return listaCo;
    }
    
    public void borrarComentario(int idT){
        try {
            String sql = "DELETE FROM comentarios WHERE idTarea = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,idT);
            int exito = ps.executeUpdate();
            if (exito==1) {
                JOptionPane.showMessageDialog(null, "Comentario eliminado.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo borrar el comentario.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex.getMessage());
        }
    }
}
