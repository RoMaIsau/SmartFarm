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
	 <h3>GANADO:</h3>	       
  <c:forEach items="${animales}" var="animal">
  
				    	 
				  <h3 class="text-danger">"Vaca numero  ${animal.id}"
				   <h4><a href="verEstadoSalud?id=${animal.id}">VER ESTADO DE SALUD</a></h4></h3>
						
							
					 </br>		
							
							 
</c:forEach>

  <c:forEach items="${anormales}" var="a">
  
				    	 
				  <h3 class="text-danger">"Chequee estado de salud de vaca ${a.id}!!!!</h3>
						
							
			
							 
</c:forEach>
</div>
</div>	
 
 
 	
 	<c:if test="${not empty signos}">
			        
		        
 	
 <h2 class="text-info">"SIGNOS VITALES"</h2>
 
  
  
  <c:if test="${signos.temperatura gt 40.0}">
 <H3 class="text-danger"> Temperatura (°C): </H3><h2 class="text-danger"> ${signos.temperatura} </h2>
 </c:if>
 <H3>Temperatura (°C)</H3>
 <h3> ${signos.temperatura} </h3>
 
 <H3>Frecuencia Cardiaca(lpm)</H3>
  <h3> ${signos.frecuenciaCardiaca} </h3>
  
  <H3>Frecuencia Respiratoria (rpm)</H3>
   <h3> ${signos.frecuenciaRespiratoria} </h3>
   
   <H3>Pulso </H3>
    <h3> ${signos.pulso} </h3>
    <br>
    <a href="modificarSignos?id=${signos.id}"> MODIFICAR </a>
    
  	</c:if>	
							
					 



							

		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>
