<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<%@ include file="../../parts/meta.jsp" %> 
	
<title>Smart Farm</title>
</head>
<body class="bg-gradient-primary">
	
	<div class="container mt-4">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<img class="mb-2" style="height: 90px;"
											src="img/SF-logo.png" alt="logo"
											class="logo-img">
										<p class="logo-letra">SMART FARM</p>
									</div>
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">Bienvenido/a!</h1>
									</div>
									<%--Bloque que es visible si el elemento error no esta vacio --%>
									<c:if test="${not empty error}">
										<span class="text-danger mx-1">${error}</span>
										<br>
									</c:if>
									<%--Definicion de un form asociado a la accion /validar-login por POST. Se indica ademas que el model attribute se--%>
									<%--debe referenciar con el nombre usuario, spring mapea los elementos de la vista con los atributos de dicho objeto--%>
									<%--para eso debe coincidir el valor del elemento path de cada input con el nombre de un atributo del objeto --%>
									<form:form action="validar-login" method="POST"
										modelAttribute="usuario" class="user">
										<div class="form-group">
											<%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados --%>
											<form:input path="email" id="email" name="email"
												placeholder="Email" type="email" class="form-control form-control-user"
												value="" aria-describedby="emailHelp" />
										</div>
										<div class="form-group">
											<form:input path="password" id="password" name="password"
												placeholder="Contraseña" type="password"
												class="form-control form-control-user" value="" />
										</div>
										<button type="submit" class="btn btn-primary btn-user btn-block text-white"> 
											Iniciar sesión 
										</button>
										<hr>
									</form:form>
									<div class="text-center">
										<a class="small" href="#">¿Olvidó su
											contraseña?</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="js/jquery/jquery.min.js"></script>
	<script src="css/bootstrap/js/bootstrap.bundle.min.js"></script>


	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>
</body>
</html>
