<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="../../parts/meta.jsp"%>

</head>
<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		

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

	  <h3>HISTORIAS:</h3><br>	       
  <c:forEach items="${historias}" var="historia">
  
				    	 
		<a href="verhistoria?id=${historia.idAnimal}">
		<h3 class="text-success">Ver historia de animal  ${historia.idAnimal}</h3></a><br>
				   
			<a href="diagnosticar?id=${historia.idAnimal}">
		<h3 class="text-success">Diagnosticar animal  ${historia.idAnimal}</h3></a>			
							
					 </br>	<br>	
							
							 
</c:forEach> 

<c:if test="${not empty hc}">
	 <h3>Historia Clinica:</h3>	       
  <c:forEach items="${hc.signos}" var="signos">
  
				    	 
		
		Temperatura:<h3 class="text-danger">  ${signos.temperatura}</h3>
		Pulso: <h3 class="text-danger">  ${signos.pulso}</h3>
		Frecuencia cardiaca: <h3 class="text-danger">  ${signos.frecuenciaCardiaca}</h3>
		Frecuencia respiratoria: <h3 class="text-danger">  ${signos.frecuenciaRespiratoria}</h3>	
		Fecha: <h3 class="text-danger">  ${signos.fecha}</h3>		   
						
							
					 </br>		
							
							 
</c:forEach>
	</c:if>	<br>
<h3>

${enfermedad}

</h3><br>

 <h3><a href="/SmartFarm/indexVeterinario" >Home Veterinario</a></h3>	 

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