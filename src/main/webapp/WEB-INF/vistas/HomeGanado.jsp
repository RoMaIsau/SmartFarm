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
	
	<h1>Lista de ganado vacuno:</h1>



  <c:forEach items="${vencidos}" var="v">
  
				    	 
				  <h3 class="text-danger">"La vaca  ${v.id} tiene vacunas vencidas"
						<a href="detalle?id=${v.id}">Ver</a><br>	
					</h3>
					 </br>		
					
							 
</c:forEach>



 <c:forEach items="${vencidas}" var="v">
  
				    	 
				  <h3 class="text-danger">"Vacunas vencidas: ${v.nombre}"
							<a href="vacunar?id=${vacaId}&nombre=${v.nombre}">Vacunar</a><br>
					</h3> </br>		
							
							 
</c:forEach><BR>


<h3>${mensaje}</h3><br>
<h3><a href="/SmartFarm/homeGanado" >VOLVER</a></h3><br>
<h3><a href="/SmartFarm/listaGanado" >MONITOREO DE SIGNOS VITALES</a></h3><BR>
<h3><a href="/SmartFarm/historiaClinica" >HISTORIAS CLINICAS</a></h3><BR>

 	

  	
							
							 



							

		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>
