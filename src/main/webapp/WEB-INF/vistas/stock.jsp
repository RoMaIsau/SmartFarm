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
					<!-- Page Heading -->
					<div class="d-flex flex-row-reverse">
						<a href="registrarAlimento" class="btn btn-success btn-icon-split">
							<span class="icon text-white-50"> <i class="fas fa-plus"></i>
						</span> <span class="text">Agregar alimento</span>
						</a>
					</div>

					<!-- Page Heading -->
					<c:forEach items="${tipos}" var="tipo">

						<div
							class="d-sm-flex align-items-center justify-content-between mb-4">
							<h1 class="h3 mb-0 text-gray-800">${tipo.nombre}</h1>
						</div>
						<div class="row">
							<c:forEach items="${alimentos}" var="alimento">
								<c:if test="${alimento.tipo.id == tipo.id}">
									<div class="col-xl-3 col-md-6 mb-4">
										<c:choose>
											<c:when test="${alimento.tipo.id == 1}">
												<div class="card border-left-warning shadow h-100 py-2">
											</c:when>
											<c:when test="${alimento.tipo.id == 2}">
												<div class="card border-left-info shadow h-100 py-2">
											</c:when>
											<c:when test="${alimento.tipo.id == 3}">
												<div class="card border-left-primary shadow h-100 py-2">
											</c:when>
										</c:choose>
										<div class="card-body">
											<div class="row no-gutters align-items-center">
												<div class="col mr-2">
													<div
														class="font-weight-bold text-primary text-uppercase mb-1">
														<c:out value="${alimento.nombre}" />
													</div>
													<div class="h5 mb-0 font-weight-bold text-gray-800">
														<c:out value="${alimento.cantidad}" />
														Kg.
													</div>
													<div class="text-xs text-muted mt-2">
														Stock min.:
														<c:out value="${alimento.stockMinimo}" />
														Kg.
													</div>
												</div>
												<div class="col-auto">
													<c:choose>
														<c:when test="${alimento.tipo.id == 1}">
															<i class="fab fa-pagelines fa-2x text-gray-300"></i>
														</c:when>
														<c:when test="${alimento.tipo.id == 2}">
															<i class="fas fa-seedling fa-2x text-gray-300"></i>
														</c:when>
														<c:when test="${alimento.tipo.id == 3}">
															<i class="fas fa-feather-alt fa-2x text-gray-300"></i>
														</c:when>
													</c:choose>
													<div class="text-primary editarAlimento"
														style="display: none">
														<a href="#modalStock" data-id="${alimento.id}"
															role="button" data-toggle="modal" class="modalStock"><i
															class="fas fa-edit my-3 text-info"></i></a>
													</div>
												</div>
											</div>
										</div>
									</div>
						</div>
						</c:if>
					</c:forEach>
				</div>
				</c:forEach>

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
	<!-- Modal cambiar stock min -->
	<div class="modal fade" id="modalStock" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalCenterTitle">Cambiar
						stock mínimo</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="cambiarStockMinimo" method="GET">
						<label for="stockMinimo">Stock min.:</label>
						<input class="form-control" type="text" name="stockMinimo" id="stockMinimo">
						<input class="idInput" type="text" name="id" style="display:none;">
						<hr>
						<div class="d-flex justify-content-end mt-2">
							<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancelar</button>
							<button type="submit" class="btn btn-primary mx-2 botonCambiarStock">Cambiar</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Logout Modal-->
	<%@ include file="../../parts/modalCerrarSesion.jsp"%>

	<!-- Bootstrap core JavaScript-->
	<%@ include file="../../parts/scripts.jsp"%>

	<!-- Asignar valor de id en modal -->
	<script>
		$(document).on(
				"click",
				".modalStock",
				function() {
					var id = $(this).data('id');
					$('.idInput').attr('value', id);
				});
	</script>

</body>
</html>