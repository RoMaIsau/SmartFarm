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
				<!-- ================================================================================== -->
					
					<div id="chartContainer" style="height: 370px; width: 100%;"></div>
					
				<!-- ================================================================================== -->
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
	
	<!-- Gráfico de ritmo cardíaco del animal -->
	<script type="text/javascript">
		window.onload = function() {
			function getRndInteger(min, max) {
				return Math.floor(Math.random() * (max - min + 1) ) + min;
				}
			
			var dataPoints = [];
			var y = 80;
			for(var i = 0; i < 12; i++){
				y = 80
				y += getRndInteger(-3, 3);
				dataPoints.push({x: i, y: y});
			}
			 
			var chart = new CanvasJS.Chart("chartContainer", {
				title: {
					text: "Ritmo cardíaco del animal"
				},
				axisX:{
					title: "Tiempo en segundos"
				},
				axisY:{
					includeZero: false,
					suffix: " PPM"
				},
				data: [{
					type: "line",
					color: "green",
					yValueFormatString: "#,##0.0#",
					toolTipContent: "{y} PPM",
					dataPoints: dataPoints
				}]
			});
			chart.render();
			 
			var updateInterval = 1000;
			setInterval(function () { updateChart() }, updateInterval);
			 
			var xValue = dataPoints.length;
			var yValue = dataPoints[dataPoints.length - 1].y;
			 
			function updateChart() {
				yValue = 80
				yValue += getRndInteger(-3, 3);
				dataPoints.push({ x: xValue, y: yValue });
				xValue++;
				chart.render();
			};
		}
	</script>
	
	<!-- Logout Modal-->
	<%@ include file="../../parts/modalCerrarSesion.jsp" %>
	
	<!-- Bootstrap core JavaScript-->
	<%@ include file="../../parts/scripts.jsp" %>
	
</body>
</html>