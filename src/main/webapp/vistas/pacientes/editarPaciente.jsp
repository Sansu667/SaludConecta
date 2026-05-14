<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Paciente — SaludConecta</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: #f0f4f8; padding: 30px;
        }
        .formulario-contenedor {
            background: white; max-width: 600px; margin: auto;
            padding: 30px; border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        h2 { color: #f9ab00; margin-bottom: 24px; }
        .campo { margin-bottom: 16px; }
        label {
            display: block; font-weight: 600;
            margin-bottom: 5px; color: #444;
        }
        input, select {
            width: 100%; padding: 10px;
            border: 1px solid #ccc; border-radius: 8px;
        }
        input:focus { outline: none; border-color: #f9ab00; }
        .btn-actualizar {
            background: #f9ab00; color: white; border: none;
            padding: 12px 28px; border-radius: 8px;
            font-size: 1rem; cursor: pointer; width: 100%;
        }
        .btn-actualizar:hover { background: #d4900c; }
        .enlace-volver { display: block; text-align: center; margin-top: 14px; }
    </style>
</head>
<body>

<div class="formulario-contenedor">
    <h2>✏️ Editar Paciente</h2>

    <%-- Formulario de actualización enviado por POST --%>
    <form action="${pageContext.request.contextPath}/pacientes"
          method="post">

        <input type="hidden" name="accion" value="actualizar">
        <input type="hidden" name="idPaciente" value="${paciente.idPaciente}">

        <div class="campo">
            <label for="nombres">Nombres</label>
            <input type="text" id="nombres" name="nombres"
                   value="${paciente.nombres}" required>
        </div>

        <div class="campo">
            <label for="apellidos">Apellidos</label>
            <input type="text" id="apellidos" name="apellidos"
                   value="${paciente.apellidos}" required>
        </div>

        <div class="campo">
            <label for="tipoDoc">Tipo de documento</label>
            <select id="tipoDoc" name="tipoDoc" required>
                <option value="CC"
                    <c:if test="${paciente.tipoDoc == 'CC'}">selected</c:if>>
                    Cédula de Ciudadanía
                </option>
                <option value="TI"
                    <c:if test="${paciente.tipoDoc == 'TI'}">selected</c:if>>
                    Tarjeta de Identidad
                </option>
                <option value="CE"
                    <c:if test="${paciente.tipoDoc == 'CE'}">selected</c:if>>
                    Cédula de Extranjería
                </option>
                <option value="PP"
                    <c:if test="${paciente.tipoDoc == 'PP'}">selected</c:if>>
                    Pasaporte
                </option>
            </select>
        </div>

        <div class="campo">
            <label for="numDoc">Número de documento</label>
            <input type="text" id="numDoc" name="numDoc"
                   value="${paciente.numDoc}" required>
        </div>

        <div class="campo">
            <label for="fechaNac">Fecha de nacimiento</label>
            <input type="date" id="fechaNac" name="fechaNac"
                   value="${paciente.fechaNac}" required>
        </div>

        <div class="campo">
            <label for="email">Correo electrónico</label>
            <input type="email" id="email" name="email"
                   value="${paciente.email}" required>
        </div>

        <div class="campo">
            <label for="telefono">Teléfono</label>
            <input type="tel" id="telefono" name="telefono"
                   value="${paciente.telefono}">
        </div>

        <div class="campo">
            <label for="direccion">Dirección</label>
            <input type="text" id="direccion" name="direccion"
                   value="${paciente.direccion}">
        </div>

        <button type="submit" class="btn-actualizar">Actualizar Paciente</button>
    </form>

    <a href="${pageContext.request.contextPath}/pacientes?accion=listar"
       class="enlace-volver">← Volver al listado</a>
</div>

</body>
</html>
