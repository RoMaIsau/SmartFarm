<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- REQUERIDO PARA LOS GR�FICOS DE ORINA Y TEMPERATURA -->
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>

<!-- PRINCIPIO: REQUERIDO PARA EL FUNCIONAMIENTO DEL GR�FICO DE ORINA -->
<%/* SE INTENT� HACER CON UN "SWITCH" EN LUGAR DE "IF" PERO NO FUNCIONA */
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
String dataPoints;
String e = (String) request.getAttribute("enfermedadClase");

if(e == "" || e == null || e == "Fiebre Aftosa"){/* VALORES DE ORINA NORMALES */
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
<!-- FIN: REQUERIDO PARA EL FUNCIONAMIENTO DEL GR�FICO DE ORINA -->


<!-- PRINCIPIO: REQUERIDO PARA EL FUNCIONAMIENTO DEL GR�FICO DE TEMPERATURA -->
<%
Gson gsonObj2 = new Gson();
Map<Object,Object> map2 = null;
List<Map<Object,Object>> list2 = new ArrayList<Map<Object,Object>>();
String dataPoints2;
String e2 = (String) request.getAttribute("enfermedadClase");

if(e2 == "" || e2 == null || e2 == "Fiebre Aftosa"){/* TEMPERATURA NORMAL */
	map2 = new HashMap<Object,Object>(); map2.put("x", 1484209000000L); map2.put("y", new Integer[] {37,40}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1486887400000L); map2.put("y", new Integer[] {38,41}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1489306600000L); map2.put("y", new Integer[] {37,40}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1491985000000L); map2.put("y", new Integer[] {36,39}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1494577000000L); map2.put("y", new Integer[] {37,40}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1497255400000L); map2.put("y", new Integer[] {38,41}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1499847400000L); map2.put("y", new Integer[] {37,40}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1502525800000L); map2.put("y", new Integer[] {36,39}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1505204200000L); map2.put("y", new Integer[] {37,40}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1507796200000L); map2.put("y", new Integer[] {38,41}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1510474600000L); map2.put("y", new Integer[] {37,40}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1513066600000L); map2.put("y", new Integer[] {36,39}); list2.add(map2);
	dataPoints2 = gsonObj2.toJson(list2);
} else {/* TEMPERATURA ANORMAL */
	map2 = new HashMap<Object,Object>(); map2.put("x", 1484209000000L); map2.put("y", new Integer[] {47,50}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1486887400000L); map2.put("y", new Integer[] {48,51}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1489306600000L); map2.put("y", new Integer[] {47,50}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1491985000000L); map2.put("y", new Integer[] {46,49}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1494577000000L); map2.put("y", new Integer[] {47,50}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1497255400000L); map2.put("y", new Integer[] {48,51}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1499847400000L); map2.put("y", new Integer[] {47,50}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1502525800000L); map2.put("y", new Integer[] {46,49}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1505204200000L); map2.put("y", new Integer[] {47,50}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1507796200000L); map2.put("y", new Integer[] {48,51}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1510474600000L); map2.put("y", new Integer[] {47,50}); list2.add(map2);
	map2 = new HashMap<Object,Object>(); map2.put("x", 1513066600000L); map2.put("y", new Integer[] {46,49}); list2.add(map2);
	dataPoints2 = gsonObj2.toJson(list2);
}
%>
<!-- FIN: REQUERIDO PARA EL FUNCIONAMIENTO DEL GR�FICO DE TEMPERATURA -->

<!-- PRINCIPIO: REQUERIDO PARA EL FUNCIONAMIENTO DEL GR�FICO DE FRECUENCIA CARD�ACA -->
<%
Gson gsonObj3 = new Gson();
Map<Object,Object> map3 = null;
List<Map<Object,Object>> list3 = new ArrayList<Map<Object,Object>>();
String dataPoints3;
String e1 = (String) request.getAttribute("enfermedadClase");

if(e1 == "" || e1 == null || e1 == "Fiebre Aftosa"){/* FRECUENCIA NORMAL */
	map3 = new HashMap<Object,Object>(); map3.put("label", "Frecuencia por segundo"); map3.put("y", 25); list3.add(map3);
	dataPoints3 = gsonObj.toJson(list3);
} else {/* FRECUENCIA ANORMAL */
	map3 = new HashMap<Object,Object>(); map3.put("label", "Frecuencia por segundo"); map3.put("y", 35); list3.add(map3);
	dataPoints3 = gsonObj.toJson(list3);
}
%>
<!-- FIN: REQUERIDO PARA EL FUNCIONAMIENTO DEL GR�FICO DE FRECUENCIA CARD�ACA -->
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
					
					<!-- Ritmo Card�aco -->
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
	
	
	<!-- ========== GR�FICOS ========== -->
	<script type="text/javascript">
		window.onload = function() {
			function getRndInteger(min, max) {
				return Math.floor(Math.random() * (max - min + 1) ) + min;
			}
			
			/* ===== RITMO CARD�ACO ===== */
			<c:choose>
				<c:when test="${enfermedad == null or enfermedad == '' or enfermedad == 'OTRA ENFERMEDAD'}">/* RITMO CARD�ACO NORMAL */
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
							text: "Ritmo card�aco"
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
				<c:when test="${enfermedad == 'Fiebre Aftosa'}">/* RITMO CARD�ACO ACELERADO */
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
							text: "Ritmo card�aco"
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
			<c:choose>
				<c:when test="${enfermedad == null or enfermedad == '' or enfermedad == 'Fiebre Aftosa'}">/* VALORES NORMALES */
					var valoresUrinarios = new CanvasJS.Chart("valoresUrinarios", {
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
				<c:when test="${enfermedad == 'OTRA ENFERMEDAD'}">/* VALORES ANORMALES */
					var valoresUrinarios = new CanvasJS.Chart("valoresUrinarios", {
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
			<c:choose>
				<c:when test="${enfermedad == null or enfermedad == '' or enfermedad == 'Fiebre Aftosa'}">/* VALORES NORMALES */
					var temperaturaCorporal = new CanvasJS.Chart("temperaturaCorporal", {
						animationEnabled: false,
						exportEnabled: false,
						title: {
							fontFamily: "Nunito",
							fontStyle: "bold",
							text: "Temperatura corporal"
						},
						subtitles: [{
							text: "Promedio por d�a"
						}],
						axisY: {
							title: "Temperatura (�C)",
							suffix: " �C"
						},
						axisX: {
							valueFormatString: "M"
						},
						data: [{
							type: "rangeColumn",
							xValueFormatString: "MMMM",
							xValueType: "dateTime",
							yValueFormatString: "#,##0.## �C",
							indexLabel: "{y[#index]}",
							toolTipContent: " <span style=\"color:#4F81BC\">{x}</span><br><b>Min:</b> {y[0]}<br><b>Max:</b> {y[1]}",
							dataPoints: <%out.print(dataPoints2);%>
						}]
					});
					temperaturaCorporal.render();
				</c:when>
				<c:when test="${enfermedad == 'OTRA ENFERMEDAD'}">/* VALORES ANORMALES */
					var temperaturaCorporal = new CanvasJS.Chart("temperaturaCorporal", {
						animationEnabled: false,
						exportEnabled: false,
						title: {
							fontFamily: "Nunito",
							fontStyle: "bold",
							text: "Temperatura corporal"
						},
						subtitles: [{
							text: "Promedio por d�a"
						}],
						axisY: {
							title: "Temperatura (�C)",
							suffix: " �C"
						},
						axisX: {
							valueFormatString: "M"
						},
						data: [{
							type: "rangeColumn",
							color: "red",
							xValueFormatString: "MMMM",
							xValueType: "dateTime",
							yValueFormatString: "#,##0.## �C",
							indexLabel: "{y[#index]}",
							toolTipContent: " <span style=\"color:#4F81BC\">{x}</span><br><b>Min:</b> {y[0]}<br><b>Max:</b> {y[1]}",
							dataPoints: <%out.print(dataPoints2);%>
						}]
					});
					temperaturaCorporal.render();
				</c:when>
			</c:choose>
			
			
			/* ===== FRECUENCIA RESPIRATORIA ===== */
			<c:choose>
				<c:when test="${enfermedad == null or enfermedad == '' or enfermedad == 'Fiebre Aftosa'}">/* VALORES NORMALES */
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
				<c:when test="${enfermedad == 'OTRA ENFERMEDAD'}">/* VALORES ANORMALES */
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
						frecuenciaRespiratoria.options.subtitles[0].text = "promedio estable: 25";
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