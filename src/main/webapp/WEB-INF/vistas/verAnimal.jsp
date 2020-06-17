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
					<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Animal</h1>
					</div>
					<div class="row d-flex justify-content-center">
						<div class="col-xl-6 col-md-6 mb-4 ">
							<div class="card border-left-warning shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div class="font-weight-bold text-primary text-uppercase mb-1">
												<c:out value="${animal.id}" />
											</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800">
												<c:out value="${animal.tipo.nombre}" />
											</div>
											<div class="text-xs text-muted mt-2">
												<c:out value="${animal.raza.nombre}" />
											</div>
											<div class="text-xs text-muted mt-2">
												<c:out value="${animal.peso}" /> Kg.
											</div>
										</div>
										<div class="col-auto">
											<c:choose>
												<c:when test="${animal.tipo.id == 1}">
													<img style="width: 70px; height:70px;" src="<c:url value="/img/caprino.png"/>">
												</c:when>
												<c:when test="${animal.tipo.id == 2}">
													<img style="width: 50px; height:50px;" src="<c:url value="/img/equino.png"/>">
												</c:when>
												<c:when test="${animal.tipo.id == 3}">
													<img style="width: 50px; height:50px;" src="<c:url value="/img/ovino.png"/>">
												</c:when>
												<c:when test="${animal.tipo.id == 4}">
													<img style="width: 50px; height:50px;" src="<c:url value="/img/porcino.png"/>">
												</c:when>
												<c:when test="${animal.tipo.id == 5}">
													<img style="width: 50px; height:50px;" src="<c:url value="/img/vacuno.png"/>">
												</c:when>
											</c:choose>
										</div>
									</div>
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
	<%@ include file="../../parts/modalCerrarSesion.jsp"%>

	<!-- Bootstrap core JavaScript-->
	<%@ include file="../../parts/scripts.jsp"%>

</body>
</html>