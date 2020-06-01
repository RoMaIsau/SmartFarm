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
				
					<h1 class="h3 mb-2 text-gray-800">Estadísticas</h1>
					<p class="mb-4">A continuación se muestran todos los gastos que ha contabilizado</p>
					 
					<div class="card shadow mb-4">
						<div class="card-header py-3 mx-0 row justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary">Registros</h6>
							<a href="contabilizargastos" class="btn btn-success btn-icon-split">
								<span class="icon text-white-50">
									<i class="fas fa-plus"></i>
								</span>
								<span class="text">Agregar registro</span>
							</a>
						</div>
						
						<div>
						</div>
						
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
									<thead>
										<tr>
											<th>Usuario</th>
											<th>Fecha</th>
											<th>Gastos Alimenticios</th>
											<th>Gastos Empresariales</th>
											<th>Gastos Medicos</th>
											<th>Gastos Tecnologicos</th>
											<th>Gastos Total</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${gastos}" var="gastos">
											<tr>
												<td><c:out value="${gastos.usuario.id}"/></td>
												<td><c:out value="${gastos.fecha}"/></td>
												<td>$ <c:out value="${gastos.gastosAlimenticios}" /></td>
												<td>$ <c:out value="${gastos.gastosEmpresariales}" /></td>
												<td>$ <c:out value="${gastos.gastosMedicos}" /></td>
												<td>$ <c:out value="${gastos.gastosTecnologicos}" /></td>
												<td>$ <c:out value="${gastos.gastosTotal}" /></td>
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
	<%@ include file="../../parts/modalCerrarSesion.jsp"%>

	<!-- Bootstrap core JavaScript-->
	<%@ include file="../../parts/scripts.jsp"%>

</body>
</html>