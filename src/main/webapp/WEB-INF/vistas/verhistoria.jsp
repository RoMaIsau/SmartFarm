<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
					<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h1 mb-0 text-gray-800">Historia clínica</h1>
					</div>
					
					<c:if test="${not empty hc}">
	 					<div class="row no-gutters align-items-center">											
 							<c:forEach items="${signos}" var="s">
 								<div class="col-md-6">
									<div class="card border-left-primary shadow mr-3 h-100 py-2">
	 									<div class="card-body">
	 										<div class="font-weight-bold">
	 											<fmt:formatDate value="${s.fecha}" pattern="yyyy-MM-dd" var="currentYear"/>
												<h4 class="text-primary">Fecha: <small class="text-danger"><c:out value="${currentYear}"/></small></h4>
	 											<h4 class="text-primary">Temperatura: <small class="text-danger">${s.temperatura} °C</small></h4>
												<h4 class="text-primary">Pulso: <small class="text-danger">${s.pulso} PPM</small></h4>
												<h4 class="text-primary">Frecuencia cardiaca: <small class="text-danger">${s.frecuenciaCardiaca} PPM</small></h4>
												<h4 class="text-primary">Frecuencia respiratoria: <small class="text-danger">${s.frecuenciaRespiratoria}</small></h4>
											</div>
										</div>
									</div>
								</div>
								<br>
							</c:forEach><br><br>
						</div>
					</c:if>	<br>
					
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