<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../../parts/meta.jsp"%>
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
	<body>
  <c:forEach items="${anormales}" var="a">
  
				    <h3 class="text-danger">"Chequee salud del animal ${a.id}!!!!</h3>
						
					<h4 class="text-danger">Signos vitales anormales...</h4><br>								 
</c:forEach>
	
	<div class = "container">
			<div id="" style="margin-top:30px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">	       
	<div class="row">
       
  <c:forEach items="${animales}" var="animal">
  
		 <div class="col-md-6">		    	 
				  <h3 class="text-primary">"Animal numero  ${animal.id}"</h3>
				   <h4><a href="verEstadoSalud?id=${animal.id}">VER SALUD ACTUAL</a></h4>
					</div> 	
							
					 </br>		
							
							 
</c:forEach>
</div> 	


  <c:forEach items="${signos2}" var="a">
  TEMPERATURA:
				    <h3 class="text-danger"> ${a.temperatura}!!!!</h3><BR>
				    frecuencia cardiaca:
					  <h3 class="text-danger"> ${a.frecuenciaCardiaca}!!!!</h3><br>	
													 
</c:forEach>


</div>
</div>	



 
 
 	
 	<c:if test="${not empty signos}">
			        
		        
 	
 <h3 class="text-info">"SIGNOS VITALES ACTUALES"</h3>
 
  
  
  <c:if test="${signos.temperatura gt 40.0}">
 <H3 class="text-danger"> Temperatura (�C): </H3><h2 class="text-danger"> ${signos.temperatura} </h2>
 </c:if>
 <c:if test="${signos.temperatura lt 40.0}">
 <H3>Temperatura (�C)</H3>
 <h3> ${signos.temperatura} </h3>
 </c:if>
 <H3>Frecuencia Cardiaca(lpm)</H3>
  <h3> ${signos.frecuenciaCardiaca} </h3>
  
  <H3>Frecuencia Respiratoria (rpm)</H3>
   <h3> ${signos.frecuenciaRespiratoria} </h3>
   
   <H3>Pulso </H3>
    <h3> ${signos.pulso} </h3>
    <br>
    <a href="modificarSignos?id=${signos.id}"> MODIFICAR </a><br>
    </c:if>	<br><br>
    
    <c:if test="${not empty vacunas}">
			        
		     
 	
 <h3 class="text-info">"VACUNAS APLICADAS"</h3>
   <c:forEach items="${vacunas}" var="vac">
  
				    <h3 class="text-danger"> ${vac.nombre}</h3><BR>
				  	
													 
</c:forEach>
  
 
    </c:if>	
    
    	<c:if test="${not empty alarmaTratamiento}">
    	<h3> ${alarmaTratamiento} </h3>
    <a href="nuevoDiagnostico?id=${idAnimalTratamiento}"> NUEVO DIAGNOSTICO </a><br>
  	</c:if>	<br>
  	
							
					 
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
			</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Smart Farm 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<%@ include file="../../parts/modalCerrarSesion.jsp" %>
	
	<!-- Bootstrap core JavaScript-->
	<%@ include file="../../parts/scripts.jsp" %>
</html>
