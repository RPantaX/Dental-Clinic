<%@page import="Modelo.Trabajador"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado de Trabajadores</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	 <link href="DataTables/datatable.min.css" rel="stylecheet"/>
</head>

<body>
	<%@ include file="../comun/menu.jsp" %>
	<div class="container">
		    <br>
		    <h1 class="h1 mb-3 font-weight-normal">Listado de Trabajadores</h1>
		    <br>
	</div> 
	<div class="container">	 	
		
		 <div>
		        <img src="images/new32.png">
		        <a href="${pageContext.request.contextPath}/TrabajadorServlet?opcion=nue"> Nuevo trabajador </a>
		 </div> 
		 
		<table id="table" class= "table table-hover">
		 <thead>
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>1° Apellido</th>
				<th>2° Apellido</th>
				<th>Fecha de Nacimiento</th>
				<th>Puesto</th>
				<th>Email</th>
				<th>Celular</th>
				<th>Fecha de ingreso</th>
				<th>Sueldo</th>
				<th>Usuario</th>
				<th>Contraseña</th>
				<th></th>
				<th></th>
			</tr>
		 </thead>
	      <tbody>
	    <c:forEach items="${lstTrabajadores}" var="tr">
	      
	     	 <tr>
			     	<td>${tr.idTrabajador}</td>
			      	<td>${tr.nom}</td>
			      	<td>${tr.primerApe}</td>
			      	<td>${tr.segundoApe}</td>
			      	<td>${tr.fechaNac}</td>
			      	<td>${tr.tipoPuesto.desPuesto}</td>
			      	<td>${tr.email}</td>
			      	<td>${tr.celular}</td>
			      	<td>${tr.fechaIngreso}</td>
			      	<td>${tr.sueldo}</td>
			      	<td>${tr.username}</td>
			      	<td>${tr.contrasena}</td>
			      	<td><img src="images/edit32.png"> <a href="${pageContext.request.contextPath}/TrabajadorServlet?opcion=bus&cod=${tr.idTrabajador}"> Actualizar </a></td>
			      	<td> <img src="images/delete32.png"><a href="${pageContext.request.contextPath}/TrabajadorServlet?opcion=eli&cod=${tr.idTrabajador}"> Eliminar </a></td>
			</tr>
			
		 	</c:forEach>
			</tbody>
		</table>
	</div>
	
		
	  <%    String mensaje = (String)request.getAttribute("mensaje");
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
