<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" >
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    
    <link rel="stylesheet" href="css/login.css">
	<link href="https://fonts.googleapis.com/css?family=Karla:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Muli:wght@700&display=swap" rel="stylesheet">
    <title>Smart Farm</title>
</head>
<body>
    <main class="d-flex align-items-center min-vh-100 py-3 py-md-0">
        <div class="container">
          <div class="card login-card">
            <div class="row">
              <div class="col-md-5">
                <img class="login-img" src="https://images.unsplash.com/photo-1564085352725-08da0272627d">
              </div>
              <div class="col-md-7">
                <div class="card-body text-center">
                  <div class="logo">
                    <img src="https://i.ibb.co/BqwTJW7/SF-logo.png" alt="logo" class="logo-img">
                    <p class="logo-letra">SMART FARM</p>
                  </div>
                  <p class="login-card-description">¡Bienvenida/o!</p>
                  
                  <%--Bloque que es visible si el elemento error no esta vacio --%>
					<c:if test="${not empty error}">
				        <span style="color: red; float:left; margin-bottom: 5px;">${error}</span>
				        <br>
			        </c:if>	
			        <%--Definicion de un form asociado a la accion /validar-login por POST. Se indica ademas que el model attribute se--%>
					<%--debe referenciar con el nombre usuario, spring mapea los elementos de la vista con los atributos de dicho objeto--%>
					<%--para eso debe coincidir el valor del elemento path de cada input con el nombre de un atributo del objeto --%>
                  	<form:form action="validar-login" method="POST" modelAttribute="usuario">
                      <div class="form-group">
                      <%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados --%>
                        <form:input path="email" id="email" type="email" class="form-control" placeholder="Email"/>
                      </div>
                      <div class="form-group mb-4">
                        <form:input path="password" type="password" id="password" class="form-control" placeholder="Contraseña"/>
                      </div>
                      <button class="btn btn-block login-btn mb-4" name="login" id="login" type="submit">Iniciar sesión</button>
                    </form:form>
                    
                    <a href="#!" class="link-contrasena">¿Olvidó su contraseña?</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>
    
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
	<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
