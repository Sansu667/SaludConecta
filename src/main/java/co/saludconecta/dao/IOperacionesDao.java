package co.saludconecta.dao;

import java.util.List;

/**
 * Interfaz genérica que define las operaciones CRUD estándar
 * para cualquier entidad del sistema SaludConecta.
 *
 * @param <T> tipo de entidad sobre la que se aplican las operaciones
 */
public interface IOperacionesDao<T> {

    /**
     * Inserta un nuevo registro en la base de datos.
     * @param entidad objeto a persistir
     * @return true si la inserción fue exitosa
     */
    boolean insertar(T entidad);

    /**
     * Consulta todos los registros de la entidad.
     * @return lista de entidades encontradas
     */
    List<T> consultarTodos();

    /**
     * Consulta un registro específico por su identificador.
     * @param id identificador único del registro
     * @return entidad encontrada o null si no existe
     */
    T consultarPorId(int id);

    /**
     * Actualiza un registro existente en la base de datos.
     * @param entidad objeto con los datos actualizados
     * @return true si la actualización fue exitosa
     */
    boolean actualizar(T entidad);

    /**
     * Elimina un registro de la base de datos por su identificador.
     * @param id identificador único del registro a eliminar
     * @return true si la eliminación fue exitosa
     */
    boolean eliminar(int id);
}
