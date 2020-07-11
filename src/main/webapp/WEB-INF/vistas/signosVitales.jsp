<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- REQUERIDO PARA LOS GRÁFICOS DE ORINA Y TEMPERATURA -->
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>

<!-- PRINCIPIO: REQUERIDO PARA EL FUNCIONAMIENTO DEL GRÁFICO DE ORINA -->
<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

map = new HashMap<Object,Object>(); map.put("label", "Densidad"); map.put("y", 20.7); list.add(map); /*  map.put("exploded", true); */
map = new HashMap<Object,Object>(); map.put("label", "pH"); map.put("y", 20); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Proteinas"); map.put("y", 20); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Setonas"); map.put("y", 20); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Sangre oculta"); map.put("y", 0.3); list.add(map);

String dataPoints = gsonObj.toJson(list);
%>
<!-- FIN: REQUERIDO PARA EL FUNCIONAMIENTO DEL GRÁFICO DE ORINA -->


<!-- PRINCIPIO: REQUERIDO PARA EL FUNCIONAMIENTO DEL GRÁFICO DE TEMPERATURA -->
<%
Gson gsonObj2 = new Gson();
Map<Object,Object> map2 = null;
List<Map<Object,Object>> list2 = new ArrayList<Map<Object,Object>>();

map2 = new HashMap<Object,Object>(); map2.put("x", 1483209000000L); map2.put("y", new Integer[] {37,40}); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 1485887400000L); map2.put("y", new Integer[] {38,41}); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 1488306600000L); map2.put("y", new Integer[] {37,40}); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 1490985000000L); map2.put("y", new Integer[] {36,39}); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 1493577000000L); map2.put("y", new Integer[] {37,40}); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 1496255400000L); map2.put("y", new Integer[] {38,41}); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 1498847400000L); map2.put("y", new Integer[] {37,40}); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 1501525800000L); map2.put("y", new Integer[] {36,39}); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 1504204200000L); map2.put("y", new Integer[] {37,40}); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 1506796200000L); map2.put("y", new Integer[] {38,41}); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 1509474600000L); map2.put("y", new Integer[] {37,40}); list2.add(map2);
map2 = new HashMap<Object,Object>(); map2.put("x", 1512066600000L); map2.put("y", new Integer[] {36,39}); list2.add(map2);

String dataPoints2 = gsonObj2.toJson(list2);
%>
<!-- FIN: REQUERIDO PARA EL FUNCIONAMIENTO DEL GRÁFICO DE TEMPERATURA -->

<!-- PRINCIPIO: REQUERIDO PARA EL FUNCIONAMIENTO DEL GRÁFICO DE TEMPERATURA -->
<%
Gson gsonObj3 = new Gson();
Map<Object,Object> map3 = null;
List<Map<Object,Object>> list3 = new ArrayList<Map<Object,Object>>();

map3 = new HashMap<Object,Object>(); map3.put("label", "Frecuencia por segundo"); map3.put("y", 25); list3.add(map3);

String dataPoints3 = gsonObj.toJson(list3);
%>
<!-- FIN: REQUERIDO PARA EL FUNCIONAMIENTO DEL GRÁFICO DE TEMPERATURA -->
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
					
					<!-- Ritmo Cardíaco -->
					<div class="row">
						<div class="col-md-12 col-sm-12 col-12 mb-4">
							<div id="RitmoCardiaco"></div>
							<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
						</div>
					</div>
					<div class="row">
						<div class="col-md-1 col-sm-1 col-1 mb-4 mt-4"><br>
							<i class="fas fa-heartbeat"></i>
						</div>
						<div class="col-md-1 col-sm-1 col-1 mb-4 mt-4"><br>
							<span>PPM</span>
						</div>
						<div class="col-md-2 col-sm-2 col-5 mb-4 mt-5">
							<input class="form-control" type="text" id="pulso" name="pulso" value=""></input>
						</div>
					</div>
					
					
					<!-- Valores urinarios y Temperatura corporal -->
					<div class="row">
						<!-- Valores urinarios -->
						<div class="col-md-6 col-sm-12 col-12 mb-4">
							<div id="valoresUrinarios"></div>
						</div>
						
						<div class="hidden-md-down">
							<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>	
						</div>
						
						<!-- Frecuencia Respiratoria -->
						<div class="col-md-6 col-sm-12 col-12 mb-4">
							<div id="frecuenciaRespiratoria"></div>
						</div>
						
						<div class="hidden-md-down">
							<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
						</div>
						
						<!-- Temperatura corporal -->
						<div class="col-md-12 col-sm-12 col-12 mb-4">
							<div id="temperaturaCorporal"></div>
						</div>
						
						<div class="hidden-md-down">
							<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
						</div>
					</div>
					
					
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
	
	
	<!-- ========== GRÁFICOS ========== -->
	<script type="text/javascript">
		window.onload = function() {
			/* ===== RITMO CARDÍACO ===== */
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
			 
			var RitmoCardiaco = new CanvasJS.Chart("RitmoCardiaco", {
				title: {
					text: "Ritmo cardíaco"
				},
				axisX:{
					title: "Tiempo en segundos"
				},
				axisY:{
					includeZero: false,
					suffix: " PPM"
				},
				data: [{
					type: "spline",
					color: "green",
					yValueFormatString: "#,##0.0#",
					toolTipContent: "{y} PPM",
					dataPoints: dataPoints
				}]
			});
			RitmoCardiaco.render();
			 
			var updateInterval = 1000;
			setInterval(function () { updateChart() }, updateInterval);
			 
			var xValue = dataPoints.length;
			var yValue = dataPoints[dataPoints.length - 1].y;
			
			function updateChart() {
				yValue = 80
				yValue += getRndInteger(-3, 3);
				dataPoints.push({ x: xValue, y: yValue });
				xValue++;
				RitmoCardiaco.render();
				cambiarPulso(yValue);
			};
			
			function cambiarPulso(valor) {
				var pulso = document.getElementById("pulso");
				pulso.value = valor;
			};
			
			
			/* ===== VALORES URINARIOS ===== */
			var valoresUrinarios = new CanvasJS.Chart("valoresUrinarios", {
				theme: "light2",
				animationEnabled: true,
				exportFileName: "Valores urinarios",
				exportEnabled: true,
				title:{
					text: "Valores urinarios"
				},
				data: [{
					type: "pie",
					showInLegend: true,
					legendText: "{label}",
					toolTipContent: "{label}: <strong>{y}%</strong>",
					indexLabel: "{label} {y}%",
					dataPoints : <%out.print(dataPoints);%>
				}]
			});
			valoresUrinarios.render();
			
			
			/* ===== TEMPERATURA ===== */
			var temperaturaCorporal = new CanvasJS.Chart("temperaturaCorporal", {
				animationEnabled: true,
				exportEnabled: true,
				title: {
					text: "Temperatura corporal"
				},
				subtitles: [{
					text: "Promedio por día"
				}],
				axisY: {
					title: "Temperatura (°C)",
					suffix: " °C"
				},
				axisX: {
					valueFormatString: "M"
				},
				data: [{
					type: "rangeColumn",
					xValueFormatString: "MMMM",
					xValueType: "dateTime",
					yValueFormatString: "#,##0.## °C",
					indexLabel: "{y[#index]}",
					toolTipContent: " <span style=\"color:#4F81BC\">{x}</span><br><b>Min:</b> {y[0]}<br><b>Max:</b> {y[1]}",
					dataPoints: <%out.print(dataPoints2);%>
				}]
			});
			temperaturaCorporal.render();
			
			
			/* ===== FRECUENCIA RESPIRATORIA ===== */
			var frecuenciaRespiratoria = new CanvasJS.Chart("frecuenciaRespiratoria", {
				title: {
					text: "Frecuencia respiratoria"
				},
				subtitles:[{
					fontSize: 16,
					text: null
				}],
				axisY:{
					title: ""
				},
				data: [{
					type: "column",
					yValueFormatString: "#,##0",
					indexLabel: "{y}",
					dataPoints : <%out.print(dataPoints3);%>
				}]
			});
			
			function updateChart3() {
				var performance, deltaY, yVal;
				var dps = frecuenciaRespiratoria.options.data[0].dataPoints;
				for (var i = 0; i < dps.length; i++) {
					deltaY = Math.round(2 + Math.random() * (-2 - 2));
					yVal = deltaY + dps[i].y > 0 ? dps[i].y + deltaY : 0;
					dps[i].y = getRndInteger(23, 27);
				}
				frecuenciaRespiratoria.options.subtitles[0].text = "promedio estable: 25";
				frecuenciaRespiratoria.render();
			};
			updateChart3();
			setInterval(function () { updateChart3() }, 1000);
		};
	</script>
	
	
	
	
	<!-- Logout Modal-->
	<%@ include file="../../parts/modalCerrarSesion.jsp" %>
	
	<!-- Bootstrap core JavaScript-->
	<%@ include file="../../parts/scripts.jsp" %>
	
</body>
</html>