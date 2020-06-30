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
			<div id="" style="margin-top:30px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">	       
		<h2>CALENDARIO DE VACUNACION</h2>       
  <c:forEach items="${vacunas}" var="vacuna">
  
				    	 
				  <h3 class="text-danger">"Vacuna: ${vacuna.nombre}"</h3>
				  <h3 class="text-danger">"Edad de aplicacion:${vacuna.edadAplicacionMeses} meses"</h3>
				  
						
							
					 </br>		
							
							 
</c:forEach>
</div>
</div>

</body>
</html>