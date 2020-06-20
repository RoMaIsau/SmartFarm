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
	<body>
		<div class = "container">
			<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				
				<form:form action="modificarSignosPost" method="POST" modelAttribute="signosAttribute">
			    	<h3 class="form-signin-heading">Ingrese los signos vitales</h3>
					<hr class="colorgraph"><br>
               <input name="id" id="id" type="hidden" class="form-control" value="${idSigno}" />
					TEMPERATURA:
					<input name="temperatura" id="temperatura" type="text" class="form-control" />
					FRECUENCIA CARDIACA:
					<input name="frecuenciaCardiaca" type="text" id="frecuenciaC" class="form-control"/> 
					FRECUENCIA RESPIRATORIA:    		  
					<input name="frecuenciaRespiratoria" type="text" id="frecuenciaR" class="form-control"/> 
					PULSO:
					<input name="pulso" type="text" id="pulso" class="form-control"/> <br>
					
					<button class="btn btn-lg btn-success btn-block" Type="Submit"/>MODIFICAR</button>
				</form:form>

				
			</div>
		</div>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>