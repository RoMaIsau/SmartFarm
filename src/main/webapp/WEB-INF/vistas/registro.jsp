<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" >
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    
    <link rel="stylesheet" href="css/registro.css">
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet">
    <title>Registro - Smart Farm</title>
<body>
	<div class="container contenido mt-5 p-3 shadow p-3 bg-white rounded">
        <form:form method="POST" modelAttribute="usuario" action="validar-registro">
            <h3 class="text-center text-secondary">Registrar usuario/a</h3>
            <div class="container cajas">
            <%--Bloque que es visible si el elemento error no esta vacio --%>
				<c:if test="${not empty error}">
			        <span style="color: red; float:left;">${error}</span>
			        <br>
		        </c:if>
		        <c:if test="${not empty mensaje}">
			        <span style="color: green; float:left;">${mensaje}</span>
			        <br>
		        </c:if>	
                <div class="form-group mt-2">
                    <div class="inner-addon left-addon">
                        <i class="fas fa-user"></i>
                        <form:input path="email" type="email" id="email" name="email" class="form-control" placeholder="Email" required = "true" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="inner-addon left-addon">
                        <i class="fas fa-lock"></i>
                        <form:input path="password" type="password" id="password" name="password" class="form-control" placeholder="Contraseña" required = "true" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="inner-addon left-addon">
                        <i class="fas fa-lock"></i>
                        <input type="password" id="password2" name="password2" class="form-control" placeholder="Repetir contraseña" required = "true" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="inner-addon left-addon">
                        <i class="fas fa-user-tag"></i>
                        <form:input path="rol" type="text" id="rol" name="rol" class="form-control" placeholder="Rol" required = "true" />
                    </div>
                </div>
                <button type="submit" class="btn boton">Registrar</button>
            </div>
        </form:form>  
    </div>
   
    
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
	<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="https://kit.fontawesome.com/8e42460bc2.js" crossorigin="anonymous"></script>
</body>
</html>