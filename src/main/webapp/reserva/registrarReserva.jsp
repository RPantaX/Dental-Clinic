<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Registrar Reservas</title>
 	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
<%@ include file="../comun/menu.jsp" %>
	<div class="container mt-5">
	    <div class="row">
	        <div class="col-md-10 offset-md-3">   
				    <form id="frmRegistroReserva" action="ReservaServlet" method="post" >
				        <h2 class="mb-4">Registrar Reservas</h2>
				        <div class="row">
				            <div class="col-md-6">
				              
				                <br>
				                <div class="form-group">
				                    <label for="txtIdPaciente">Id Paciente</label>
				                    <input type="number" class="form-control" id="txtIdPaciente" name="txtIdPaciente">
				                </div>
				                 <br>
				                <div class="form-group">
				                    <label for="txtFecha">Fecha</label>
				                    <input type="text" class="form-control" id="txtFecha" name="txtFecha">
				                </div>
				                 <br>
				                <div class="form-group">
				                    <label for="txtHora">Hora</label>
				                    <input type="text" class="form-control" id="txtHora" name="txtHora">
				                </div>
				                
				                <br>
				                <div class="form-group">
				                    <label for="cboEstado">Estado</label>
				                    <select class="form-control" id="cboEstado" name="cboEstado">
				                        <option value="1" >Activo</option>
				                        <option value="2" >Anulado</option>
				                    </select>
				                </div>       
				                <br>
				                <div class="form-group">
				                	<label for="txtPrecio">Precio</label>
				                	<input type="number" class="form-control" id="txtPrecio" name="txtPrecio">
				                </div>
				                <br>
				                <div class="form-group"> 
						        	<button type="submit" name="opcion" value="reg" class="btn btn-primary">&nbsp;&nbsp;&nbsp;Registrar&nbsp; &nbsp;&nbsp;</button>
									<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ReservaServlet?opcion=lis"> Regresar a Listar </a>
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
       $("#frmRegistroReserva").validate({
    	   rules: {
    		   txtIdPaciente:{
    			   required: true,
    			   numeriOnly: true
    		   },
    		   txtFecha:{
    			   required: true,
    		   },
    		   txtHora:{
    			   required: true,
    		   },
    		   txtPrecio:{
    			   required: true,
    			   decimalOnly: true
    		   }  
    		},
           messages: {
        	   txtIdPaciente: {
                   required: "Ingrese el Id",
                   numeriOnly: "Ingresa solo valores enteros"
               },
               txtFecha:{
            	   required:"Ingrese la fecha de reserva",
               },
               txtHora:{
            	   required:"Ingrese la hora de reserva",
               },
               txtPrecio: {
                   required: "Ingrese un precio",
                   decimalOnly: "Ingrese solo números decimales"
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