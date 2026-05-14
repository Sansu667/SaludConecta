package co.saludconecta.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase utilitaria para gestionar la conexión a la base de datos MySQL
 * mediante JDBC. Implementa el patrón Singleton para reutilizar la conexión.
 */
public class ConexionBaseDatos {

    private static final String URL_CONEXION = 
        "jdbc:mysql://localhost:3306/saludconecta?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO_BD   = "saludconecta";
    private static final String CONTRASENA_BD = "saludconecta123";

    private static Connection conexionActiva = null;

    // Constructor privado: impide instanciación directa
    private ConexionBaseDatos() {}

    /**
     * Retorna una conexión activa a la base de datos.
     * Si no existe una conexión previa, la crea.
     *
     * @return objeto Connection hacia la base de datos saludconecta
     * @throws SQLException si no se puede establecer la conexión
     */
    public static Connection obtenerConexion() throws SQLException {
        if (conexionActiva == null || conexionActiva.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexionActiva = DriverManager.getConnection(
                    URL_CONEXION, USUARIO_BD, CONTRASENA_BD
                );
                System.out.println("Conexión establecida exitosamente.");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver JDBC no encontrado: " + e.getMessage());
            }
        }
        return conexionActiva;
    }

    /**
     * Cierra la conexión activa con la base de datos.
     */
    public static void cerrarConexion() {
        try {
            if (conexionActiva != null && !conexionActiva.isClosed()) {
                conexionActiva.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
