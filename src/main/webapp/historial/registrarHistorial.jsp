<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrar Historial</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
	<%@ include file="../comun/menu.jsp" %>
	<div class="container mt-5">
	    <div class="row">
	        <div class="col-md-10 offset-md-3">   
				    <form id="frmRegistroHsitorial" action="HistorialServlet" method="post" >
				        <h2 class="mb-4">Registrar Nuevo Historial</h2>
				        <div class="row">
				            <div class="col-md-6">
				              
				                <div class="form-group">
				                    <label for="txtDesc">Descripción</label>
				                    <input type="text" class="form-control" id="txtDesc" name="txtDesc">
				                </div>
				                 <br>
				                <div class="form-group">
				                    <label for="txtIdPac">Id Trabajador</label>
				                    <input type="number" class="form-control" id="txtIdPac" name="txtIdPac" >
				                </div>
				                <br>

				                <br>
				                <div class="form-group"> 
						        	<button type="submit" name="opcion" value="reg" class="btn btn-primary">&nbsp;&nbsp;&nbsp;Registrar&nbsp; &nbsp;&nbsp;</button>
									<a class="btn btn-secondary" href="${pageContext.request.contextPath}/HistorialServlet?opcion=lis"> Regresar a Listar </a>
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
       $("#frmRegistroHsitorial").validate({
    	   rules: {
    		   	txtDesc: "required",
    		   	txtIdPac: {
    		        required: true,
    		        numericOnly: true 
    		    }
    		},
           messages: {
        	   txtDesc: {
                   required: "Ingrese la descripción del historial"
               },
               txtIdPac:{
               	required: "Ingrese solo números"
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