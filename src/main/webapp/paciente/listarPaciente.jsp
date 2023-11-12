<%@page import="Modelo.Paciente"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado de Pacientes</title>
</head>
<body>
	<%@ include file="../comun/menu.jsp" %>
	<div class="container">
		    <br>
		    <h1 class="h1 mb-3 font-weight-normal">Listado de Pacientes</h1>
		    <br>
	</div> 
	<div class="container">
		<div>
			<img src="images/new32.png">
			<a href="${pageContext.request.contextPath}/PacienteServlet?opcion=nue">Nuevo Paciente</a>
		</div>
		<table id="table" class="table table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>1° Apellido</th>
					<th>2° Apellido</th>
					<th>Email</th>
					<th>Celular</th>
					<th>Fecha_registro</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
				<tbody>
				<c:forEach items="${lstPacientes}" var="pa">
						<tr>
							<td>${pa.idPac}</td>
							<td>${pa.nombrePac}</td>
							<td>${pa.primerApePac}</td>
							<td>${pa.segundoApePac}</td>
							<td>${pa.emailPac}</td>
							<td>${pa.celularPac}</td>
							<td>${pa.fechaRegPac}</td>
							<td><img src="images/edit32.png"> <a href="${pageContext.request.contextPath}/PacienteServlet?opcion=bus&cod=${pa.idPac}"> Actualizar </a></td>
			      			<td> <img src="images/delete32.png"> <a href="${pageContext.request.contextPath}/PacienteServlet?opcion=eli&cod=${pa.idPac}"> Eliminar </a></td>
							
						</tr>
					</c:forEach>
				</tbody>
		</table>
	</div>
		<%	String mensaje = (String) request.getAttribute("mensaje");
			if (mensaje==null) mensaje="";
		
		%>
			<%=mensaje%>
			
	<script src="js/jquery.js"></script>
	  <script src="DataTables/datatables.min.js"></script>
	  <script>
	  	$(document).ready(function(){
	  		$('#table').DataTable({
	  			"language": {
	  				"lengthMenu": "Mostrar_MENU_registros",
	  				"zeroRecords": "No se encontraron resultados",
	  				"info": "Mostrando registros del _START_  al_END_ de un total de _TOTAL_registros",
	  				"infoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
	  				"infoFiltered": "(filtrado de un total de _MAX_registros)",
	  				"sSearch":"Buscar:",
	  				"oPaginate":{
	  					"sFirst":"Primero",
	  					"sLast":"Último",
	  					"sNext":"Siguiente",
	  					"sPrevious":"Anterior",
	  				},
	  				"sProcessing":"Procesando..."
	  			},
	  			"lengthMenu":[5,10,15,20]
	  		});
	  	});
	  </script>
</body>
</html>