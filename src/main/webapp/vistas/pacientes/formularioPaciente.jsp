<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Nuevo Paciente — SaludConecta</title>
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
        h2 { color: #1a73e8; margin-bottom: 24px; }
        .campo { margin-bottom: 16px; }
        label {
            display: block; font-weight: 600;
            margin-bottom: 5px; color: #444;
        }
        input, select {
            width: 100%; padding: 10px;
            border: 1px solid #ccc; border-radius: 8px;
            font-size: 0.95rem;
        }
        input:focus, select:focus {
            outline: none; border-color: #1a73e8;
            box-shadow: 0 0 0 2px rgba(26,115,232,0.2);
        }
        .btn-guardar {
            background: #1a73e8; color: white; border: none;
            padding: 12px 28px; border-radius: 8px;
            font-size: 1rem; cursor: pointer; width: 100%;
            margin-top: 8px;
        }
        .btn-guardar:hover { background: #1558b0; }
        .enlace-volver { display: block; text-align: center; margin-top: 14px; }
    </style>
</head>
<body>

<div class="formulario-contenedor">
    <h2>➕ Registrar Nuevo Paciente</h2>

    <%-- Formulario enviado por POST al servlet PacienteServlet --%>
    <form action="${pageContext.request.contextPath}/pacientes"
          method="post">

        <input type="hidden" name="accion" value="insertar">

        <div class="campo">
            <label for="nombres">Nombres</label>
            <input type="text" id="nombres" name="nombres"
                   placeholder="Ej: Laura Sofía" required>
        </div>

        <div class="campo">
            <label for="apellidos">Apellidos</label>
            <input type="text" id="apellidos" name="apellidos"
                   placeholder="Ej: Ramírez Torres" required>
        </div>

        <div class="campo">
            <label for="tipoDoc">Tipo de documento</label>
            <select id="tipoDoc" name="tipoDoc" required>
                <option value="">-- Seleccione --</option>
                <option value="CC">Cédula de Ciudadanía</option>
                <option value="TI">Tarjeta de Identidad</option>
                <option value="CE">Cédula de Extranjería</option>
                <option value="PP">Pasaporte</option>
            </select>
        </div>

        <div class="campo">
            <label for="numDoc">Número de documento</label>
            <input type="text" id="numDoc" name="numDoc"
                   placeholder="Ej: 1020304050" required>
        </div>

        <div class="campo">
            <label for="fechaNac">Fecha de nacimiento</label>
            <input type="date" id="fechaNac" name="fechaNac" required>
        </div>

        <div class="campo">
            <label for="email">Correo electrónico</label>
            <input type="email" id="email" name="email"
                   placeholder="correo@ejemplo.com" required>
        </div>

        <div class="campo">
            <label for="telefono">Teléfono</label>
            <input type="tel" id="telefono" name="telefono"
                   placeholder="Ej: 3001234567">
        </div>

        <div class="campo">
            <label for="direccion">Dirección</label>
            <input type="text" id="direccion" name="direccion"
                   placeholder="Ej: Calle 45 # 12-30, Bogotá">
        </div>

        <button type="submit" class="btn-guardar">Guardar Paciente</button>
    </form>

    <a href="${pageContext.request.contextPath}/pacientes?accion=listar"
       class="enlace-volver">← Volver al listado</a>
</div>

</body>
</html>
