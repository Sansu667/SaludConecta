<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SaludConecta — Inicio</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body {
            font-family: 'Segoe UI', sans-serif;
            background: #f0f4f8;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }
        .encabezado {
            background: #1a73e8;
            color: white;
            width: 100%;
            text-align: center;
            padding: 30px;
            font-size: 2rem;
            font-weight: bold;
        }
        .subtitulo {
            color: #555;
            margin: 20px 0;
            font-size: 1.1rem;
        }
        .contenedor-tarjetas {
            display: flex;
            gap: 30px;
            margin-top: 30px;
            flex-wrap: wrap;
            justify-content: center;
        }
        .tarjeta {
            background: white;
            border-radius: 12px;
            padding: 30px 40px;
            text-align: center;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            text-decoration: none;
            color: #1a73e8;
            font-size: 1.1rem;
            font-weight: 600;
            transition: transform 0.2s;
        }
        .tarjeta:hover { transform: translateY(-5px); }
        .tarjeta span { display: block; font-size: 2.5rem; margin-bottom: 10px; }
    </style>
</head>
<body>

    <div class="encabezado">🏥 SaludConecta</div>
    <p class="subtitulo">Sistema de gestión de citas médicas en línea</p>

    <div class="contenedor-tarjetas">
        <a href="${pageContext.request.contextPath}/pacientes?accion=listar"
           class="tarjeta">
            <span>👥</span>Gestión de Pacientes
        </a>
        <a href="${pageContext.request.contextPath}/citas?accion=listar"
           class="tarjeta">
            <span>📅</span>Gestión de Citas
        </a>
    </div>

</body>
</html>
