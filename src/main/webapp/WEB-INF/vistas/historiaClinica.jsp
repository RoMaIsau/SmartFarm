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
					<c:if test="${not empty mostrarTabla}">
						<h1 class="h3 mb-2 text-gray-800">Historiales clínicos</h1>
						
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
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${animales}" var="a">
												<tr>
													<td>${a.id}</td>
													<td>${a.tipo.nombre}</td>
													<td>${a.raza.nombre}</td>
													<td>
														<a href="verhistoria?id=${a.id}">
															<i class="fas fa-medkit"></i>
														</a>
													</td>
													<td>
														<a href="diagnosticar?id=${a.id}">
															<i class="fas fa-heartbeat"></i>
														</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					
						<div class="row">
							<div class="col-xl-12 col-md-12 mb-4">
								<div class="card border-left-primary shadow h-100 py-2">
									<div class="card-body">
										<div class="row no-gutters align-items-center">
											<div class="col mr-2">
												<div class="font-weight-bold text-primary text-uppercase mb-1">
													<a href="/SmartFarm/enfermedades">Ir al historial de enfermedades.</a>
													<div class="text-xs text-muted mt-2">
														Se mostrará el registro de las enfermedades a lo largo del tiempo.
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					
					<h3 class="text-info">${enfermedad}</h3>
					
					<c:if test="${not empty hc}">
	 					<h3>Historia Clinica:</h3>	       
  						<c:forEach items="${signos}" var="s">
  							Temperatura:<h3 class="text-danger">  ${s.temperatura}</h3>
							Pulso: <h3 class="text-danger">  ${s.pulso}</h3>
							Frecuencia cardiaca: <h3 class="text-danger">  ${s.frecuenciaCardiaca}</h3>
							Frecuencia respiratoria: <h3 class="text-danger">  ${s.frecuenciaRespiratoria}</h3>	
							Fecha: <h3 class="text-danger">  ${s.fecha}</h3>
					 	<br>
						</c:forEach>
					</c:if>	<br>

					<c:if test="${not empty ranking}">
						<h4 class="text-success">TODAS LAS ENFERMEDADES:</h4><br>
						<c:forEach items="${ranking}" var="r">
							<h4>"La enfermedad ${r.key} se registro ${r.value} veces este año"</h4>
				  	 	<br>
				  	 	</c:forEach>
						<br>	
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