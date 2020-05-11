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
					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">Usuarios/as</h1>
					<p class="mb-4">
						Todas las personas que se encuentran registradas en Smart Farm.
					</p>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Usuarios</h6>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>Nombre</th>
											<th>Email</th>
											<th>Rol</th>
											<th>ID</th>
											<th>Fecha de alta</th>
											<th>Acciones</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>Nombre</th>
											<th>Email</th>
											<th>Rol</th>
											<th>ID</th>
											<th>Fecha de alta</th>
											<th>Acciones</th>
										</tr>
									</tfoot>
									<tbody>
										<c:forEach items="${usuarios}" var="usuario">
											<tr>
												<td><c:out value="${usuario.apellido}" />, <c:out value="${usuario.nombre}" /> </td>
												<td><c:out value="${usuario.email}" /></td>
												<td><c:out value="${usuario.rol}" /></td>
												<td><c:out value="${usuario.id}" /></td>
												<td><c:out value="${usuario.fechaAlta}" /></td>
												<td class="text-center"><a href="#"><i class="fas fa-edit mx-2 text-info"></i></a>
													<a href="#modalEliminar" data-id="${usuario.id}" role="button"
													data-toggle="modal" class="open-Modal"><i
														class="fas fa-trash mx-2 text-danger"></i></a></td>
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
						<span>Copyright &copy; Your Website 2019</span>
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

	<!-- Modal eliminar usuario -->
	<div class="modal fade" id="modalEliminar" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Borrar usuario</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">�Quiere borrar el usuario?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancelar</button>
					<a type="button" class="btn btn-danger text-white botonEliminar">Aceptar</a>
				</div>
			</div>
		</div>
	</div>


	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">�</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="js/jquery/jquery.min.js"></script>
	<script src="css/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>
	
	<!-- Page level plugins -->
	<script src="css/datatables/jquery.dataTables.min.js"></script>
	<script src="css/datatables/dataTables.bootstrap4.min.js"></script>
	
	<!-- Page level custom scripts -->
  	<script src="js/datatables-demo.js"></script>
</body>
</html>