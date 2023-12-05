<%@page import="Modelo.Trabajador"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="application/vnd.ms-excel" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String nombreArchivo="reporte.xls";
	response.setHeader("Content-Disposition", "attachment;filename="+nombreArchivo);

%>
<!DOCTYPE html>
<html>
<head>
<title>Listado de Trabajadores</title>
	
</head>

<body>
	
	<div class="container">
		    <br>
		    <h1 class="h1 mb-3 font-weight-normal">Listado de Trabajadores</h1>
		    <br>
	</div> 
	<div class="container">	 	 
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
			</tr>
			
		 	</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>