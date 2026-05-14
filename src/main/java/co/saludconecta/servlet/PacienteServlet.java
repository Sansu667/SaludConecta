package co.saludconecta.servlet;

import co.saludconecta.dao.PacienteDao;
import co.saludconecta.modelo.Paciente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Servlet que gestiona las operaciones CRUD de la entidad Paciente.
 * Atiende peticiones GET (listar, mostrar formulario de edición)
 * y POST (insertar, actualizar, eliminar).
 */
public class PacienteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final PacienteDao pacienteDao = new PacienteDao();

    // ----------------------------------------------------------------
    // GET — listar pacientes y mostrar formularios
    // ----------------------------------------------------------------

    /**
     * Procesa las peticiones GET.
     * Acciones disponibles via parámetro 'accion':
     *   - listar   : muestra todos los pacientes (por defecto)
     *   - nuevo    : muestra el formulario de registro
     *   - editar   : muestra el formulario con datos precargados
     *   - eliminar : elimina el paciente y redirige al listado
     *
     * @param solicitud  objeto HttpServletRequest
     * @param respuesta  objeto HttpServletResponse
     */
    @Override
    protected void doGet(HttpServletRequest solicitud,
                         HttpServletResponse respuesta)
            throws ServletException, IOException {

        String accion = solicitud.getParameter("accion");
        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {

            case "nuevo":
                solicitud.getRequestDispatcher(
                    "/vistas/pacientes/formularioPaciente.jsp"
                ).forward(solicitud, respuesta);
                break;

            case "editar":
                int idEditar = Integer.parseInt(
                    solicitud.getParameter("id")
                );
                Paciente pacienteAEditar = pacienteDao.consultarPorId(idEditar);
                solicitud.setAttribute("paciente", pacienteAEditar);
                solicitud.getRequestDispatcher(
                    "/vistas/pacientes/editarPaciente.jsp"
                ).forward(solicitud, respuesta);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(
                    solicitud.getParameter("id")
                );
                pacienteDao.eliminar(idEliminar);
                respuesta.sendRedirect(
                    solicitud.getContextPath() + "/pacientes?accion=listar"
                );
                break;

            case "listar":
            default:
                List<Paciente> listaPacientes = pacienteDao.consultarTodos();
                solicitud.setAttribute("listaPacientes", listaPacientes);
                solicitud.getRequestDispatcher(
                    "/vistas/pacientes/listaPacientes.jsp"
                ).forward(solicitud, respuesta);
                break;
        }
    }

    // ----------------------------------------------------------------
    // POST — insertar y actualizar pacientes
    // ----------------------------------------------------------------

    /**
     * Procesa las peticiones POST.
     * Acciones disponibles via parámetro 'accion':
     *   - insertar   : registra un nuevo paciente
     *   - actualizar : modifica un paciente existente
     *
     * @param solicitud  objeto HttpServletRequest
     * @param respuesta  objeto HttpServletResponse
     */
    @Override
    protected void doPost(HttpServletRequest solicitud,
                          HttpServletResponse respuesta)
            throws ServletException, IOException {

        solicitud.setCharacterEncoding("UTF-8");
        String accion = solicitud.getParameter("accion");

        if ("insertar".equals(accion)) {
            Paciente nuevoPaciente = construirPacienteDesdeSolicitud(solicitud, 0);
            pacienteDao.insertar(nuevoPaciente);

        } else if ("actualizar".equals(accion)) {
            int idPaciente = Integer.parseInt(
                solicitud.getParameter("idPaciente")
            );
            Paciente pacienteActualizado = construirPacienteDesdeSolicitud(
                solicitud, idPaciente
            );
            pacienteDao.actualizar(pacienteActualizado);
        }

        respuesta.sendRedirect(
            solicitud.getContextPath() + "/pacientes?accion=listar"
        );
    }

    // ----------------------------------------------------------------
    // MÉTODO AUXILIAR
    // ----------------------------------------------------------------

    /**
     * Construye un objeto Paciente a partir de los parámetros
     * recibidos en la solicitud HTTP.
     *
     * @param solicitud  solicitud con los parámetros del formulario
     * @param id         identificador del paciente (0 si es nuevo)
     * @return objeto Paciente listo para persistir
     */
    private Paciente construirPacienteDesdeSolicitud(
            HttpServletRequest solicitud, int id) {

        return new Paciente(
            id,
            solicitud.getParameter("nombres"),
            solicitud.getParameter("apellidos"),
            solicitud.getParameter("tipoDoc"),
            solicitud.getParameter("numDoc"),
            solicitud.getParameter("fechaNac"),
            solicitud.getParameter("email"),
            solicitud.getParameter("telefono"),
            solicitud.getParameter("direccion")
        );
    }
}

