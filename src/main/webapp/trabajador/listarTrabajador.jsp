<%@page import="Modelo.Trabajador"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="es_PE"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado de Trabajadores</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<link href="DataTables/datatable.min.css" rel="stylecheet" />
<script src="https://kit.fontawesome.com/08c2dd9070.js" crossorigin="anonymous"></script>

</head>

<body>

<%@ include file="../comun/menu.jsp"%>
<header id="main-header" class="p-2 bg-info text-white">
	<div class="container" >
		<div class="row">
			<div class="text-center">
				<h1 class="h1 mb-2 font-weight-normal">Listado de Trabajadores</h1>
			</div>
		</div>
		
	</div>
</header>
<section id="actions" class="p-4 mb-4 bg-light">
	<div class="container">
			<div class="row d-flex justify-content-center">
				<div class="col-md-3">
					<a href="${pageContext.request.contextPath}/TrabajadorServlet?opcion=nue" class="btn btn-primary btn-block"><i class="fas fa-plus"></i> Nuevo trabajador </a>

				</div>
				<div class="col-md-3">
					<div ><a href="${pageContext.request.contextPath}/TrabajadorServlet?opcion=pdf" class="btn btn-danger btn-block">Reporte en pdf</a></div>
				</div>
				
				<div class=>
				</div>
			</div>
	</div>
</section>
	<section id="clientes">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-16">
					<div class="card">
						<table id="table" class="table table-striped">
							<thead class="thead-dark">
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
										<td><fmt:formatNumber value="${tr.sueldo}" type="currency"/></td>
										<td>${tr.username}</td>
										<td>${tr.contrasena}</td>
										<td><a href="${pageContext.request.contextPath}/TrabajadorServlet?opcion=bus&cod=${tr.idTrabajador}" class="btn btn-secondary">
												<i class="fas fa-angle-double-right"></i> Actualizar 
											</a>
										</td>
										<td><a
											href="${pageContext.request.contextPath}/TrabajadorServlet?opcion=eli&cod=${tr.idTrabajador}" class="btn btn-danger">
												<i class="fas fa-angle-double-right"></i> Eliminar </a></td>
									</tr>

								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<%
	String mensaje = (String) request.getAttribute("mensaje");
	if (mensaje == null)
		mensaje = "";
	%>
	<%=mensaje%>
	<%@ include file="../comun/footer.jsp"%>
	<script src="js/jquery.js"></script>
	<script src="DataTables/datatables.min.js"></script>
	<script>
		$(document).ready(function(){
				$('#table').DataTable({
												"language" : {
													"lengthMenu" : "Mostrar_MENU_registros",
													"zeroRecords" : "No se encontraron resultados",
													"info" : "Mostrando registros del _START_  al _END_ de un total de _TOTAL_ registros",
													"infoEmpty" : "Mostrando registros del 0 al 0 de un total de 0 registros",
													"infoFiltered" : "(filtrado de un total de _MAX_ registros)",
													"sSearch" : "Buscar:",
													"oPaginate" : {
														"sFirst" : "Primero",
														"sLast" : "Último",
														"sNext" : "Siguiente",
														"sPrevious" : "Anterior",
													},
													"sProcessing" : "Procesando..."
												},
												"lengthMenu" : [ 20, 15, 10, 5 ]
											});
						});
	</script>
</body>
</html>
