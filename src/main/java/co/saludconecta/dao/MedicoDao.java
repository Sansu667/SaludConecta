package co.saludconecta.dao;

import co.saludconecta.conexion.ConexionBaseDatos;
import co.saludconecta.modelo.Medico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDao implements IOperacionesDao<Medico> {

    @Override
    public boolean insertar(Medico medico) {
        String sql = "INSERT INTO medico (nombres, apellidos, num_registro, email, telefono, id_especialidad) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBaseDatos.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, medico.getNombres());
            ps.setString(2, medico.getApellidos());
            ps.setString(3, medico.getNumRegistro());
            ps.setString(4, medico.getEmail());
            ps.setString(5, medico.getTelefono());
            ps.setInt(6, medico.getIdEspecialidad());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar médico: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Medico> consultarTodos() {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM medico";

        try (Connection con = ConexionBaseDatos.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar médicos: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public Medico consultarPorId(int id) {
        String sql = "SELECT * FROM medico WHERE id_medico = ?";

        try (Connection con = ConexionBaseDatos.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapear(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar médico por ID: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean actualizar(Medico medico) {
        String sql = "UPDATE medico SET nombres = ?, apellidos = ?, num_registro = ?, " +
                     "email = ?, telefono = ?, id_especialidad = ? WHERE id_medico = ?";

        try (Connection con = ConexionBaseDatos.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, medico.getNombres());
            ps.setString(2, medico.getApellidos());
            ps.setString(3, medico.getNumRegistro());
            ps.setString(4, medico.getEmail());
            ps.setString(5, medico.getTelefono());
            ps.setInt(6, medico.getIdEspecialidad());
            ps.setInt(7, medico.getIdMedico());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar médico: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM medico WHERE id_medico = ?";

        try (Connection con = ConexionBaseDatos.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar médico: " + e.getMessage());
            return false;
        }
    }

    private Medico mapear(ResultSet rs) throws SQLException {
        return new Medico(
            rs.getInt("id_medico"),
            rs.getString("nombres"),
            rs.getString("apellidos"),
            rs.getString("num_registro"),
            rs.getString("email"),
            rs.getString("telefono"),
            rs.getInt("id_especialidad")
        );
    }
}
