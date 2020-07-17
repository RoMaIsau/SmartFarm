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
	<div class="container-fluid mt-3">	
  <c:forEach items="${anormales}" var="a">
  	   <div class="alert alert-warning" role="alert">	 
			<strong>
				   ¡¡Signos vitales anormales en el animal ${a.id}!!!</strong>
						
					
					<a href="signos?id=${a.id}"> <i class="	fas fa-heartbeat"></i></a>	</div>							 
</c:forEach>
</div>	
	<c:if test="${not empty alarmaTratamiento}">
	<div class="alert alert-warning" role="alert">	 
			<strong>
    	<h3 class="text-danger"> ${alarmaTratamiento} </h3>
    <a href="nuevoDiagnostico?id=${idAnimalTratamiento}"> NUEVO DIAGNOSTICO <i class="fas fa-user-md"></i> </a><br>
  	</strong>
  	</c:if>
	<div class="container-fluid">
					<c:if test="${not empty animales}">
						
						
						<div class="card shadow mb-4 mt-2">
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
										<thead>
											<tr>
												<th>ID Animal</th>
												
												<th>SALUD ACTUAL</th>
												
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${animales}" var="a">
												<tr>
													<td>${a.id}</td>
													
													<td>
														<a href="verEstadoSalud?id=${a.id}">
															<i class="	fas fa-plus-square"></i>
														</a>
													</td>
												
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</c:if>



  <c:forEach items="${signos2}" var="a">
  TEMPERATURA:
				    <h3 class="text-danger"> ${a.temperatura}!!!!</h3><BR>
				    frecuencia cardiaca:
					  <h3 class="text-danger"> ${a.frecuenciaCardiaca}!!!!</h3><br>	
													 
</c:forEach>


</div>
</div>	

<div class="container-fluid">
					<c:if test="${not empty idAnimalTratamiento}">
						
						
						<div class="card shadow mb-4 mt-2">
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
										<thead>
											<tr>
												<th>ID Animal</th>
												
												<th>VACUNAS APLICADAS</th>
												
												<th>ENFERMEDAD ACTUAL</th>
												
												<th>TRATAMIENTO EN CURSO</th>
												
											</tr>
										</thead>
										<tbody>
											
												<tr>
													<td>${idAnimalTratamiento}</td>
													
													<td>
														    <c:if test="${not empty vacunas}">
			        
		     
 	

   <c:forEach items="${vacunas}" var="vac">
  
				    <h5 class="text-primary"> ${vac.nombre}</h5><BR>
				  	
													 
</c:forEach>
  
 
    </c:if>	
													</td>
												<td>
												<c:if test="${not empty enfermedadA}">${enfermedadA}</c:if>
												<c:if test="${not empty enfermedadB}">${enfermedadB}</c:if>
												
												</td>
											<td><c:if test="${not empty nombreTratamiento}">${nombreTratamiento}</c:if>
											<c:if test="${empty nombreTratamiento}">NO</c:if>
											</td>
												</tr>
											
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</c:if>



 
 
 	
 	<c:if test="${not empty signos}">
			        
		        
 	
 <h3 class="text-info">SIGNOS VITALES ACTUALES</h3>
 
  
  
  <c:if test="${signos.temperatura gt 40.0}">
 <H3 class="text-danger"> Temperatura (°C): </H3><h2 class="text-danger"> ${signos.temperatura} </h2>
 </c:if>
 <c:if test="${signos.temperatura lt 40.0}">
 <H3>Temperatura (°C)</H3>
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
    

    
    	<br>
  	
							
					 
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
