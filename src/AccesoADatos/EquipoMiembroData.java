package AccesoADatos;

import Entidades.Equipo;
import Entidades.EquipoMiembro;
import Entidades.Miembro;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class EquipoMiembroData {

    private Connection con = null;
    private Conexion conexion;

    public EquipoMiembroData() {

        con = Conexion.getConexion();

    }

    public void guardarEquipoMiembro(EquipoMiembro equipoMiembro) {
        try {
            String sql = "INSERT INTO equipomiembros(fechaIncorporacion, idEquipo, idMiembro) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(equipoMiembro.getFechaIncorporacion()));
            ps.setInt(2, equipoMiembro.getEquipoId().getIdEquipo());
            ps.setInt(3, equipoMiembro.getMiembroId().getIdMiembro());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Inscripcion de miembro a equipo con exito");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                equipoMiembro.setIdMiembroEq(rs.getInt(1));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla EquipoMiembros" + ex.getMessage());
        }
    }

    public EquipoMiembro buscarEquipoMiembroPorId(int idEqM) {
        EquipoMiembro equipoMiembro = new EquipoMiembro();
        Equipo equipo;
        EquipoData equipoD = new EquipoData();
        Miembro miembro;
        MiembroData miembroD = new MiembroData();
        String sql = "SELECT * FROM equipomiembros WHERE idMiembroEq=?";
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, idEqM);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                equipo = equipoD.buscarEquipoPorId(rs.getInt("idEquipo"));
                miembro = miembroD.buscarMiembroPorId(rs.getInt("idMiembro"));
                equipoMiembro.setEquipoId(equipo);
                equipoMiembro.setMiembroId(miembro);
                equipoMiembro.setIdMiembroEq(idEqM);
                equipoMiembro.setFechaIncorporacion(rs.getDate("fechaIncorporacion").toLocalDate());
            } else {
                JOptionPane.showMessageDialog(null, "No existe el equipo-miembro");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla Equipo-Miembro" + ex.getMessage());
        }
        
        return equipoMiembro;
    }
    
    public EquipoMiembro buscarEquipoMiembroPorIdMiembro(int idM) {
        EquipoMiembro equipoMiembro = new EquipoMiembro();
        Equipo equipo;
        EquipoData equipoD = new EquipoData();
        Miembro miembro;
        MiembroData miembroD = new MiembroData();
        String sql = "SELECT * FROM equipomiembros WHERE idMiembro=?";
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, idM);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                equipo = equipoD.buscarEquipoPorId(rs.getInt("idEquipo"));
                miembro = miembroD.buscarMiembroPorId(idM);
                equipoMiembro.setEquipoId(equipo);
                equipoMiembro.setMiembroId(miembro);
                equipoMiembro.setIdMiembroEq(rs.getInt("idMiembroEq"));
                equipoMiembro.setFechaIncorporacion(rs.getDate("fechaIncorporacion").toLocalDate());
            } else {
                JOptionPane.showMessageDialog(null, "No existe el equipo-miembro");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla Equipo-Miembro" + ex.getMessage());
        }
        
        return equipoMiembro;
    }
    
    public String devolverEquiposId(int id){
        String sql = "SELECT * FROM equipomiembros WHERE idEquipo = ?";
        PreparedStatement ps = null;
        int cont = 0;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cont++;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla Equipo-Miembro" + ex.getMessage());
        }
        return cont+"";
    }
    
    public List<EquipoMiembro> listarEM(){
        List<EquipoMiembro> lista = new ArrayList<>();
        EquipoMiembro em;
        EquipoData eqD = new EquipoData();
        Equipo eq;
        Miembro mi;
        MiembroData miD = new MiembroData();
        String sql = "SELECT * FROM equipomiembros";
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                em = new EquipoMiembro();
                eq = eqD.buscarEquipoPorId(rs.getInt("idEquipo"));
                em.setEquipoId(eq);
                em.setFechaIncorporacion(rs.getDate("fechaIncorporacion").toLocalDate());
                mi = miD.buscarMiembroPorId(rs.getInt("idMiembro"));
                em.setMiembroId(mi);
                em.setIdMiembroEq(rs.getInt("idMiembroEq"));
                lista.add(em);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla Equipo-Miembro " + ex.getMessage());
        }
        return lista;
    }
    
    public void borrarMiembroEq(int id){
        TareaData tareaD = new TareaData();
        try{
            tareaD.borrarTarea(id);
            String sql = "DELETE FROM equipomiembros WHERE idMiembroEq = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito==1) {
                JOptionPane.showMessageDialog(null, "MiembroEquipo eliminado.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo borrar el MiembroEquipo.");
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
        }
    }
}
