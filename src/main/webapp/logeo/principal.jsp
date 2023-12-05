<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bienvenido al Sistema</title> 
</head>
<body> 
  
 <%@ include file="../comun/menu.jsp" %>
   
  
 <div style="position: absolute;top: 50%;left: 50%;transform: translate(-50%, -50%);"> 
    <img src="${pageContext.request.contextPath}/images/Bienvenido.jpg" width="90%">
 </div>
  <div class="${mensajeCook != null ? 'alert alert-danger text-center mt-3' : ''}" style="text-align: center; color:red;">
					    ${mensajeCook}
	</div>


</body>
</html>