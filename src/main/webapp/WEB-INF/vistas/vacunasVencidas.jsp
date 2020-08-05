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
							<c:if test="${not empty vencidos}">
						
						
						<div class="card shadow mb-4 mt-2">
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
										<thead>
											<tr>
												<th>ID Animal</th>
												
												<th>VACUNAS VENCIDAS</th>
												
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${vencidos}" var="a">
												<tr>
													<td>${a.id}</td>
													
													<td>
														<a href="detalle?id=${a.id}">
															<i class="fas fa-briefcase-medical"></i>
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
								<c:if test="${not empty vencidas}">
					<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Vacunas vencidas</h1>
					</div>
					<div class="row no-gutters align-items-center">
						<c:forEach items="${vencidas}" var="v">
							<div class="col-md-6 mr-2">
								<div class="card-body border-left-primary shadow">
									<div class="font-weight-bold text-primary text-uppercase mb-1">${v.nombre}
										<a href="vacunar?id=${vacaId}&nombre=${v.nombre}">
											<i class="fas fa-syringe"></i>
										</a>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:if>
				<h3 class="text-info">${mensaje}</h3><br>
	
	
	
				
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