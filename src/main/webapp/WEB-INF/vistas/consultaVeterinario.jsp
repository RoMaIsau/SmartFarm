<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<%@ include file="../../parts/meta.jsp"%>
	<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap/js/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap/js/bootstrap-theme.min.css" rel="stylesheet">
	</head>
	<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<%@ include file="../../parts/sidebar.jsp"%>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->

				<%@ include file="../../parts/topbar.jsp"%>

				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<div class = "container">
						<div id="loginbox" style="margin-top:50px;" >
							<h3 class="form-signin-heading"> INGRESE LOS SINTOMAS OBSERVADOS</h3>
							<form:form action="diagnosticarPost" method="POST" modelAttribute="sintomas">
						    	
								<hr class="colorgraph"><br>
								<div class="text-primary">
			                <form:input path="idAnimal" id="idA" type="hidden" class="form-control" value="${idAnimal}" />
						 	 ULCERAS EN UBRES Y BOCA<form:radiobutton path="Ulceras" id="ulceras"  value="true"  class="form-control" /><br><br>
				
							    FALTA DE APETITO<form:radiobutton path="anorexia" id="anorexia"   value="true" class="form-control" /><br><br>
								DEBILIDAD<form:radiobutton path="debilidad" id="debilidad" value="true" class="form-control" /><br><br>
								BAJA PRODUCCION DE LECHE<form:radiobutton path="bajaProduccionLeche" id="bajaProduccion"  value="true"  class="form-control" /><br><br>
								DIARREA <form:radiobutton path="diarrea" id="diarrea"  value="true" class="form-control" /><br><br>
								CONJUNTIVITIS<form:radiobutton path="conjuntivitis" id="conjuntivitis"  value="true" class="form-control" /><br><br>
								TOS<form:radiobutton path="tos" id="tos"  value="true"  class="form-control" /><br><br>
								SALIVACION ESPUMOSA<form:radiobutton path="salivacionEspumosa" id="saliva"  value="true" class="form-control" /><br><br>
								SECRECION NASAL<form:radiobutton path="secrecionNasal" id="secrecion"  value="true" class="form-control" /><br><br>
								</div>
								<h4>¿A partir de que fecha desea chequear los signos vitales del animal?</h4><br>
								<form:input path="fechaSignosVitales" id="fecha" type="date"  class="form-control" /> <br><br>
				
								<button class="btn btn-success  btn-block mb-4" Type="Submit"/>Consultar</button>
							</form:form>
			
							
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		
		<a class="scroll-to-top rounded" href="#page-top"> <i
			class="fas fa-angle-up"></i>
		</a>
		<!-- Logout Modal-->
		<%@ include file="../../parts/modalCerrarSesion.jsp" %>
		<!-- Bootstrap core JavaScript-->
		<%@ include file="../../parts/scripts.jsp" %>
	</body>
</html>

