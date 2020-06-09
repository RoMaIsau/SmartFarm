<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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

					<h1 class="h3 mb-2 text-gray-800">Mapa</h1>
					<div id="map" style="height: 500px; width: 100%;"></div>
					<div class="card shadow mb-4 mt-2">
						<div class="card-header py-3 mx-0 row justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary">Cantidad de
								animales ${fn:length(lists)}</h6>
						</div>

						<div class="card-body">
							<div class="table-responsive">
								<table class="table text-center" id="tabla" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>ID Animal</th>
											<th hidden>Latitud</th>
											<th hidden>Longitud</th>
											<th>Tipo</th>
											<th>Raza</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${lists}" var="ubicacion">
											<td>${ubicacion.animal.id}</td>
											<td hidden>${ubicacion.longitud}</td>
											<td hidden>${ubicacion.latitud}</td>
											<td>${ubicacion.animal.tipo.nombre}</td>
											<td>${ubicacion.animal.raza.nombre}</td>
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
	
	<script src='https://api.mapbox.com/mapbox-gl-js/v1.10.1/mapbox-gl.js'></script>
	<link href='https://api.mapbox.com/mapbox-gl-js/v1.10.1/mapbox-gl.css'
		rel='stylesheet' />
	<script src="<c:url value="/js/mostrarMapa.js"/>"></script>
	
	<%@ include file="../../parts/scripts.jsp"%>

</body>
</html>