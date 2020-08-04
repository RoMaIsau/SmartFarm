<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- REQUERIDO PARA LOS GRÁFICOS DE ORINA Y TEMPERATURA -->
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>

<!-- PRINCIPIO: REQUERIDO PARA EL FUNCIONAMIENTO DEL GRÁFICO DE ORINA -->
<%/* SE INTENTÓ HACER CON UN "SWITCH" EN LUGAR DE "IF" PERO NO FUNCIONA */
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
String dataPoints;
String e = (String) request.getAttribute("enfermedadClase");

if(e == "" || e == null || e == "Fiebre Aftosa" || e == "Miocardiopatia congenita" || e == "Rinotraqueitis infecciosa"){/* VALORES DE ORINA NORMALES */
	map = new HashMap<Object,Object>(); map.put("label", "Densidad"); map.put("y", 18.7); list.add(map);/*  map.put("exploded", true); */
	map = new HashMap<Object,Object>(); map.put("label", "pH"); map.put("y", 27); list.add(map);
	map = new HashMap<Object,Object>(); map.put("label", "Proteinas"); map.put("y", 27); list.add(map);
	map = new HashMap<Object,Object>(); map.put("label", "Setonas"); map.put("y", 27); list.add(map);
	map = new HashMap<Object,Object>(); map.put("label", "Sangre oculta"); map.put("y", 0.3); list.add(map);
	dataPoints = gsonObj.toJson(list);
}else{/* VALORES DE ORINA ANORMALES */
	map = new HashMap<Object,Object>(); map.put("label", "Densidad"); map.put("y", 20.7); list.add(map);/*  map.put("exploded", true); */
	map = new HashMap<Object,Object>(); map.put("label", "pH"); map.put("y", 40); list.add(map);
	map = new HashMap<Object,Object>(); map.put("label", "Proteinas"); map.put("y", 20); list.add(map);
	map = new HashMap<Object,Object>(); map.put("label", "Setonas"); map.put("y", 18); list.add(map);
	map = new HashMap<Object,Object>(); map.put("label", "Sangre oculta"); map.put("y", 1.3); list.add(map);
	dataPoints = gsonObj.toJson(list);
}
%>
<!-- FIN: REQUERIDO PARA EL FUNCIONAMIENTO DEL GRÁFICO DE ORINA -->


<!-- PRINCIPIO: REQUERIDO PARA EL FUNCIONAMIENTO DEL GRÁFICO DE TEMPERATURA -->
<%
Gson gsonObj2 = new Gson();
Map<Object,Object> map2 = null;
List<Map<Object,Object>> list2 = new ArrayList<Map<Object,Object>>();
String dataPoints2;

if(e == "" || e == null || e == "Fiebre Aftosa" || e == "Miocardiopatia congenita"){/* VALORES NORMALES */
	map2 = new HashMap<Object,Object>(); map2.put("label", "1"); map2.put("y", 37.7); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("label", "2"); map2.put("y", 38.0); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("label", "3"); map2.put("y", 38.1); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("label", "4"); map2.put("y", 36.9); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("label", "5"); map2.put("y", 37.5); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("label", "6"); map2.put("y", 37.3); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("label", "7"); map2.put("y", 36.0); list2.add(map2);
}else{/* VALORES ANORMALES */
	map2 = new HashMap<Object,Object>(); map2.put("label", "1"); map2.put("y", 47.7); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("label", "2"); map2.put("y", 48.0); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("label", "3"); map2.put("y", 48.1); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("label", "4"); map2.put("y", 46.9); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("label", "5"); map2.put("y", 47.5); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("label", "6"); map2.put("y", 47.3); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("label", "7"); map2.put("y", 46.0); list2.add(map2);
}
dataPoints2 = gsonObj.toJson(list2);
%>
<!-- FIN: REQUERIDO PARA EL FUNCIONAMIENTO DEL GRÁFICO DE TEMPERATURA -->

<!-- PRINCIPIO: REQUERIDO PARA EL FUNCIONAMIENTO DEL GRÁFICO DE FRECUENCIA CARDÍACA -->
<%
Gson gsonObj3 = new Gson();
Map<Object,Object> map3 = null;
List<Map<Object,Object>> list3 = new ArrayList<Map<Object,Object>>();
String dataPoints3;
String e1 = (String) request.getAttribute("enfermedadClase");

if(e1 == "" || e1 == null || e1 == "Leptospirosis"){/* FRECUENCIA NORMAL */
	map3 = new HashMap<Object,Object>(); map3.put("label", "Frecuencia por segundo"); map3.put("y", 25); list3.add(map3);
	dataPoints3 = gsonObj.toJson(list3);
} else {/* FRECUENCIA ANORMAL */
	map3 = new HashMap<Object,Object>(); map3.put("label", "Frecuencia por segundo"); map3.put("y", 35); list3.add(map3);
	dataPoints3 = gsonObj.toJson(list3);
}
%>
<!-- FIN: REQUERIDO PARA EL FUNCIONAMIENTO DEL GRÁFICO DE FRECUENCIA CARDÍACA -->
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
					<c:if test="${enfermedadClase == ''}">
						<div class="d-flex justify-content-center">
							<h1 class="h3 mb-3 text-gray-800">En este momento no se registran enfermedades en el animal</h1>
						</div>
					</c:if>
					<c:if test="${enfermedadClase != ''}">
						<div class="d-flex justify-content-center">
							<h1 class="h3 mb-3 text-gray-800">El animal tiene "<c:out value="${enfermedadClase}" />"</h1>
						</div>
					</c:if>
					
					<!-- Ritmo cardíaco y frecuencia respiratoria -->
					<!-- PPM -->
					<div class="row">
						<div class="col-xl-1 offset-xl-3 col-md-1 offset-md-2 col-sm-2 offset-sm-3 col-2 offset-3 mb-4">
							<i class="fas fa-heartbeat"></i>
						</div>
						<div class="col-xl-1 col-md-1 offset-md-1 col-sm-1 col-2 mb-4">
							<span>PPM</span>
						</div>
						<div class="col-xl-1 col-md-1 col-sm-1 offset-sm-1 col-4 mb-4">
							<input class="form-control" style="height: 30px; width: 45px;" type="text" id="pulso" name="pulso" value=""></input>
						</div>
					</div>
					
					<!-- Ritmo cardíaco -->
					<div class="row">
						<div class="col-md-9 col-sm-12 col-12 mb-4 shadow">
							<div id="RitmoCardiaco"></div>
						</div>
						
						<div class="hidden-md-down">
							<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
						</div>
						
						<!-- Frecuencia respiratoria -->
						<div class="col-md-3 col-sm-12 col-12 mb-4 shadow">
							<div id="frecuenciaRespiratoria"></div>
						</div>
						
						<div class="hidden-md-down">
							<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
						</div>
					</div>
					
					<!-- Temperatura corporal y valores urinarios -->
					<div class="row">
						<!-- Temperatura corporal -->
						<div class="col-md-7 col-sm-12 col-12 mb-4 shadow">
							<div id="temperaturaCorporal"></div>
						</div>
						
						<div class="hidden-md-down">
							<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>	
						</div>
						
						<!-- Valores urinarios -->
						<div class="col-md-5 col-sm-12 col-12 mb-4 shadow">
							<div id="valoresUrinarios"></div>
						</div>
						
						<div class="hidden-md-down">
							<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
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
			function getRndInteger(min, max) {
				return Math.floor(Math.random() * (max - min + 1) ) + min;
			}
			
			/* ===== RITMO CARDÍACO ===== */
			<c:choose>
				<c:when test="${cardio1 == true}">/* RITMO CARDÍACO NORMAL */
					var dataPoints = [];
					var y = 80;
					for(var i = 0; i < 12; i++){
						y = 80
						y += getRndInteger(-3, 3);
						dataPoints.push({x: i, y: y});
					}
					 
					var RitmoCardiaco = new CanvasJS.Chart("RitmoCardiaco", {
						title: {
							fontFamily: "Nunito",
							fontStyle: "bold",
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
				</c:when>
				<c:when test="${cardio1 == false}">/* RITMO CARDÍACO ACELERADO */
					var dataPoints = [];
					var y = 95;
					for(var i = 0; i < 12; i++){
						y = 95
						y += getRndInteger(-3, 3);
						dataPoints.push({x: i, y: y});
					}
					 
					var RitmoCardiaco = new CanvasJS.Chart("RitmoCardiaco", {
						title: {
							fontFamily: "Nunito",
							fontStyle: "bold",
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
							color: "red",
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
						yValue = 95
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
				</c:when>
			</c:choose>
		
			
			
			
			/* ===== VALORES URINARIOS ===== */
			CanvasJS.addColorSet("tortaNormal", ["#1ed1c2", "#16afad", "#72ca47", "#0d8800", "#1ed177"]);
			CanvasJS.addColorSet("tortaAnormal", ["#ff0000", "#c60000", "#a10000", "#7f0000", "#620000"]);
			<c:choose>
				<c:when test="${orina1 == true}">/* VALORES NORMALES */
					var valoresUrinarios = new CanvasJS.Chart("valoresUrinarios", {
						colorSet: "tortaNormal",
						theme: "light2",
						animationEnabled: false,
						exportFileName: "Valores urinarios",
						exportEnabled: false,
						title:{
							fontFamily: "Nunito",
							fontStyle: "normal",
							text: "Valores urinarios (normales)"
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
				</c:when>
				<c:when test="${orina1 == false}">/* VALORES ANORMALES */
					var valoresUrinarios = new CanvasJS.Chart("valoresUrinarios", {
						colorSet: "tortaAnormal",
						theme: "light1",
						animationEnabled: false,
						exportFileName: "Valores urinarios",
						exportEnabled: false,
						title:{
							fontFamily: "Nunito",
							fontStyle: "normal",
							text: "Valores urinarios (anormales)"
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
				</c:when>
			</c:choose>
			
			
			
			
			/* ===== TEMPERATURA ===== */
			CanvasJS.addColorSet("sombraDeVerdes", ["#008080", "#2E8B57", "#3CB371",  "#90EE90"]);
			CanvasJS.addColorSet("sombraDeRojo", ["#fe0000", "#a10000", "#620000",  "#a10000"]);
			
			<c:choose>
				<c:when test="${temperatura1 == true}">/* VALORES NORMALES */
					var graficoTemp = new CanvasJS.Chart("temperaturaCorporal", {
						colorSet: "sombraDeVerdes",
						title: {
							fontFamily: "Nunito",
							fontStyle: "bold",
							text: "Temperatura corporal"
						},
						axisX: {
							title: "Promedio en los últimos 7 días"
						},
						axisY: {
							title: "Temperatura (°C)"
						},
						data: [{
							type: "column",
							yValueFormatString: "#,##0.0# °C",
							dataPoints: <%out.print(dataPoints2);%>
						}]
					});
					graficoTemp.render();
				</c:when>
				<c:when test="${temperatura1 == false}">/* VALORES ANORMALES */
					var graficoTemp = new CanvasJS.Chart("temperaturaCorporal", {
						colorSet: "sombraDeRojo",
						title: {
							fontFamily: "Nunito",
							fontStyle: "bold",
							text: "Temperatura corporal"
						},
						axisX: {
							title: "Promedio en los últimos 7 días"
						},
						axisY: {
							title: "Temperatura (°C)"
						},
						data: [{
							type: "column",
							yValueFormatString: "#,##0.0# °C",
							dataPoints: <%out.print(dataPoints2);%>
						}]
					});
					graficoTemp.render();
				</c:when>
			</c:choose>
			
			
			/* ===== FRECUENCIA RESPIRATORIA ===== */
			CanvasJS.addColorSet("respiracionNormal", ["#008080"]);
			<c:choose>
				<c:when test="${respiracion1 == true}">/* VALORES NORMALES */
					var frecuenciaRespiratoria = new CanvasJS.Chart("frecuenciaRespiratoria", {
						colorSet: "respiracionNormal",
						title: {
							fontFamily: "Nunito",
							fontStyle: "bold",
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
				</c:when>
				<c:when test="${respiracion1 == false}">/* VALORES ANORMALES */
					var frecuenciaRespiratoria = new CanvasJS.Chart("frecuenciaRespiratoria", {
						title: {
							fontFamily: "Nunito",
							fontStyle: "bold",
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
							color: "red",
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
							dps[i].y = getRndInteger(33, 37);
						}
						frecuenciaRespiratoria.options.subtitles[0].text = "promedio estable: 35";
						frecuenciaRespiratoria.render();
					};
					updateChart3();
					setInterval(function () { updateChart3() }, 1000);
				</c:when>
			</c:choose>
		};
	</script>
	
	
	
	
	<!-- Logout Modal-->
	<%@ include file="../../parts/modalCerrarSesion.jsp" %>
	
	<!-- Bootstrap core JavaScript-->
	<%@ include file="../../parts/scripts.jsp" %>
	
</body>
</html>