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



  <c:forEach items="${dietas}" var="d">
  
				    	 
				  <h3 class="text-danger">"La vaca  ${d.id}  tiene dietas pendientes"
							<a href="detalleDieta?id=${d.id}">Ver</a><br>
					</h3> </br>		
							
							 
</c:forEach>

 <c:forEach items="${vencidas}" var="v">
  
				    	 
				  <h3 class="text-danger">"Vacunas vencidas: ${v.nombre}"
							<a href="vacunar?id=${vacaId}&nombre=${v.nombre}">Vacunar</a><br>
					</h3> </br>		
							
							 
</c:forEach>

 <c:forEach items="${detalleDieta}" var="d">
  
				    	 
				  <h3 class="text-danger">Dietas pendientes: ${d.nombre}<br>
				   ${d.fechaYhora}<br>
							<a href="alimentar?id=${vacaId}&nombre=${d.nombre}">Alimentar</a><br>
					</h3> </br>		
							
							 
</c:forEach>

  	
							
							 



							

		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>
