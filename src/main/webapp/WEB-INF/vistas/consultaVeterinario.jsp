<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	</head>
	<body class="">
		<div class = "container text-success">
			<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<h3 class="form-signin-heading text-success"> INGRESE LOS SINTOMAS OBSERVADOS</h3>
			<center>	<form:form action="diagnosticarPost" method="POST" modelAttribute="sintomas">
			    	
					<hr class="colorgraph"><br>
                <form:input path="idAnimal" id="idA" type="hidden" class="form-control " value="${idAnimal}" />
			 	 	ULCERAS EN UBRES Y BOCA <form:radiobutton path="Ulceras" id="ulceras"  value="true"  class="form-control" /><br><br>
	
				    FALTA DE APETITO<form:radiobutton path="anorexia" id="anorexia"   value="true" class="form-control" /><br><br>
					DEBILIDAD<form:radiobutton path="debilidad" id="debilidad" value="true" class="form-control" /><br><br>
					BAJA PRODUCCION DE LECHE<form:radiobutton path="bajaProduccionLeche" id="bajaProduccion"  value="true"  class="form-control" /><br><br>
					DIARREA <form:radiobutton path="diarrea" id="diarrea"  value="true" class="form-control" /><br><br>
					CONJUNTIVITIS<form:radiobutton path="conjuntivitis" id="conjuntivitis"  value="true" class="form-control" /><br><br>
					TOS<form:radiobutton path="tos" id="tos"  value="true"  class="form-control" /><br><br>
					SALIVACION ESPUMOSA<form:radiobutton path="salivacionEspumosa" id="saliva"  value="true" class="form-control" /><br><br>
					SECRECION NASAL<form:radiobutton path="secrecionNasal" id="secrecion"  value="true" class="form-control" /><br><br>
					
					<h4 class="text-info">¿A partir de que fecha desea chequear los signos vitales del animal?</h4><br>
					<form:input path="fechaSignosVitales" id="fecha" type="date"  class="form-control" /> <br><br>
	
					<button class="btn btn-lg btn-success btn-block" Type="Submit"/>Consultar</button>
				</form:form>

				</center>
			</div>
		</div>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>
