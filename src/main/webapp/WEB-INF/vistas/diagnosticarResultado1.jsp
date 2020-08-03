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
					<h3 class="text-info">${enfermedad}</h3><br>
					
					<c:if test="${enfermedad != 'No hay suficientes síntomas de enfermedad'}">
						<c:if test="${not empty hcId}">
							<c:if test="${empty tratamientoB}">
							
								<div class="container" style="max-width: 720px !important;">
									<div class="card o-hidden border-0 shadow-lg my-5">
										<div class="card-body p-0">
											<!-- Nested Row within Card Body -->
											<div class="row">
												<div class="col-lg-12">
													<div class="p-5">
														<div 	class="text-center">
														
															<h1 class="h4 text-gray-900 mb-4">
																Si considera que el resultado del diagnóstico es correcto,
																precione aceptar para guardar e iniciar el tratamiento.
															</h1>
															
															<div class="row">
																<div class="col-sm-12 col-md-6 mb-sm-0">
																	<a href="indexVeterinario">
																		<button class="btn btn-danger btn-block">Rechazar</button>
																	</a>
																</div>
																<div class="col-sm-12 col-md-6 mb-sm-0">
																	<a href="guardarEnfermedad?id=${hcId}&&nombre=${nombre}&&tratamiento=${tratamiento}">
																		<button class="btn btn-success btn-block" Type="Submit">Aceptar</button>
																	</a>
																</div>
															</div>
															
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								
							</c:if>
						</c:if>
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