<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Cita — SaludConecta</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background: #f0f4f8; padding: 30px; }
        .formulario-contenedor {
            background: white; max-width: 550px; margin: auto;
            padding: 30px; border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        h2  { color: #f9ab00; margin-bottom: 24px; }
        .campo { margin-bottom: 16px; }
        label { display: block; font-weight: 600; margin-bottom: 5px; color: #444; }
        input, select {
            width: 100%; padding: 10px;
            border: 1px solid #ccc; border-radius: 8px;
        }
        .btn-actualizar {
            background: #f9ab00; color: white; border: none;
            padding: 12px 28px; border-radius: 8px;
            font-size: 1rem; cursor: pointer; width: 100%;
        }
        .enlace-volver { display: block; text-align: center; margin-top: 14px; }
    </style>
</head>
<body>

<div class="formulario-contenedor">
    <h2>✏️ Editar Cita Médica</h2>

    <form action="${pageContext.request.contextPath}/citas" method="post">

        <input type="hidden" name="accion"  value="actualizar">
        <input type="hidden" name="idCita"  value="${cita.idCita}">

        <div class="campo">
            <label for="fechaCita">Fecha de la cita</label>
            <input type="date" id="fechaCita" name="fechaCita"
                   value="${cita.fechaCita}" required>
        </div>

        <div class="campo">
            <label for="horaCita">Hora de la cita</label>
            <input type="time" id="horaCita" name="horaCita"
                   value="${cita.horaCita}" required>
        </div>

        <div class="campo">
            <label for="estado">Estado</label>
            <select id="estado" name="estado" required>
                <option value="Programada"
                    <c:if test="${cita.estado == 'Programada'}">selected</c:if>>
                    Programada
                </option>
                <option value="Cancelada"
                    <c:if test="${cita.estado == 'Cancelada'}">selected</c:if>>
                    Cancelada
                </option>
                <option value="Atendida"
                    <c:if test="${cita.estado == 'Atendida'}">selected</c:if>>
                    Atendida
                </option>
            </select>
        </div>

        <div class="campo">
            <label for="idPaciente">ID del Paciente</label>
            <input type="number" id="idPaciente" name="idPaciente"
                   value="${cita.idPaciente}" required>
        </div>

        <div class="campo">
            <label for="idMedico">ID del Médico</label>
            <input type="number" id="idMedico" name="idMedico"
                   value="${cita.idMedico}" required>
        </div>

        <button type="submit" class="btn-actualizar">Actualizar Cita</button>
    </form>

    <a href="${pageContext.request.contextPath}/citas?accion=listar"
       class="enlace-volver">← Volver al listado</a>
</div>

</body>
</html>
