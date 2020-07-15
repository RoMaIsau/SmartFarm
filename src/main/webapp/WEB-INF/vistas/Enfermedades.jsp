<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.*,java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%!DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	String now = fmt.format(new Date());%>
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
					<div class="row">
						<c:if test="${not empty tratamiento}">
							<div class="col-md-4 col-xl-4">
								<h5>Buscar tratamientos</h5>
								<form id="formBuscar" action="buscarEnfermedad" method="post"
									modelAttribute="tratamiento">
									<input type="search" name="nombre" class="d-inline p-1 form-control" style="width: 60%"
										placeholder="Ingrese enfermedad">
									<button type="submit" id="buscar" class="btn btn-primary text-white">Buscar</button>
								</form>
								<br>
								<div id="busqueda" class="">
									<h2>${descripcion}</h2>
									<h2>${trat}</h2>
								</div>
							</div>
						</c:if>
					</div>
					<c:if test="${empty trat}">
						<div
							class="d-sm-flex align-items-center justify-content-between mb-4">
							<h1 class="h3 mb-0 text-gray-800">Enfermedades</h1>

						</div>
						<div class="row">
							<div class="col-xl-6 col-lg-6 col-md-6">
								<div class="card shadow mb-4">
									<div class="card-body p-2">
										<div class="chart-bar">
											<div class="chartjs-size-monitor">
												<div class="chartjs-size-monitor-expand">
													<div class=""></div>
												</div>
												<div class="chartjs-size-monitor-shrink">
													<div class=""></div>
												</div>
											</div>
											<canvas id="enfermedadesMes" class="chartjs-render-monitor"
												style="display: block; width: 100%; height: 100%;"></canvas>
										</div>
									</div>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-6">
								<div class="card shadow mb-4">
									<div class="card-body p-3">
										<div class="chart-bar">
											<div class="chartjs-size-monitor">
												<div class="chartjs-size-monitor-expand">
													<div class=""></div>
												</div>
												<div class="chartjs-size-monitor-shrink">
													<div class=""></div>
												</div>
											</div>
											<canvas id="mes" class="chartjs-render-monitor"
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
			</c:if>
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
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>

	<script>
		
		
		/* Grafico line gastos en total */ 

		var ctx = document.getElementById("enfermedadesMes");
		var myLineChart = new Chart(ctx, {
		  type: 'line',
		  data: {
		    labels: ["Aftosa", "Leptospirosis", "Miocardiopatia", "Intoxicacion","IBR"],
		    datasets: [{
		      label: "Total",
		      lineTension: 0.3,
		      backgroundColor: "rgba(137, 137, 255, 0.05)",
		      borderColor: "rgba(137, 137, 255, 1)",
		      pointRadius: 3,
		      pointBackgroundColor: "rgba(137, 137, 255, 1)",
		      pointBorderColor: "rgba(137, 137, 255, 1)",
		      pointHoverRadius: 3,
		      pointHoverBackgroundColor: "rgba(137, 137, 255, 1)",
		      pointHoverBorderColor: "rgba(137, 137, 255, 1)",
		      pointHitRadius: 10,
		      pointBorderWidth: 2,
		      data: [<c:forEach items="${todos}" var="gastos">
				${gastos},
				   </c:forEach>],
		    }],
		  },
		  options: {
		    maintainAspectRatio: false,
		    layout: {
		      padding: {
		        left: 10,
		        right: 25,
		        top: 25,
		        bottom: 0
		      }
		    },
		    scales: {
		      xAxes: [{
		        time: {
		          unit: 'date'
		        },
		        gridLines: {
		          display: false,
		          drawBorder: false
		        },
		        ticks: {
		          maxTicksLimit: 7
		        }
		      }],
		      yAxes: [{
		        ticks: {
		          maxTicksLimit: 5,
		          padding: 10,
		          // Include a dollar sign in the ticks
		          callback: function(value, index, values) {
		            return  value;
		          }
		        },
		        gridLines: {
		          color: "rgb(234, 236, 244)",
		          zeroLineColor: "rgb(234, 236, 244)",
		          drawBorder: false,
		          borderDash: [2],
		          zeroLineBorderDash: [2]
		        }
		      }],
		    },
		    legend: {
		      display: false
		    },
		    tooltips: {
		      backgroundColor: "rgb(255,255,255)",
		      bodyFontColor: "#858796",
		      titleMarginBottom: 10,
		      titleFontColor: '#6e707e',
		      titleFontSize: 14,
		      borderColor: '#dddfeb',
		      borderWidth: 1,
		      xPadding: 15,
		      yPadding: 15,
		      displayColors: false,
		      intersect: false,
		      mode: 'index',
		      caretPadding: 10,
		      callbacks: {
		        label: function(tooltipItem, chart) {
		          var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
		          return datasetLabel + '' + tooltipItem.yLabel;
		        }
		      }
		    },
		    title : {
				display : true,
				text : 'Total de enfermedades en 2020',
				fontStyle: 'bold',
				fontSize: 16,
				fontColor: '#22A45A',
			}
		  }
		});
		
		/* Fin grafico line gastos en total */ 
		
		/* Grafico line gastos en total */ 

		var ctx = document.getElementById("mes");
		var myLineChart = new Chart(ctx, {
		  type: 'line',
		  data: {
		    labels: ["Abril","Mayo","Junio","Julio","Agosto","Sept"],
		    datasets: [{
		      label: "Total",
		      lineTension: 0.3,
		      backgroundColor: "rgba(137, 137, 255, 0.05)",
		      borderColor: "rgba(137, 137, 255, 1)",
		      pointRadius: 3,
		      pointBackgroundColor: "rgba(137, 137, 255, 1)",
		      pointBorderColor: "rgba(137, 137, 255, 1)",
		      pointHoverRadius: 3,
		      pointHoverBackgroundColor: "rgba(137, 137, 255, 1)",
		      pointHoverBorderColor: "rgba(137, 137, 255, 1)",
		      pointHitRadius: 10,
		      pointBorderWidth: 2,
		      data: [<c:forEach items="${mes}" var="gastos">
				${gastos},
				   </c:forEach>],
		    }],
		  },
		  options: {
		    maintainAspectRatio: false,
		    layout: {
		      padding: {
		        left: 10,
		        right: 25,
		        top: 25,
		        bottom: 0
		      }
		    },
		    scales: {
		      xAxes: [{
		        time: {
		          unit: 'date'
		        },
		        gridLines: {
		          display: false,
		          drawBorder: false
		        },
		        ticks: {
		          maxTicksLimit: 7
		        }
		      }],
		      yAxes: [{
		        ticks: {
		          maxTicksLimit: 5,
		          padding: 10,
		          // Include a dollar sign in the ticks
		          callback: function(value, index, values) {
		            return  value;
		          }
		        },
		        gridLines: {
		          color: "rgb(234, 236, 244)",
		          zeroLineColor: "rgb(234, 236, 244)",
		          drawBorder: false,
		          borderDash: [2],
		          zeroLineBorderDash: [2]
		        }
		      }],
		    },
		    legend: {
		      display: false
		    },
		    tooltips: {
		      backgroundColor: "rgb(255,255,255)",
		      bodyFontColor: "#858796",
		      titleMarginBottom: 10,
		      titleFontColor: '#6e707e',
		      titleFontSize: 14,
		      borderColor: '#dddfeb',
		      borderWidth: 1,
		      xPadding: 15,
		      yPadding: 15,
		      displayColors: false,
		      intersect: false,
		      mode: 'index',
		      caretPadding: 10,
		      callbacks: {
		        label: function(tooltipItem, chart) {
		          var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
		          return datasetLabel + '' + tooltipItem.yLabel;
		        }
		      }
		    },
		    title : {
				display : true,
				text : 'Enfermedades por mes',
				fontStyle: 'bold',
				fontSize: 16,
				fontColor: '#22A45A',
			}
		  }
		});
		
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
						position : 'bottom',
					},
					title : {
						display : true,
						text : 'Tipos de gastos por mes',
						fontStyle: 'bold',
						fontSize: 16,
						fontColor: '#22A45A'
					}
				}
			});
		};
		
	</script>

</body>
</html>