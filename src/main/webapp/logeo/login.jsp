<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <!-- Enlaces a los archivos CSS de Bootstrap -->
    <style type="text/css">
    html{
    height: 100%; /*para que la el back que le pusimos en el body se aplique a todo el screen*/
	}
	body{
	    margin: 0;
	    padding: 0;
	    background: linear-gradient(aqua, rgb(60, 255, 255), rgb(164, 255, 255))
	}
	.login{
	    background-color:rgb(0, 162, 255);
	    width: 400px;
	    padding: 30px;
	    margin: 50px auto;
	    border-radius: 5px;
	}
	fieldset{
	   border: none;
	
	}
	legend{
	    margin: 0 0 30px;
	    padding: 0;
	    font-size: 30px;/*tamaño de la letra*/
	    color: blue;
	    font-family: Georgia, 'Times New Roman', Times, serif;
	    text-align: center;
	}
	label{
	    color: white;
	}
	input{
	    width: 100%;
	    padding: 10px;
	    color: white;
	    background-color: transparent;
	    border: none;
	    border-bottom: 1px solid white;
	    margin-bottom: 30px;
	}
	button{
	    width: 100%;
	    background-color:darkblue;
	    border-radius: 6px;
	    border: none;
	    letter-spacing: 3px;
	    text-transform: uppercase;
	    color: white;
	    padding: 15px;
	    margin-top: 30px;
	    cursor: pointer;
	}
    </style>
</head>
<body>
    <div class="login">
            <form id="loginForm" action="loginServlet" method="post">
                <fieldset>
                <legend>Login</legend>
                <label for="txtUsuario">Usuario</label>
                
                <input type="text" id="txtUsuario" name="txtUsuario">
                <div id="usuarioError" class="text-primary" style="font-weight: bold;"></div>
                <label for="txtContrasena">Contraseña</label>
                
                <input type="password" id="txtContrasena" name="txtContrasena">
                <div id="contrasenaError" class="text-primary" style="font-weight: bold;"></div>
                <button type="submit" name="opcion" value="log"  class="btn btn-primary">Iniciar Sesión</button>
                </fieldset>
            </form>
        </div>       	        
                       
        
     <div class="${mensaje != null ? 'alert alert-danger text-center mt-3' : ''}" style="text-align: center; color:red;">

					    ${mensaje}
	</div>
					
     
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#loginForm').validate({
                rules: {
                	txtUsuario: {
                        required: true
                    },
                    txtContrasena: {
                        required: true
                    }
                },
                messages: {
                	txtUsuario: {
                        required: "Por favor, ingresa el nombre de usuario."
                    },
                    txtContrasena: {
                        required: "Por favor, ingresa la contraseña."
                    }
                },
                errorElement: "span",
                errorPlacement: function(error, element) {
                    if (element.attr("name") === "txtUsuario") {
                        error.appendTo("#usuarioError");
                    } else if (element.attr("name") === "txtContrasena") {
                        error.appendTo("#contrasenaError");
                    }
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