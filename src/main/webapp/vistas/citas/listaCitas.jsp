<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Citas — SaludConecta</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background: #f0f4f8; padding: 20px; }
        h1   { color: #1a73e8; margin-bottom: 20px; }
        .btn-nuevo {
            background: #1a73e8; color: white; padding: 10px 20px;
            border-radius: 8px; text-decoration: none;
            display: inline-block; margin-bottom: 20px;
        }
        table {
            width: 100%; border-collapse: collapse;
            background: white; border-radius: 10px;
            overflow: hidden; box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        th { background: #1a73e8; color: white; padding: 12px; text-align: left; }
        td { padding: 11px 12px; border-bottom: 1px solid #eee; }
        tr:hover td { background: #f5f9ff; }
        .estado-programada { color: #1a73e8; font-weight: bold; }
        .estado-cancelada  { color: #d93025; font-weight: bold; }
        .estado-atendida   { color: #188038; font-weight: bold; }
        .btn-editar   { background: #f9ab00; color: white; padding: 5px 12px; border-radius: 6px; text-decoration: none; margin-right: 5px; }
        .btn-eliminar { background: #d93025; color: white; padding: 5px 12px; border-radius: 6px; text-decoration: none; }
        .vacio { text-align: center; color: #888; padding: 30px; }
    </style>
</head>
<body>

    <h1>📅 Listado de Citas Médicas</h1>

    <a href="${pageContext.request.contextPath}/citas?accion=nuevo"
       class="btn-nuevo">+ Nueva Cita</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Estado</th>
                <th>ID Paciente</th>
                <th>ID Médico</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty listaCitas}">
                    <tr>
                        <td colspan="7" class="vacio">
                            No hay citas registradas.
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="cita" items="${listaCitas}">
                        <tr>
                            <td>${cita.idCita}</td>
                            <td>${cita.fechaCita}</td>
                            <td>${cita.horaCita}</td>
                            <td>
                                <span class="estado-${cita.estado.toLowerCase()}">
                                    ${cita.estado}
                                </span>
                            </td>
                            <td>${cita.idPaciente}</td>
                            <td>${cita.idMedico}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/citas?accion=editar&id=${cita.idCita}"
                                   class="btn-editar">Editar</a>
                                <a href="${pageContext.request.contextPath}/citas?accion=eliminar&id=${cita.idCita}"
                                   class="btn-eliminar"
                                   onclick="return confirm('¿Cancelar esta cita?')">
                                    Eliminar
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>

    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">← Volver al inicio</a>

</body>
</html>
