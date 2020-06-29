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
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Animal</h1>
					</div>
					<div class="row d-flex justify-content-center">
						<div class="col-xl-5 col-md-6 mb-4 ">
							<div class="card border-left-warning shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="font-weight-bold text-primary text-uppercase mb-1">
												<c:out value="${animal.id}" />
											</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800">
												<c:out value="${animal.tipo.nombre}" />
											</div>
											<div class="text-xs text-muted mt-2">
												<c:out value="${animal.raza.nombre}" />
											</div>
											<div class="text-xs text-muted mt-2">
												<c:out value="${animal.peso}" />
												Kg.
											</div>
										</div>
										<div class="col-auto">
											<c:choose>
												<c:when test="${animal.tipo.id == 1}">
													<img style="width: 70px; height: 70px;"
														src="<c:url value="/img/caprino.png"/>">
												</c:when>
												<c:when test="${animal.tipo.id == 2}">
													<img style="width: 50px; height: 50px;"
														src="<c:url value="/img/equino.png"/>">
												</c:when>
												<c:when test="${animal.tipo.id == 3}">
													<img style="width: 50px; height: 50px;"
														src="<c:url value="/img/ovino.png"/>">
												</c:when>
												<c:when test="${animal.tipo.id == 4}">
													<img style="width: 50px; height: 50px;"
														src="<c:url value="/img/porcino.png"/>">
												</c:when>
												<c:when test="${animal.tipo.id == 5}">
													<img style="width: 50px; height: 50px;"
														src="<c:url value="/img/vacuno.png"/>">
												</c:when>
											</c:choose>
										</div>
									</div>
								</div>
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
										<canvas id="myBarChart" width="606" height="320"
											class="chartjs-render-monitor"
											style="display: block; width: 303px; height: 160px;"></canvas>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xl-4 col-lg-5">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">Alimentación
										por tipo</h6>
								</div>
								<div class="card-body">
									<div class="chart-pie">
										<div class="chartjs-size-monitor">
											<div class="chartjs-size-monitor-expand">
												<div class=""></div>
											</div>
											<div class="chartjs-size-monitor-shrink">
												<div class=""></div>
											</div>
										</div>
										<canvas id="chartAlimento" width="1008" height="506"
											class="chartjs-render-monitor"
											style="display: block; width: 504px; height: 253px;"></canvas>
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
	
	Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
	Chart.defaults.global.defaultFontColor = '#858796';

	// Grafico metros recorridos
	var ctx = document.getElementById("myBarChart");
	var myBarChart = new Chart(ctx, {
	  type: 'bar',
	  data: {
		labels:[
		<c:forEach items="${animalUbicacion}" var="lista">
			"${lista.fecha}",
		</c:forEach>
	  	],
	    datasets: [{
	      label: "Metros",
	      backgroundColor: "#4e73df",
	      hoverBackgroundColor: "#2e59d9",
	      borderColor: "#4e73df",
	      data: [
	    	<c:forEach items="${animalUbicacion}" var="lista">
	  			"${lista.metrosRecorridos}",
	  		</c:forEach>
		  ],
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
	          unit: 'day'
	        },
	        gridLines: {
	          display: false,
	          drawBorder: false
	        },
	        ticks: {
	          maxTicksLimit: 6
	        },
	        maxBarThickness: 25,
	      }],
	      yAxes: [{
	        ticks: {
	          min: 0,
	          max: 1500,
	          maxTicksLimit: 5,
	          padding: 10,
	          // Include a dollar sign in the ticks
	          callback: function(value, index, values) {
	            return value + ' mts.';
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
	      titleMarginBottom: 10,
	      titleFontColor: '#6e707e',
	      titleFontSize: 14,
	      backgroundColor: "rgb(255,255,255)",
	      bodyFontColor: "#858796",
	      borderColor: '#dddfeb',
	      borderWidth: 1,
	      xPadding: 15,
	      yPadding: 15,
	      displayColors: false,
	      caretPadding: 10,
	      callbacks: {
	        label: function(tooltipItem, chart) {
	          var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
	          return datasetLabel + ': ' + tooltipItem.yLabel;
	        }
	      }
	    },
	  }
	});
	// Fin grafico metros recorridos

	// Grafico alimentos 
	 
	var ctx = document.getElementById("chartAlimento");
	var myPieChart = new Chart(ctx, {
	  type: 'doughnut',
	  data: {
	    labels: [<c:forEach items="${alimentos}" var="alimento">
	    <c:choose>
		    <c:when test="${alimento.id == 1}">'Maiz',</c:when>
		    <c:when test="${alimento.id == 2}">'Trigo',</c:when>
		    <c:when test="${alimento.id == 3}">'Centeno',</c:when>
		    <c:when test="${alimento.id == 4}">'Heno',</c:when>
		    <c:when test="${alimento.id == 5}">'Pasto',</c:when>
		    <c:when test="${alimento.id == 6}">'Avena',</c:when>
		    <c:when test="${alimento.id == 7}">'Cebada',</c:when>
	    </c:choose>	
		</c:forEach>],
	    datasets: [{
	      data: [<c:forEach items="${alimentos}" var="alimento">
			${alimento.cantidad},
			</c:forEach>],
	      backgroundColor: [
	    	  <c:forEach items="${alimentos}" var="alimento">
	    	    <c:choose>
	    		    <c:when test="${alimento.id == 1}">'#EFE081',</c:when>
	    		    <c:when test="${alimento.id == 2}">'#F0B66B',</c:when>
	    		    <c:when test="${alimento.id == 3}">'#0B7072',</c:when>
	    		    <c:when test="${alimento.id == 4}">'#89C7B6',</c:when>
	    		    <c:when test="${alimento.id == 5}">'#87C19B',</c:when>
	    		    <c:when test="${alimento.id == 6}">'#F1713F',</c:when>
	    		    <c:when test="${alimento.id == 7}">'#7998C9',</c:when>
	    	    </c:choose>	
	    		</c:forEach>
	      ],
	      
	      hoverBorderColor: "rgba(234, 236, 244, 1)",
	    }],
	  },
	  options: {
	    maintainAspectRatio: false,
	    tooltips: {
	      backgroundColor: "rgb(255,255,255)",
	      bodyFontColor: "#000000",
	      borderColor: '#dddfeb',
	      borderWidth: 1,
	      xPadding: 15,
	      yPadding: 15,
	      displayColors: false,
	      caretPadding: 10,
	    },
	    legend: {
	      display: true
	    },
	    cutoutPercentage: 80,
	  },
	});
	
	// Fin grafico alimentos 
	
</script>
</body>
</html>