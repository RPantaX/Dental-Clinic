<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrar Pacientes</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
	<%@ include file="../comun/menu.jsp" %>
	<div class="container mt-5">
	    <div class="row">
	        <div class="col-md-10 offset-md-3">   
				    <form id="frmRegistroPaciente" action="PacienteServlet" method="post" >
				        <h2 class="mb-4">Registrar Pacientes</h2>
				        <div class="row">
				            <div class="col-md-6">
				              
				                <br>
				                <div class="form-group">
				                    <label for="txtNombre">Nombre</label>
				                    <input type="text" class="form-control" id="txtNombre" name="txtNombre">
				                </div>
				                 <br>
				                <div class="form-group">
				                    <label for="txtPrimerApe">1° Apellido</label>
				                    <input type="text" class="form-control" id="txtPrimerApe" name="txtPrimerApe" >
				                </div>
				                
				                <br>
				                <div class="form-group">
				                    <label for="txtSegundoApe">2° Apellido</label>
				                    <input type="text" class="form-control" id="txtSegundoApe" name="txtSegundoApe" >
				                </div>
				                <br>
				                <div class="form-group">
				                    <label for="txtEmail">Email</label>
				                    <input type="text" class="form-control" id="txtEmail" name="txtEmail" >
				                </div>
				                
				                <br>

				                <div class="form-group">
				                	<label for="txtCelular">Celular</label>
				                	<input type="number" class="form-control" id="txtCelular" name="txtCelular" >
				                </div>
				                <br>
				                <div class="form-group">
				                	<label for="txtFecha">Fecha de registro</label>
				                	<input type="text" class="form-control" id="txtFecha" name="txtFecha">
				                </div>
				                <br>
				                <div class="form-group"> 
						        	<button type="submit" name="opcion" value="reg" class="btn btn-primary">&nbsp;&nbsp;&nbsp;Registrar&nbsp; &nbsp;&nbsp;</button>
									<a class="btn btn-secondary" href="${pageContext.request.contextPath}/PacienteServlet?opcion=lis"> Regresar a Listar </a>
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
       $("#frmRegistroPaciente").validate({
    	   rules: {
    		    txtNombre: "required",
    		    txtPrimerApe: {
    		        required: true,
    		    },
    		    txtSegundoApe: {
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
    		    txtFecha:{
                	required: true,
                }
    		},
           messages: {
        	   txtNombre: {
                   required: "Ingrese su nombre"
               },
               txtPrimerApe: {
                   required: "Ingrese su primer apellido"
               },
               txtSegundoApe:{
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
               txtFecha:{
               	required: "Ingresela fecha de registro"
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