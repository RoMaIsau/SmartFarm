<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	 <h3>HISTORIAS:</h3>	       
  <c:forEach items="${historias}" var="historia">
  
				    	 
		<a href="verhistoria?id=${historia.idAnimal}">
		<h3 class="text-danger">Ver historia de vaca  ${historia.idAnimal}</h3></a>
				   
			<a href="diagnosticar?id=${historia.idAnimal}">
		<h3 class="text-danger">Diagnosticar vaca  ${historia.idAnimal}</h3></a>			
							
					 </br>		
							
							 
</c:forEach>

	 <h3>Historia:</h3>	       
  <c:forEach items="${hc.signos}" var="signos">
  
				    	 
		
		Temperatura:<h3 class="text-danger">  ${signos.temperatura}</h3>
		Pulso: <h3 class="text-danger">  ${signos.pulso}</h3>
		Frecuencia cardiaca: <h3 class="text-danger">  ${signos.frecuenciaCardiaca}</h3>
		Frecuencia respiratoria: <h3 class="text-danger">  ${signos.frecuenciaRespiratoria}</h3>	
		Fecha: <h3 class="text-danger">  ${signos.fecha}</h3>		   
						
							
					 </br>		
							
							 
</c:forEach>
<h3>
La vaca podria tener ${enfermedad}

</h3>

  

</body>
</html>