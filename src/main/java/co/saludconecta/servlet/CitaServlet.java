package co.saludconecta.servlet;

import co.saludconecta.dao.CitaDao;
import co.saludconecta.modelo.Cita;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Servlet que gestiona las operaciones CRUD de la entidad Cita.
 * Atiende peticiones GET (listar, editar, eliminar)
 * y POST (insertar, actualizar).
 */
public class CitaServlet extends HttpServlet {

    private static final long serialVersionUID = 2L;
    private final CitaDao citaDao = new CitaDao();

    /**
     * Procesa las peticiones GET sobre citas médicas.
     * Acciones: listar, nuevo, editar, eliminar.
     *
     * @param solicitud  HttpServletRequest
     * @param respuesta  HttpServletResponse
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
                    "/vistas/citas/formularioCita.jsp"
                ).forward(solicitud, respuesta);
                break;

            case "editar":
                int idEditar = Integer.parseInt(
                    solicitud.getParameter("id")
                );
                Cita citaAEditar = citaDao.consultarPorId(idEditar);
                solicitud.setAttribute("cita", citaAEditar);
                solicitud.getRequestDispatcher(
                    "/vistas/citas/editarCita.jsp"
                ).forward(solicitud, respuesta);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(
                    solicitud.getParameter("id")
                );
                citaDao.eliminar(idEliminar);
                respuesta.sendRedirect(
                    solicitud.getContextPath() + "/citas?accion=listar"
                );
                break;

            case "listar":
            default:
                List<Cita> listaCitas = citaDao.consultarTodos();
                solicitud.setAttribute("listaCitas", listaCitas);
                solicitud.getRequestDispatcher(
                    "/vistas/citas/listaCitas.jsp"
                ).forward(solicitud, respuesta);
                break;
        }
    }

    /**
     * Procesa las peticiones POST sobre citas médicas.
     * Acciones: insertar, actualizar.
     *
     * @param solicitud  HttpServletRequest
     * @param respuesta  HttpServletResponse
     */
    @Override
    protected void doPost(HttpServletRequest solicitud,
                          HttpServletResponse respuesta)
            throws ServletException, IOException {

        solicitud.setCharacterEncoding("UTF-8");
        String accion = solicitud.getParameter("accion");

        if ("insertar".equals(accion)) {
            Cita nuevaCita = construirCitaDesdeSolicitud(solicitud, 0);
            citaDao.insertar(nuevaCita);

        } else if ("actualizar".equals(accion)) {
            int idCita = Integer.parseInt(
                solicitud.getParameter("idCita")
            );
            Cita citaActualizada = construirCitaDesdeSolicitud(
                solicitud, idCita
            );
            citaDao.actualizar(citaActualizada);
        }

        respuesta.sendRedirect(
            solicitud.getContextPath() + "/citas?accion=listar"
        );
    }

    /**
     * Construye un objeto Cita a partir de los parámetros
     * recibidos en la solicitud HTTP.
     *
     * @param solicitud  solicitud con los parámetros del formulario
     * @param id         identificador de la cita (0 si es nueva)
     * @return objeto Cita listo para persistir
     */
    private Cita construirCitaDesdeSolicitud(
            HttpServletRequest solicitud, int id) {

        return new Cita(
            id,
            solicitud.getParameter("fechaCita"),
            solicitud.getParameter("horaCita"),
            solicitud.getParameter("estado"),
            Integer.parseInt(solicitud.getParameter("idPaciente")),
            Integer.parseInt(solicitud.getParameter("idMedico"))
        );
    }
}
