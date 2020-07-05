<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<%@ include file="../../parts/meta.jsp"%>
<script src='https://api.mapbox.com/mapbox-gl-js/v1.10.1/mapbox-gl.js'></script>
<link href='https://api.mapbox.com/mapbox-gl-js/v1.10.1/mapbox-gl.css'
	rel='stylesheet' />
<script src="https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-draw/v1.0.9/mapbox-gl-draw.js"></script>
 <link rel="stylesheet" href="https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-draw/v1.0.9/mapbox-gl-draw.css"
    type="text/css" />
</head>
<style>
.icon {
	background-size: cover;
	width: 40px;
	height: 40px;
	border-radius: 50%;
	cursor: pointer;
}

#vacuno {
	background-image: url('img/vacuno.png');
}

#ovino {
	background-image: url('img/ovino.png');
}

#caprino {
	background-image: url('img/caprino.png');
}

#equino {
	background-image: url('img/equino.png');
}

#porcino {
	background-image: url('img/porcino.png');
}

.mapboxgl-popup {
	max-width: 200px;
}

.mapboxgl-popup-content {
	border-left: .25rem solid #1cc88a !important;
}
#corral-seleccionado {
  position: absolute;
  z-index: 1;  
  background-color: rgba(255, 255, 255, 0.9);
  padding: 5px;
  text-align: left;
}
</style>

<body id="page-top">
	<script type="text/javascript">
		var contextPath = '<c:out value="${contextPath}"/>';
	</script>
	<div id="wrapper">
		<%@ include file="../../parts/sidebar.jsp"%>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<%@ include file="../../parts/topbar.jsp"%>
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-2 col-xs-6 col-sm-6 mb-4">
							<h1 class="h3 mb-2 text-gray-800">Mapa</h1>
						</div>
					</div>
					<div id="corral-seleccionado"></div>
					<div id="map" style="height: 500px; width: 100%;"></div>
				</div>
				<div id="contenedorAsignacionCorral"></div>
			</div>
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Smart Farm 2020</span>
					</div>
				</div>
			</footer>
		</div>
	</div>
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<%@ include file="../../parts/modalCerrarSesion.jsp"%>

	<!-- Bootstrap core JavaScript-->

	<%@ include file="../../parts/scripts.jsp"%>
	<script>
		var map;
		
		mapboxgl.accessToken = 'pk.eyJ1IjoiZXplMjEiLCJhIjoiY2tiODBxcDYzMGIxYTMwcWFtZ2pncmNjdCJ9.XzKHl3aCknUpCwDcMSMlJg';
		map = new mapboxgl.Map({
			container : "map",
			style : "mapbox://styles/mapbox/satellite-streets-v11",
			zoom : 14,
			center : [ -59.241913, -35.276381 ]
		});
		var draw = new MapboxDraw({
		      displayControlsDefault : false,
		      controls : {
		        polygon : true,
		        trash: true
		      }
		    });

		map.on('load', function(){
			map.addControl(draw);
			dibujarCorrales();
		});
	</script>
	<script src="<c:url value="/js/corrales.js"/>"></script>
</body>
</html>