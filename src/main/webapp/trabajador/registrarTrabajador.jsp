<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Registrar Trabajador</title>
 	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
<%@ include file="../comun/menu.jsp" %>
	<div class="container mt-5">
	    <div class="row">
	        <div class="col-md-10 offset-md-3">   
				    <form id="frmRegistroTrabajador" action="TrabajadorServlet" method="post" >
				        <h2 class="mb-4">Registrar Trabajador</h2>
				        <div class="row">
				            <div class="col-md-6">
				              
				                <br>
				                <div class="form-group">
				                    <label for="txtNombres">Nombre</label>
				                    <input type="text" class="form-control" id="txtNombres" name="txtNombres" required>
				                </div>
				                 <br>
				                <div class="form-group">
				                    <label for="txtPrimerApellido">1° Apellido</label>
				                    <input type="text" class="form-control" id="txtPrimerApellido" name="txtPrimerApellido" required>
				                </div>
				                 <br>
				                <div class="form-group">
				                    <label for="txtSegundoApellido">2° Apellido</label>
				                    <input type="text" class="form-control" id="txtSegundoApellido" name="txtSegundoApellido" required>
				                </div>
				                <br>
				                 
				                <div class="form-group">
				                    <label for="txtFechaNac">Fecha de Nacimiento</label>
				                    <input type="text" class="form-control" id="txtFechaNac" name="txtFechaNac"  required>
				                </div>
				                <br>
				                <div class="form-group">
				                    <label for="cboTipoPuesto">Puesto</label>
				                    <select class="form-control" id="cboTipoPuesto" name="cboTipoPuesto">
				                        <option value="1" >Odontólogo(a) Estético</option>
				                        <option value="2" >Odontólogo(a) Preventiva</option>
				                        <option value="3" >Odontólogo(a) Infantil</option>
				                        <option value="4" >Recepcionista</option>
				                    </select>
				                </div>
				                
				                <br>
				                <div class="form-group">
				                    <label for="txtEmail">Email</label>
				                    <input type="text" class="form-control" id="txtEmail" name="txtEmail" required>
				                </div>
				                
				          		<br>
				                <div class="form-group">
				                    <label for="txtCelular">Celular</label>
				                    <input type="number" class="form-control" id="txtCelular" name="txtCelular" required>
				                </div>
				                <br>
				                <div class="form-group">
				                	<label for="txtFechaIngrego">Fecha de Ingreso</label>
				                	<input type="text" class="form-control" id="txtFechaIngreso" name="txtFechaIngreso" required>
				                </div>
				                <br>
				                <div class="form-group">
				                	<label for="txtSueldo">Sueldo</label>
				                	<input type="number" class="form-control" id="txtSueldo" name="txtSueldo" required>
				                </div>
				                <br>
				                <div class="form-group">
				                	<label for="txtUsuario">Nombre de usuario</label>
				                	<input type="text" class="form-control" id="txtUsuario" name="txtUsuario" required>
				                </div>
				                <br>
				                <div class="form-group">
				                	<label for="txtContrasena">Contraseña</label>
				                	<input type="password" class="form-control" id="txtContrasena" name="txtContrasena" required>
				                </div>
				                 
				                 <br>
				                 
				                <div class="form-group"> 
						        	<button type="submit" name="opcion" value="reg" class="btn btn-primary">&nbsp;&nbsp;&nbsp;Registrar&nbsp; &nbsp;&nbsp;</button>
									<a class="btn btn-secondary" href="${pageContext.request.contextPath}/TrabajadorServlet?opcion=lis"> Regresar a Listar </a>
						        </div>	
						        
						     	   <%    String mensaje = (String)request.getAttribute("mensaje");
				                    	if (mensaje==null) mensaje="";	
								   %>  
									 
								   <%=mensaje%>   	  
				            </div>
				        </div>		        		        
				    </form>
	        </div>
	    </div>
	</div>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
    	
   	 $.validator.addMethod("numericOnly", function(value, element) {
   	        return this.optional(element) || /^[0-9]+$/.test(value);
   	    }, "Ingrese solo números.");
   	 
   	 $.validator.addMethod("decimalOnly", function(value, element) {
         return this.optional(element) || /^\d+(\.\d{1,2})?$/.test(value);
     }, "Ingrese solo números decimales.");
       $("#frmRegistroTrabajador").validate({
    	   rules: {
    		    txtNombres: "required",
    		    txtPrimerApellido: {
    		        required: true,
    		    },
    		    txtSegundoApellido: {
    		        required: true,
    		    },
    		    txtFechaNac:{
                	required: true,
                },
                txtEmail: {
                	required: true,
                	email: true
                },
    		    txtCelular: {
    		        required: true,
    		        numericOnly: true 
    		    },
    		    txtFechaIngrego:{
                	required: true,
                },
    		    txtSueldo: {
    		        required: true,
    		        decimalOnly: true 
    		    },
    		    txtUsuario: {
                    required: true,
                    minlength: 3
                },
    		    txtContrasena: {
                    required: true,
                    minlength: 8
                }
    		},
           messages: {
        	   txtNombres: {
                   required: "Ingrese su nombre"
               },
               txtPrimerApellido: {
                   required: "Ingrese su primer apellido"
               },
               txtSegundoApellido:{
                   required: "Ingrese su segundo apellido"
               },
               txtFechaNac:{
                   required: "Ingrese su fecha de nacimiento"
               },
               txtEmail: {
                   required: "Ingrese su correo",
                   email: "Ingrese un correo válido"
               },
               txtCelular:{
               	required: "Ingrese un número de celular"
               },
               txtFechaIngrego:{
               	required: "Ingresela fecha de ingreso"
               },
               txtSueldo: {
                   required: "Ingrese un sueldo"
               },
               txtUsuario:{
               	required: "Ingrese un nombre de usuario"
               },
               txtContrasena: {
                   required: "Ingrese su contraseña",
                   minlength: "Tu contraseña debe tener al menos 8 caracteres"
               },
               
           },
           errorElement: "span",
           errorPlacement: function(error, element) {
               error.addClass("invalid-feedback");
               element.closest(".form-group").append(error);
           },
           highlight: function(element, errorClass, validClass) {
               $(element).addClass("is-invalid").removeClass("is-valid");
           },
           unhighlight: function(element, errorClass, validClass) {
               $(element).removeClass("is-invalid").addClass("is-valid");
           },
           submitHandler: function(form) {
               form.submit();
           }
       });
   });
    
    
    </script>
</body>
</html>