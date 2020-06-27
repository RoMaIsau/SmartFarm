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

					<h1 class="h3 mb-2 text-gray-800">Estad�sticas</h1>
					<p class="mb-4">A continuaci�n se muestran todos los gastos que
						usted ha contabilizado</p>

					<div class="card shadow mb-4">
						<div class="card-header py-3 mx-0 row justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary">Gastos</h6>
							<a href="estadisticasnuevas"
								class="btn btn-success btn-icon-split"> <span
								class="icon text-white-50"> <i class="fas fa-plus"></i>
							</span> <span class="text">Agregar gasto</span>
							</a>
						</div>

						<div></div>

						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>N� registro</th>
											<th>Tipo de gasto</th>
											<th>Fecha</th>
											<th>Monto</th>
											<th>Acciones</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${gastos}" var="gastos">
											<tr>
												<td><c:out value="${gastos.id}" /></td>
												<td><c:out value="${gastos.tipoDeGasto}" /></td>
												<td><c:out value="${gastos.fecha}" /></td>
												<td>$ <c:out value="${gastos.monto}" /></td>
												<td class="text-center"><a
													href="estadisticasamodificar?id=${gastos.id}"> <i
														class="fas fa-edit mx-2 text-info"></i>
												</a> <a href="#modalEliminar" data-id="${gastos.id}"
													role="button" data-toggle="modal" class="open-Modal"> <i
														class="fas fa-trash mx-2 text-danger"></i>
												</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xl-8 col-lg-7">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">Metros
										recorridos</h6>
								</div>
								<div class="card-body">
									<div class="chart-bar">
										<div class="chartjs-size-monitor">
											<div class="chartjs-size-monitor-expand">
												<div class=""></div>
											</div>
											<div class="chartjs-size-monitor-shrink">
												<div class=""></div>
											</div>
										</div>
										<canvas id="gastosPorMes" class="chartjs-render-monitor"
											style="display: block; width: 100%; height: 100%;"></canvas>
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

	<!-- Modal eliminar registro -->
	<div class="modal fade" id="modalEliminar" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Borrar registro</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">�Quiere borrar el registro?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancelar</button>
					<a type="button"
						class="btn btn-danger text-white botonEliminarGastos">Aceptar</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Logout Modal-->
	<%@ include file="../../parts/modalCerrarSesion.jsp"%>

	<!-- Bootstrap core JavaScript-->
	<%@ include file="../../parts/scripts.jsp"%>

	<script type="text/javascript" src="<c:url value="/js/Chart.min.js"/>"></script>

	<script>
		var MONTHS = [ 'January', 'February', 'March', 'April', 'May', 'June',
				'July', 'August', 'September', 'October', 'November',
				'December' ];
		var color = Chart.helpers.color;
		var barChartData = {
			labels : [ 'January', 'February', 'March', 'April', 'May', 'June',
					'July' ],
			datasets : [
					{
						label : 'Dataset 1',
						backgroundColor : '#e36387',
						borderColor : '#e36387',
						borderWidth : 1,
						data : [ 1, 2, 3, 4, 5, 6, 7 ]
					},
					{
						label : 'Dataset 2',
						backgroundColor : '#709fb0',
						borderColor : '#709fb0',
						borderWidth : 1,
						data : [ 1, 2, 3, 4, 5, 6, 7 ]
					} ]

		};

		window.onload = function() {
			var ctx = document.getElementById('gastosPorMes').getContext('2d');
			window.myBar = new Chart(ctx, {
				type : 'bar',
				data : barChartData,
				options : {
					responsive : true,
					legend : {
						position : 'top',
					},
					title : {
						display : true,
						text : 'Chart.js Bar Chart'
					}
				}
			});

		};
	</script>

</body>
</html>