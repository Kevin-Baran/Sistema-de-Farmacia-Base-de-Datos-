package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin
 */
public class TipoMedicamentoDAO {

    PreparedStatement ps;
    ResultSet rs;
    //variable de conexion
    Conexion con = new Conexion();
    Connection cn = con.Conectar(); //obtener la conexion

    public List listar() {
        List<TipoMedicamento> lista = new ArrayList<>();
        String sql = "SELECT *FROM tipo_medicamento";
        try {
            cn = con.Conectar();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoMedicamento tmed = new TipoMedicamento();
                tmed.setCod_tipo_medicamento(rs.getInt(1));
                tmed.setNombre(rs.getString(2));
                tmed.setTipo_enfermedad(rs.getString(3));
                lista.add(tmed);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public int add(Object[] o) {
        int r = 0;
        String sql = "INSERT INTO tipo_medicamento (Nombre, Tipo_enfermedad) VALUES (?,?)";
        try {
            cn = con.Conectar();
            ps = cn.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            r = ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Guardado Satisfactoriamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Registro No Guardado");
        }
        return r;
    }

    public int actualizar(Object[] o) {
        int r = 0;
        String sql = "UPDATE Tipo_medicamento SET Nombre=?, Tipo_enfermedad=? WHERE Cod_tipo_medicamento=?";
        try {
            cn = con.Conectar();
            ps = cn.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Registro No Actualizado");
        }
        return r;
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM tipo_medicamento WHERE Cod_tipo_medicamento = ?";
        try {
            cn = con.Conectar();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Registro No Eliminado");
        }
    }
}
