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
						<h1 class="h3 mb-3 text-gray-800">Animales</h1>
						
						<div class="row">
							<div class="col-12 col-md-3 offset-md-1 mb-1">
								<div class="d-flex justify-content-center">
									<i class="fas fa-user-md fa-lg mt-3" style="color: #22A45A"></i>   Sin enfermedades registradas<br>(puede diagnosticar)
								</div>
							</div>
							<div class="col-12 col-md-4 mb-1">
								<div class="d-flex justify-content-center">
									<i class="fas fa-user-md fa-lg mt-3" style="color: #0710cc"></i>   Animal recuperado<br>(puede diagnosticar)
								</div>
							</div>
							<div class="col-12 col-md-3 mb-1">
								<div class="d-flex justify-content-center">
									<i class="fas fa-user-md fa-lg mt-3" style="color: #a60000"></i>   Tratamiento en curso<br>(no puede diagnosticar)
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="offset-2 col-8">
								<hr class="colorgraph">
							</div>
						</div>
						
						<div class="row">
							<div class="col-12 col-md-6 mb-1">
								<div class="d-flex justify-content-center">
									<i class="fas fa-heartbeat fa-lg mt-1" style="color: #22A45A"></i>   Signos vitales normales
								</div>
							</div>
							<div class="col-12 col-md-6 mb-1">
								<div class="d-flex justify-content-center">
									<i class="fas fa-heartbeat fa-lg mt-1" style="color: #a60000"></i>   Signos vitales anormales
								</div>
							</div>
						</div>
						
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
												<th>Salud actual</th>
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
													
													<c:forEach items="${enfermedades}" var="enf">
														<c:if test="${enf.historia.id == a.id}">
															<c:choose>
																<c:when test="${enf.nombre == null}">
																	<td class="text-center">
																		<a href="diagnosticar?id=${a.id}">
																			<i class="fas fa-user-md fa-lg" style="color: #22A45A"></i>
																		</a>
																	</td>
																</c:when>
																
																<c:when test="${enf.nombre != null && enf.finTratamiento == null}">
																	<td class="text-center">
																		<i class="fas fa-user-md fa-lg" style="color: #a60000"></i>
																	</td>
																</c:when>
																
																<c:when test="${enf.nombre != null && enf.finTratamiento != null}">
																	<td class="text-center">
																		<a href="diagnosticar?id=${a.id}">
																			<i class="fas fa-user-md fa-lg" style="color: #0710cc"></i>
																		</a>
																	</td>
																</c:when>
															</c:choose>
														</c:if>
													</c:forEach>
													
													<c:forEach items="${signosVitales}" var="signos">
														<c:if test="${signos.historia.id == a.id}">
															<c:if test="${signos.temperatura == 37 and signos.frecuenciaRespiratoria == 25
															and signos.pulso == 80 and signos.frecuenciaCardiaca == 80}">
																<td class="text-center">
																	<a href="signosVitales?id=${a.id}">
																		<i class="fas fa-heartbeat fa-lg" style="color: #22A45A"></i>
																	</a>
																</td>
															</c:if>
															<c:if test="${signos.temperatura != 37 or signos.frecuenciaRespiratoria != 25
															or signos.pulso != 80 or signos.frecuenciaCardiaca != 80}">
																<td class="text-center">
																	<a href="signosVitales?id=${a.id}">
																		<i class="fas fa-heartbeat fa-lg" style="color: #a60000"></i>
																	</a>
																</td>
															</c:if>
														</c:if>
													</c:forEach>
													
													<td class="text-center">
														<a href="verEstadoSalud?id=${a.id}">
															<i class="fas fa-plus-square" style="color: #22A45A"></i>
														</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
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