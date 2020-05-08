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
	
	<h1>vacas:</h1>


<h1>${idGanado}</h1>
<h1>${vacuna.nombre}</h1>
<h1>${gv}</h1>



  <c:forEach items="${vacunas}" var="v">
  
				    	 
				  <h3 class="text-danger">"La vaca  ${v.key.id}  tiene su vacuna
							${v.value.nombre} vencida"
							
					</h3> </br>		
							
							 
</c:forEach>


							

		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>



