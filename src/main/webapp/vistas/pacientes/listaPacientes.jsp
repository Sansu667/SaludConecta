<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Pacientes — SaludConecta</title>
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
        th {
            background: #1a73e8; color: white;
            padding: 12px; text-align: left;
        }
        td { padding: 11px 12px; border-bottom: 1px solid #eee; }
        tr:hover td { background: #f5f9ff; }
        .btn-editar {
            background: #f9ab00; color: white; padding: 5px 12px;
            border-radius: 6px; text-decoration: none; margin-right: 5px;
        }
        .btn-eliminar {
            background: #d93025; color: white; padding: 5px 12px;
            border-radius: 6px; text-decoration: none;
        }
        .vacio { text-align: center; color: #888; padding: 30px; }
    </style>
</head>
<body>

    <h1>👥 Listado de Pacientes</h1>

    <a href="${pageContext.request.contextPath}/pacientes?accion=nuevo"
       class="btn-nuevo">+ Nuevo Paciente</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombres</th>
                <th>Apellidos</th>
                <th>Tipo Doc.</th>
                <th>Número Doc.</th>
                <th>Email</th>
                <th>Teléfono</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty listaPacientes}">
                    <tr>
                        <td colspan="8" class="vacio">
                            No hay pacientes registrados.
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="paciente" items="${listaPacientes}">
                        <tr>
                            <td>${paciente.idPaciente}</td>
                            <td>${paciente.nombres}</td>
                            <td>${paciente.apellidos}</td>
                            <td>${paciente.tipoDoc}</td>
                            <td>${paciente.numDoc}</td>
                            <td>${paciente.email}</td>
                            <td>${paciente.telefono}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/pacientes?accion=editar&id=${paciente.idPaciente}"
                                   class="btn-editar">Editar</a>
                                <a href="${pageContext.request.contextPath}/pacientes?accion=eliminar&id=${paciente.idPaciente}"
                                   class="btn-eliminar"
                                   onclick="return confirm('¿Eliminar este paciente?')">
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
