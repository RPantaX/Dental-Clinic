<%@page import="Modelo.Historial"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado Historial</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/08c2dd9070.js" crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="../comun/menu.jsp" %>
	<header id="main-header" class="p-2 bg-info text-white">
		<div class="container" >
			<div class="row">
				<div class="text-center">
					<h1 class="h1 mb-2 font-weight-normal">Historial del paciente</h1>
				</div>
			</div>
			
		</div>
	</header>
	
	<section id="actions" class="p-4 mb-4 bg-light">
	<div class="container">
			<div class="row d-flex justify-content-center">
				<div class="col-md-3">
					<%
				    	List<Historial> lstHistorial = (List<Historial>) request.getAttribute("lstHistorial");
				
				    // Verificar si la lista no está vacía y tiene al menos una fila
				    	if (lstHistorial != null && !lstHistorial.isEmpty()) {
				        Historial primerHistorial = lstHistorial.get(0);
				        int primerIdHist = primerHistorial.getIdHist();
				        int primerIdPac = primerHistorial.getIdPac();
				        %>
					<a href="${pageContext.request.contextPath}/HistorialServlet?opcion=nue&cod=<%=primerIdPac%>" class="btn btn-primary btn-block"><i class="fas fa-plus"></i> Nuevo Historial </a>
						<%}%>
				</div>
				
			</div>
	</div>
	</section>


	<div class="container">
		
					<table id="table" class="table table-striped">
						<thead class="thead-dark">
							<tr>
								<th>Id Historial</th>
								<th>Id Paciente</th>
								<th>Descripcion</th>
								<th>Fecha</th>
								<th>ID trabajador</th>
								<th>Trabajador</th>
							</tr>
						</thead>
							<tbody>
							<c:forEach items="${lstHistorial}" var="pa">
									<tr>
										<td>${pa.idHist}</td>
										<td>${pa.idPac}</td>
										<td>${pa.desc}</td>
										<td>${pa.fecha}</td>
										<td>${pa.idTrabajador}</td>
										<td>${pa.nombresTrab}</td>
									</tr>
								</c:forEach>
						</tbody>
					</table>
				</div>
		<%	String mensaje = (String) request.getAttribute("mensaje");
			if (mensaje==null) mensaje="";%>
		<%=mensaje%>
	<%@ include file="../comun/footer.jsp"%>			
	<script src="js/jquery.js"></script>
	  <script src="DataTables/datatables.min.js"></script>
	  <script>
	  	$(document).ready(function(){
	  		$('#table').DataTable({
	  			"language": {
	  				"lengthMenu": "Mostrar_MENU_registros",
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