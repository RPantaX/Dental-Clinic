<%@page import="Modelo.Cita"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Citas</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	 <link href="DataTables/datatable.min.css" rel="stylecheet"/>
</head>
<body>
	<%@ include file="../comun/menu.jsp" %>
	<div class="container">
		    <br>
		    <h1 class="h1 mb-3 font-weight-normal">Listado de Citas</h1>
		    <br>
	</div> 
	<div class="container">	 	
		
		<table id="table" class= "table table-hover">
		 <thead>
			<tr>
				<th>Id Cita</th>
				<th>Id Registro</th>
				<th>Fecha</th>
				<th>Hora</th>
				<th>Paciente</th>
				<th>Trabajador</th>
				<th>Puesto</th>
				<th>Sala</th>
				<th></th>
				<th></th>
			</tr>
		 </thead>
	      <tbody>
	    <c:forEach items="${lstCita}" var="ct">
	     	 <tr>
			     	<td>${ct.idCita}</td>
			      	<td>${ct.idReserva}</td>
			      	<td>${ct.fecha}</td>
			      	<td>${ct.hora}</td>
			      	<td>${ct.nombresPaciente}</td>
			      	<td>${ct.nombresTrabajador}</td>
			      	<td>${ct.puesto}</td>
			      	<td>${ct.sala}</td>
			      	<td><img src="images/edit32.png"> <a href="${pageContext.request.contextPath}/CitaServlet?opcion=bus&cod=${ct.idCita}"> Actualizar </a></td>
			      	<td> <img src="images/delete32.png"><a href="${pageContext.request.contextPath}/CitaServlet?opcion=eli&cod=${ct.idCita}"> Eliminar </a></td>
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