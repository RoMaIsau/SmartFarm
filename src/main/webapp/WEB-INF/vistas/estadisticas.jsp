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

					<h1 class="h3 mb-2 text-gray-800">Estadísticas</h1>
					<p class="mb-4">A continuación se muestran gráficos sobre datos
						de los gastos contabilizados</p>

					<div class="row">
						<div class="col-xl-4 col-lg-5">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">Gastos por
										tipo</h6>
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
										<canvas id="gastosEnTotalPorTipo"
											class="chartjs-render-monitor"
											style="display: block; width: 100%; height: 100%;"></canvas>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xl-8 col-lg-7">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">Gastos
										realizados por mes</h6>
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
					<div class="row">
						<div class="col-xl-6 col-lg-7">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">Gastos en
										total</h6>
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
										<canvas id="gastosEnTotal" class="chartjs-render-monitor"
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

	<!-- Logout Modal-->
	<%@ include file="../../parts/modalCerrarSesion.jsp"%>

	<!-- Bootstrap core JavaScript-->
	<%@ include file="../../parts/scripts.jsp"%>

	<script type="text/javascript" src="<c:url value="/js/Chart.min.js"/>"></script>

	<script>
		/* Grafico gastos por mes */ 		
		var barChartData = {
			labels : [ 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre',
					'Diciembre' ],
			datasets : [
					{
						label : 'Alimenticio',
						backgroundColor : '#FFD57E',
						borderColor : '#FFD57E',
						borderWidth : 1,
						data : [ <c:forEach items="${alimenticio}" var="gastos">
						${gastos.value},
						</c:forEach> ]
					},
					{
						label : 'Empresarial',
						backgroundColor : '#AD84C7',
						borderColor : '#AD84C7',
						borderWidth : 1,
						data : [ <c:forEach items="${empresarial}" var="gastos">
						${gastos.value},
						</c:forEach> ]
					},
					{
						label : 'Médico',
						backgroundColor : '#89C7B6',
						borderColor : '#89C7B6',
						borderWidth : 1,
						data : [ <c:forEach items="${medico}" var="gastos">
						${gastos.value},
						</c:forEach> ]
					},
					{
						label : 'Tecnológico',
						backgroundColor : '#7998C9',
						borderColor : '#7998C9',
						borderWidth : 1,
						data : [ <c:forEach items="${tecnologico}" var="gastos">
						${gastos.value},
						</c:forEach> ]
					},
					]

		};

		/* Fin grafico gastos por mes */ 
		
		
		/* Grafico pie gastos en total por tipo */ 
		
		var config2 = {
			type: 'pie',
			data: {
				datasets: [{
					data: [<c:forEach items="${gastosEnTotalPorTipo}" var="gastoPorTipo">
						   		${gastoPorTipo.monto},
						   </c:forEach>],
					backgroundColor: ['#FFD57E', '#AD84C7', '#89C7B6', '#7998C9'],
					label: 'Dataset 1'
				}],
				labels: [
					'Alimenticio',
					'Empresarial',
					'Médico',
					'Tecnológico'
				]
			},
			options: {
				responsive: true
			}
		};
		
		/* Fin grafico pie gastos en total por tipo */ 
		
		/* Grafico line gastos en total */ 
		
		var lineChartData = {
			labels: [ 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre',
				'Diciembre' ],
			datasets: [{
				label: 'Gastos finales por mes',
				borderColor: '#FFD57E',
				backgroundColor: '#FFD57E',
				fill: false,
				data: [<c:forEach items="${gastosEnTotalPorMes}" var="gastos">
						${gastos.value},
					   </c:forEach>],
				yAxisID: 'y-axis-1',
			}]
		};
		
		/* Fin grafico line gastos en total */ 

		window.onload = function() {
			var chartTotal = document.getElementById('gastosEnTotalPorTipo').getContext('2d');
			window.myPie = new Chart(chartTotal, config2);
			
			var chartPorMes = document.getElementById('gastosPorMes').getContext('2d');
			window.myBar = new Chart(chartPorMes, {
				type : 'bar',
				data : barChartData,
				options : {
					responsive : true,
					legend : {
						position : 'top',
					},
					title : {
						display : true,
						text : 'Gastos por mes en pesos'
					}
				}
			});
			
			var ctx = document.getElementById('gastosEnTotal').getContext('2d');
			window.myLine = Chart.Line(ctx, {
				data: lineChartData,
				options: {
					responsive: true,
					hoverMode: 'index',
					stacked: false,
					title: {
						display: true,
						text: 'Gastos en total en pesos'
					},
					scales: {
						yAxes: [{
							type: 'linear', 
							display: true,
							position: 'left',
							id: 'y-axis-1',
						}],
					}
				}
			});
		};
		
	</script>

</body>
</html>