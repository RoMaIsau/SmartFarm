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
	<div class="container-fluid mt-3">			
  <c:forEach items="${vencidos}" var="v">
  
				   <div class="alert alert-warning" role="alert">	 
			<strong>	  El animal  ${v.id} tiene vacunas vencidas
						<a class="text-primary" href="detalle?id=${v.id}"> <i class="fas fa-briefcase-medical"></i></a></strong><br>	
					</div>
					 </br>		
					
							 
</c:forEach><br>
</div>

<c:if test="${not empty vencidas}">
<div class="d-sm-flex align-items-center justify-content-between mb-4">
<h1 class="h3 mb-0 text-gray-800">Vacunas vencidas</h1>
						</div>
							
							
									
										
	<div class="row no-gutters align-items-center">											
 <c:forEach items="${vencidas}" var="v">
  
				   <div class="col-md-6 mr-2"> 	 
				<div class="card-body border-left-primary shadow">
				
				
 <div
	class="font-weight-bold text-primary text-uppercase mb-1"> ${v.nombre}
							<a href="vacunar?id=${vacaId}&nombre=${v.nombre}"><i class="fas fa-syringe"></i></a>
					 		
					</div>		
					</div>	
					</div>	 
</c:forEach>
</div>
</c:if>
				    	 





				<div class="container-fluid">
					<c:if test="${not empty mostrarTabla}">
						<h1 class="h3 mb-3 text-gray-800">Animales</h1>
						
						<div class="card shadow mb-4 mt-2">
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
										<thead>
											<tr>
												<th>ID Animal</th>
												<th>Tipo de animal</th>
												<th>Raza</th>
												<th>Ver historial</th>
												<th>Diagnosticar</th>
												<th>Signos vitales</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${animales}" var="a">
												<tr>
													<td>${a.id}</td>
													<td>${a.tipo.nombre}</td>
													<td>${a.raza.nombre}</td>
													<td class="text-center">
														<a href="verhistoria?id=${a.id}">
															<i class="fas fa-medkit fa-lg" style="color: #22A45A"></i>
														</a>
													</td>
													<td class="text-center">
														<a href="diagnosticar?id=${a.id}">
															<i class="fas fa-user-md fa-lg" style="color: #22A45A"></i>
														</a>
													</td>
													<td class="text-center">
														<a href="signosVitales?id=${a.id}">
															<i class="fas fa-heartbeat fa-lg" style="color: #22A45A"></i>
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
					
					<h3 class="text-info">${enfermedad}</h3><br>
					<c:if test="${not empty hcId}">
					<c:if test="${empty tratamientoB}">
						
						<a class="text-danger" href="guardarEnfermedad?id=${hcId}&&nombre=${nombre}&&tratamiento=${tratamiento}">Guardar enfermedad</a>
					
					</c:if>
					</c:if>
					<c:if test="${not empty guardada}">
					<h3 class="text-info">${guardada}</h3><br>
					<c:if test="${not empty tratamiento}">
						<h3 class="text-info">${tratamiento}</h3>
						<a class="text-danger" href="tratamientoA?id=${e1Id}">Comenzar tratamiento</a>
					
					</c:if>
					</c:if>
					<c:if test="${not empty tratamientoB}">
						<h3 class="text-info">${tratamientoB}</h3>
						<a class="text-danger" href="tratamientoB?id=${e1Id}">Reanudar tratamiento</a>
					
					</c:if>
					<c:if test="${not empty curada}">
						<h3 class="text-info">${curada}</h3>
					<a class="text-danger" href="finTratamiento?id=${e1Id}">Finalizar tratamiento</a>
					
					</c:if>
					<c:if test="${not empty enfermedades}">

					<div class="d-sm-flex align-items-center justify-content-between mb-4">
<h1 class="h1 mb-0 text-gray-800">ENFERMEDADES</h1>
						</div>
	 						<div class="row no-gutters align-items-center">											
 <c:forEach items="${enfermedades}" var="s">
  
				   <div class="col-md-6"> 	 
				<div class="card-body border-left-primary shadow">
				
				
 <div
	class="font-weight-bold text-primary text-uppercase mb-1">
	 						
  				<h4 class="text-dark">  ${s.nombre}</h4>
							Fecha: <h3 class="text-success">  ${s.fecha}</h3>
							
					 	<br>
					</div>
					</div>
					</div>
						</c:forEach><br><br>
					</div>
					
					</c:if>	<br><br>
				
					<c:if test="${not empty hc}">
					
					
					<div class="d-sm-flex align-items-center justify-content-between mb-4">
<h1 class="h1 mb-0 text-gray-800">SIGNOS VITALES</h1>
						</div>
	 						<div class="row no-gutters align-items-center">											
 <c:forEach items="${signos}" var="s">
  
				   <div class="col-md-6"> 	 
				<div class="card-body border-left-primary shadow">
				
				
 <div
	class="font-weight-bold text-primary text-uppercase mb-1">
	 						
  				<h4 class="text-primary">Temperatura:</h4><h3 class="text-danger">  ${s.temperatura}</h3>
							Pulso: <h3 class="text-danger">  ${s.pulso}</h3>
							Frecuencia cardiaca: <h3 class="text-danger">  ${s.frecuenciaCardiaca}</h3>
							Frecuencia respiratoria: <h3 class="text-danger">  ${s.frecuenciaRespiratoria}</h3>	
							Fecha: <h3 class="text-danger">  ${s.fecha}</h3>
					 	<br>
					</div>
					</div>
					</div>
						</c:forEach><br><br>
					</div>
					
						
					</c:if>	<br>
					

					
					<h3 class="text-info">${mensaje}</h3><br>
					
					
					
					<c:if test="${not empty ranking}">
						<h4 class="text-success">Registro de enfermedades:</h4><br><br>
						
						<c:forEach items="${ranking}" var="r">
							<h4>La enfermedad <span class="font-weight-bold text-uppercase">${r.key}</span> se registro ${r.value} veces este año.</h4>
						<hr class="sidebar-divider my-0">
				  	 	<br><br>
				  	 	</c:forEach>
						
					</c:if>							
				
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
</body>
</html>