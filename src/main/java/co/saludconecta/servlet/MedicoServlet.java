package co.saludconecta.servlet;

import co.saludconecta.dao.MedicoDao;
import co.saludconecta.modelo.Medico;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.http.*;
import java.io.*;
import java.util.List;

/**
 * Servlet REST para la entidad Médico.
 * Devuelve y recibe JSON; no reenvía a vistas JSP.
 * Incluye cabeceras CORS para ser consumido desde el frontend React.
 */
public class MedicoServlet extends HttpServlet {

    private static final long serialVersionUID = 3L;
    private final MedicoDao medicoDao = new MedicoDao();
    private final Gson gson = new Gson();

    // ── Preflight CORS ────────────────────────────────────────
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse res) {
        setCors(res);
        res.setStatus(HttpServletResponse.SC_OK);
    }

    // ── GET: listar, consultar, eliminar ──────────────────────
    @Override
    protected void doGet(HttpServletRequest solicitud, HttpServletResponse respuesta)
            throws IOException {

        setCors(respuesta);
        respuesta.setContentType("application/json;charset=UTF-8");

        String accion = solicitud.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {

            case "listar":
                List<Medico> lista = medicoDao.consultarTodos();
                respuesta.getWriter().write(gson.toJson(lista));
                break;

            case "consultar":
                int idConsultar = Integer.parseInt(solicitud.getParameter("id"));
                Medico medico = medicoDao.consultarPorId(idConsultar);
                respuesta.getWriter().write(gson.toJson(medico));
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(solicitud.getParameter("id"));
                medicoDao.eliminar(idEliminar);
                respuesta.getWriter().write("{\"exito\":true}");
                break;

            default:
                respuesta.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                respuesta.getWriter().write("{\"error\":\"Accion no reconocida\"}");
        }
    }

    // ── POST: insertar, actualizar ────────────────────────────
    @Override
    protected void doPost(HttpServletRequest solicitud, HttpServletResponse respuesta)
            throws IOException {

        setCors(respuesta);
        respuesta.setContentType("application/json;charset=UTF-8");
        solicitud.setCharacterEncoding("UTF-8");

        // Leer cuerpo JSON de la petición
        StringBuilder sb = new StringBuilder();
        try (BufferedReader lector = solicitud.getReader()) {
            String linea;
            while ((linea = lector.readLine()) != null) sb.append(linea);
        }

        JsonObject cuerpo = gson.fromJson(sb.toString(), JsonObject.class);
        String accion = cuerpo.get("accion").getAsString();

        if ("insertar".equals(accion)) {
            medicoDao.insertar(construirMedico(cuerpo, 0));
            respuesta.getWriter().write("{\"exito\":true}");

        } else if ("actualizar".equals(accion)) {
            int id = cuerpo.get("idMedico").getAsInt();
            medicoDao.actualizar(construirMedico(cuerpo, id));
            respuesta.getWriter().write("{\"exito\":true}");

        } else {
            respuesta.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            respuesta.getWriter().write("{\"error\":\"Accion no reconocida\"}");
        }
    }

    // ── Auxiliares ────────────────────────────────────────────

    private Medico construirMedico(JsonObject j, int id) {
        return new Medico(
            id,
            j.has("nombres")        ? j.get("nombres").getAsString()     : "",
            j.has("apellidos")      ? j.get("apellidos").getAsString()   : "",
            j.has("numRegistro")    ? j.get("numRegistro").getAsString() : "",
            j.has("email")          ? j.get("email").getAsString()       : "",
            j.has("telefono")       ? j.get("telefono").getAsString()    : "",
            j.has("idEspecialidad") ? j.get("idEspecialidad").getAsInt() : 0
        );
    }

    private void setCors(HttpServletResponse res) {
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
