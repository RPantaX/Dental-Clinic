<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bienvenido al Sistema</title>
    <!-- Agrega enlaces a los archivos CSS de Bootstrap -->
 	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
 	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>
</head>
<body> 
    <!-- Barra de navegación con menú y nombre de usuario -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="loginServlet?opcion=ini" style="font-weight: bold;">REGISTROS GENERALES</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Inicio</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="menuPerfil" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Mantenimientos
                    </a>
                    <div class="dropdown-menu" aria-labelledby="menuPerfil">
                    	<a class="dropdown-item" href="PacienteServlet?opcion=lis">Pacientes</a>
                        <a class="dropdown-item" href="ReservaServlet?opcion=lis">Reservas</a>
                        <a class="dropdown-item" href="CitaServlet?opcion=lis">Citas</a>
                        <a class="dropdown-item" href="TrabajadorServlet?opcion=lis">Trabajadores</a>
                    </div>
                </li>
                
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="menuPerfil" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Consultas
                    </a>
                    <div class="dropdown-menu" aria-labelledby="menuPerfil">
                        <a class="dropdown-item" href="#">Consulta 1</a>
                        <a class="dropdown-item" href="#">Consulta 2</a>
                        <a class="dropdown-item" href="#">Consulta 3</a>
                    </div>
                </li>
                
                <li class="nav-item">
                    <a class="nav-link" href="loginServlet?opcion=cer">Cerrar Sesión</a>
                </li>
            </ul>
        </div>
        <!-- Nombre de usuario en la esquina derecha -->
        <span class="navbar-text ml-auto">
            Bienvenido,  <strong>   ${datosTrabajador.nom}   ${datosTrabajador.primerApe}   ${datosTrabajador.segundoApe} </strong>
        </span>
    </nav>
   

</body>
</html>