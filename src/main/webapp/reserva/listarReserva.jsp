<%@page import="Modelo.Reserva"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="es_PE"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado de Reserva</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link href="DataTables/datatable.min.css" rel="stylecheet"/>
<script src="https://kit.fontawesome.com/08c2dd9070.js" crossorigin="anonymous"></script>
</head>

<body>
<%@ include file="../comun/menu.jsp" %>
<header id="main-header" class="p-2 bg-info text-white">
	<div class="container" >
		<div class="row">
			<div class="text-center">
				<h1 class="h1 mb-2 font-weight-normal">Listado de Reservas</h1>
			</div>
		</div>
		
	</div>
</header>
<section id="actions" class="p-4 mb-4 bg-light">
	<div class="container">
			<div class="row d-flex justify-content-center">
				<div class="col-md-3">
					<a href="${pageContext.request.contextPath}/ReservaServlet?opcion=nue" class="btn btn-primary btn-block"><i class="fas fa-plus"></i> Nueva Reserva </a>
				</div>

				<div class="col-md-3">
					<div ><a href="${pageContext.request.contextPath}/ReservaServlet?opcion=pdf" class="btn btn-danger btn-block">Reporte en pdf</a></div>
				</div>

			</div>
	</div>
</section>
	
	<div class="container">

		<table id="table" class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th>Id</th>
					<th>Id_Paciente</th>
					<th>Fecha</th>
					<th>Hora</th>
					<th>Estado</th>
					<th>Precio</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
				<tbody>
				<c:forEach items="${lstReservas}" var="usu">
						<tr>
							<td>${usu.id_reserva}</td>
							<td>${usu.id_paciente}</td>
							<td>${usu.fecha}</td>
							<td>${usu.hora}</td>
							<td>${usu.estado.des_estado}</td>
							<td><fmt:formatNumber value="${usu.precio}" type="currency"/></td>
							<td><img src="images/edit32.png"> <a href="${pageContext.request.contextPath}/ReservaServlet?opcion=bus&cod=${usu.id_reserva}" class="btn btn-secondary"> <i class="fas fa-angle-double-right"></i> Actualizar </a></td>
			      			<td> <img src="images/delete32.png"> <a href="${pageContext.request.contextPath}/ReservaServlet?opcion=eli&cod=${usu.id_reserva}" class="btn btn-danger"> <i class="fas fa-angle-double-right"></i> Eliminar </a></td>
							
						</tr>
					</c:forEach>
				</tbody>
		</table>
	</div>
		<%	String mensaje = (String) request.getAttribute("mensaje");
			if (mensaje==null) mensaje="";
		
		%>
			<%=mensaje%>
	<%@ include file="../comun/footer.jsp"%>		
	<script src="js/jquery.js"></script>
	  <script src="DataTables/datatables.min.js"></script>
	  <script>
	  	$(document).ready(function(){
	  		$('#table').DataTable({
	  			"language": {
	  				"lengthMenu": "Mostrar _MENU_ registros",
	  				"zeroRecords": "No se encontraron resultados",
	  				"info": "Mostrando registros del _START_  al _END_ de un total de _TOTAL_ registros",
	  				"infoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
	  				"infoFiltered": "(filtrado de un total de _MAX_ registros)",
	  				"sSearch":"Buscar:",
	  				"oPaginate":{
	  					"sFirst":"Primero",
	  					"sLast":"Último",
	  					"sNext":"Siguiente",
	  					"sPrevious":"Anterior",
	  				},
	  				"sProcessing":"Procesando..."
	  			},
	  			"lengthMenu":[20,15,10,5]
	  		});
	  	});
	  </script>
</body>
</html>