package co.saludconecta.dao;

import co.saludconecta.conexion.ConexionBaseDatos;
import co.saludconecta.modelo.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de las operaciones CRUD para la entidad Paciente.
 * Utiliza JDBC para comunicarse directamente con la base de datos MySQL.
 */
public class PacienteDao implements IOperacionesDao<Paciente> {

    // ----------------------------------------------------------------
    // INSERTAR
    // ----------------------------------------------------------------

    /**
     * Inserta un nuevo paciente en la tabla 'paciente'.
     *
     * @param paciente objeto Paciente con los datos a insertar
     * @return true si la inserción fue exitosa
     */
    @Override
    public boolean insertar(Paciente paciente) {
        String sentenciaSql = "INSERT INTO paciente " +
            "(nombres, apellidos, tipo_doc, num_doc, fecha_nac, email, telefono, direccion) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBaseDatos.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql)) {

            sentencia.setString(1, paciente.getNombres());
            sentencia.setString(2, paciente.getApellidos());
            sentencia.setString(3, paciente.getTipoDoc());
            sentencia.setString(4, paciente.getNumDoc());
            sentencia.setString(5, paciente.getFechaNac());
            sentencia.setString(6, paciente.getEmail());
            sentencia.setString(7, paciente.getTelefono());
            sentencia.setString(8, paciente.getDireccion());

            int filasAfectadas = sentencia.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar paciente: " + e.getMessage());
            return false;
        }
    }

    // ----------------------------------------------------------------
    // CONSULTAR TODOS
    // ----------------------------------------------------------------

    /**
     * Retorna la lista completa de pacientes registrados en el sistema.
     *
     * @return lista de objetos Paciente
     */
    @Override
    public List<Paciente> consultarTodos() {
        List<Paciente> listaPacientes = new ArrayList<>();
        String sentenciaSql = "SELECT * FROM paciente";

        try (Connection conexion = ConexionBaseDatos.obtenerConexion();
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery(sentenciaSql)) {

            while (resultado.next()) {
                Paciente paciente = mapearResultado(resultado);
                listaPacientes.add(paciente);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar pacientes: " + e.getMessage());
        }

        return listaPacientes;
    }

    // ----------------------------------------------------------------
    // CONSULTAR POR ID
    // ----------------------------------------------------------------

    /**
     * Busca un paciente específico por su identificador único.
     *
     * @param id identificador del paciente
     * @return objeto Paciente si existe, null si no se encuentra
     */
    @Override
    public Paciente consultarPorId(int id) {
        String sentenciaSql = "SELECT * FROM paciente WHERE id_paciente = ?";
        Paciente paciente = null;

        try (Connection conexion = ConexionBaseDatos.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql)) {

            sentencia.setInt(1, id);
            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                paciente = mapearResultado(resultado);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar paciente por ID: " + e.getMessage());
        }

        return paciente;
    }

    // ----------------------------------------------------------------
    // ACTUALIZAR
    // ----------------------------------------------------------------

    /**
     * Actualiza los datos de un paciente existente.
     *
     * @param paciente objeto Paciente con los datos modificados
     * @return true si la actualización fue exitosa
     */
    @Override
    public boolean actualizar(Paciente paciente) {
        String sentenciaSql = "UPDATE paciente SET " +
            "nombres = ?, apellidos = ?, tipo_doc = ?, num_doc = ?, " +
            "fecha_nac = ?, email = ?, telefono = ?, direccion = ? " +
            "WHERE id_paciente = ?";

        try (Connection conexion = ConexionBaseDatos.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql)) {

            sentencia.setString(1, paciente.getNombres());
            sentencia.setString(2, paciente.getApellidos());
            sentencia.setString(3, paciente.getTipoDoc());
            sentencia.setString(4, paciente.getNumDoc());
            sentencia.setString(5, paciente.getFechaNac());
            sentencia.setString(6, paciente.getEmail());
            sentencia.setString(7, paciente.getTelefono());
            sentencia.setString(8, paciente.getDireccion());
            sentencia.setInt(9, paciente.getIdPaciente());

            int filasAfectadas = sentencia.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar paciente: " + e.getMessage());
            return false;
        }
    }

    // ----------------------------------------------------------------
    // ELIMINAR
    // ----------------------------------------------------------------

    /**
     * Elimina un paciente del sistema por su identificador.
     *
     * @param id identificador del paciente a eliminar
     * @return true si la eliminación fue exitosa
     */
    @Override
    public boolean eliminar(int id) {
        String sentenciaSql = "DELETE FROM paciente WHERE id_paciente = ?";

        try (Connection conexion = ConexionBaseDatos.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql)) {

            sentencia.setInt(1, id);
            int filasAfectadas = sentencia.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar paciente: " + e.getMessage());
            return false;
        }
    }

    // ----------------------------------------------------------------
    // MÉTODO AUXILIAR DE MAPEO
    // ----------------------------------------------------------------

    /**
     * Convierte una fila del ResultSet en un objeto Paciente.
     *
     * @param resultado fila actual del ResultSet
     * @return objeto Paciente construido con los datos de la fila
     * @throws SQLException si ocurre un error al leer las columnas
     */
    private Paciente mapearResultado(ResultSet resultado) throws SQLException {
        return new Paciente(
            resultado.getInt("id_paciente"),
            resultado.getString("nombres"),
            resultado.getString("apellidos"),
            resultado.getString("tipo_doc"),
            resultado.getString("num_doc"),
            resultado.getString("fecha_nac"),
            resultado.getString("email"),
            resultado.getString("telefono"),
            resultado.getString("direccion")
        );
    }
}
