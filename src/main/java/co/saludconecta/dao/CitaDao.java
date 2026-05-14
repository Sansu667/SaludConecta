package co.saludconecta.dao;

import co.saludconecta.conexion.ConexionBaseDatos;
import co.saludconecta.modelo.Cita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de las operaciones CRUD para la entidad Cita.
 */
public class CitaDao implements IOperacionesDao<Cita> {

    /**
     * Inserta una nueva cita médica en la base de datos.
     *
     * @param cita objeto Cita a persistir
     * @return true si la inserción fue exitosa
     */
    @Override
    public boolean insertar(Cita cita) {
        String sentenciaSql = "INSERT INTO cita " +
            "(fecha_cita, hora_cita, estado, id_paciente, id_medico) " +
            "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBaseDatos.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql)) {

            sentencia.setString(1, cita.getFechaCita());
            sentencia.setString(2, cita.getHoraCita());
            sentencia.setString(3, cita.getEstado());
            sentencia.setInt(4, cita.getIdPaciente());
            sentencia.setInt(5, cita.getIdMedico());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar cita: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retorna todas las citas registradas en el sistema.
     *
     * @return lista de objetos Cita
     */
    @Override
    public List<Cita> consultarTodos() {
        List<Cita> listaCitas = new ArrayList<>();
        String sentenciaSql  = "SELECT * FROM cita";

        try (Connection conexion = ConexionBaseDatos.obtenerConexion();
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery(sentenciaSql)) {

            while (resultado.next()) {
                listaCitas.add(mapearResultado(resultado));
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar citas: " + e.getMessage());
        }

        return listaCitas;
    }

    /**
     * Consulta una cita por su identificador único.
     *
     * @param id identificador de la cita
     * @return objeto Cita o null si no existe
     */
    @Override
    public Cita consultarPorId(int id) {
        String sentenciaSql = "SELECT * FROM cita WHERE id_cita = ?";
        Cita cita = null;

        try (Connection conexion = ConexionBaseDatos.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql)) {

            sentencia.setInt(1, id);
            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                cita = mapearResultado(resultado);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar cita por ID: " + e.getMessage());
        }

        return cita;
    }

    /**
     * Actualiza el estado de una cita existente.
     *
     * @param cita objeto Cita con los nuevos datos
     * @return true si la actualización fue exitosa
     */
    @Override
    public boolean actualizar(Cita cita) {
        String sentenciaSql = "UPDATE cita SET " +
            "fecha_cita = ?, hora_cita = ?, estado = ?, " +
            "id_paciente = ?, id_medico = ? " +
            "WHERE id_cita = ?";

        try (Connection conexion = ConexionBaseDatos.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql)) {

            sentencia.setString(1, cita.getFechaCita());
            sentencia.setString(2, cita.getHoraCita());
            sentencia.setString(3, cita.getEstado());
            sentencia.setInt(4, cita.getIdPaciente());
            sentencia.setInt(5, cita.getIdMedico());
            sentencia.setInt(6, cita.getIdCita());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar cita: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina una cita de la base de datos por su identificador.
     *
     * @param id identificador de la cita a eliminar
     * @return true si la eliminación fue exitosa
     */
    @Override
    public boolean eliminar(int id) {
        String sentenciaSql = "DELETE FROM cita WHERE id_cita = ?";

        try (Connection conexion = ConexionBaseDatos.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql)) {

            sentencia.setInt(1, id);
            return sentencia.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar cita: " + e.getMessage());
            return false;
        }
    }

    /**
     * Convierte una fila del ResultSet en un objeto Cita.
     *
     * @param resultado fila del ResultSet
     * @return objeto Cita con los datos de la fila
     * @throws SQLException si ocurre error al leer columnas
     */
    private Cita mapearResultado(ResultSet resultado) throws SQLException {
        return new Cita(
            resultado.getInt("id_cita"),
            resultado.getString("fecha_cita"),
            resultado.getString("hora_cita"),
            resultado.getString("estado"),
            resultado.getInt("id_paciente"),
            resultado.getInt("id_medico")
        );
    }
}
