<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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

#menuMap {
	float: right;
	background: #fff;
	padding: 11px;
	font-family: 'Open Sans', sans-serif;
}
.coordinates {
    background: rgba(0,0,0,0.5);
    color: #fff;
    position: relative;
    bottom: 100px;
    left: 0px;
    padding:5px 10px;
    margin: 0;
    font-size: 11px;
    line-height: 18px;
    border-radius: 3px;
    display: none;
	width:300px
}
</style>

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
					<c:if test="${not empty error}">
						<span class="text-danger" style="float: left;">${error}</span> <br>
					</c:if>
					<c:if test="${not empty mensaje}">
						<span class="text-success" style="float: left;">${mensaje}</span> <br>
					</c:if>
											
					<h1 class="h3 mb-2 text-gray-800">Cambio de coordenadas</h1>
					<p class="mb-4">A continuación se muestran los puntos centrales en donde se encuentran ubicados sus grupos de animales</p>
					
					<form name="form1" id="form1" method="POST" action="validarcambiodecoordenadas">
						<div class="form-group row">
							<div class="col-sm-4 mb-3 mb-sm-0">
								<p>
									<label>Longitud:</label>
									<input name="Longitud" onfocus="this.select()" type="text" class="form-control form-control-user inputNumero" id="Longitud" readonly>
								</p>
							</div>
							<div class="col-sm-4">
								<p>
									<label>Latitud:</label>
									<input name="Latitud" onfocus="this.select()" type="text" class="form-control form-control-user inputNumero" id="Latitud" readonly>
								</p>
							</div>
							<div class="col-sm-4">
								<label>Animales a cambiar de lugar:</label>
								<select path="tipoDeAnimal" id="tipoDeAnimal" name="tipoDeAnimal" required="true" class="form-control">
									<option value=""></option>
									<c:forEach items="${tiposDeAnimales}" var="tipos">
										<option value="${tipos.nombre}">${tipos.nombre}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						<button class="btn btn-primary btn-user btn-block text-white mb-3" type="submit">Validar cambio</button>
					</form>
					
					
					<div id="corral-seleccionado"></div>
					<div id="map" style="height: 500px; width: 100%;"></div>
					<pre id="coordinates" class="coordinates" style="display: block;"></pre>
					
				</div>
				<!-- /.container-fluid -->
				<div id="contenedorAsignacionCorral"></div>
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

		var marker, i;
		
		
		<c:forEach items="${tiposDeAnimales}" var="tipos">
			var imagen = '${tipos.nombre}'.toLowerCase();
			var tipo = imagen.toUpperCase();
		
			var icono = document.createElement('div');
			icono.classList.add("icon");
			icono.id = imagen;
			
	    	var popup = new mapboxgl.Popup({
	    		offset : 25
	    	}).setHTML('<div><div class="row no-gutters align-items-center"><div class="col mr-2">'+
	                 '<div class="h6 mb-0 text-gray-900" style="font-size: 12px;">'+ tipo +'</div> </div>' +
	                  '<div class="col-auto mt-2"><img class="img-profile" style="height:35px; width: 35px;" src="/SmartFarm/img/'+imagen+'.png"/></div></div></div>');
			
		    <c:choose>
				<c:when test="${tipos.nombre == 'CAPRINO'}">
					new mapboxgl.Marker(icono).setLngLat([ ${-ubicacionesCentrales.longitudCaprinoCentral}, ${-ubicacionesCentrales.latitudCaprinoCentral} ])
					.setPopup(popup).addTo(map);
				</c:when>
				<c:when test="${tipos.nombre == 'EQUINO'}">
					new mapboxgl.Marker(icono).setLngLat([ ${-ubicacionesCentrales.longitudEquinoCentral}, ${-ubicacionesCentrales.latitudEquinoCentral} ])
					.setPopup(popup).addTo(map);
				</c:when>
				<c:when test="${tipos.nombre == 'OVINO'}">
					new mapboxgl.Marker(icono).setLngLat([ ${-ubicacionesCentrales.longitudOvinoCentral}, ${-ubicacionesCentrales.latitudOvinoCentral} ])
					.setPopup(popup).addTo(map);
				</c:when>
				<c:when test="${tipos.nombre == 'PORCINO'}">
					new mapboxgl.Marker(icono).setLngLat([ ${-ubicacionesCentrales.longitudPorcinoCentral}, ${-ubicacionesCentrales.latitudPorcinoCentral} ])
					.setPopup(popup).addTo(map);
				</c:when>
				<c:when test="${tipos.nombre == 'VACUNO'}">
					new mapboxgl.Marker(icono).setLngLat([ ${-ubicacionesCentrales.longitudVacunoCentral}, ${-ubicacionesCentrales.latitudVacunoCentral} ])
					.setPopup(popup).addTo(map);
				</c:when>
			</c:choose>
		</c:forEach>
	</script>
	
	
	
	<script>
		var coordinates = document.getElementById('coordinates');
		
		map.on('mousemove', function (e) {
			var coords = e.lngLat
			
		 	// Print the coordinates of where the point had
		    // finished being dragged to on the map.
		    coordinates.style.display = 'block';
		    coordinates.innerHTML = 'Clic botón derecho para guardar coordenadas<br>Longitud: ' + coords.lng + '<br />Latitud: ' + coords.lat;	
				
		});
		
		map.on('contextmenu', function (e) {
			var coords = e.lngLat
			
		  // Print the coordinates of where the point had
		    // finished being dragged to on the map.
		    coordinates.style.display = 'block';
		    coordinates.innerHTML = 'Longitud: ' + coords.lng + '<br />Latitud: ' + coords.lat;	
			if(confirm('¿Desea guardar coordenadas?')==false) {return false} else {form1.Latitud.value = coords.lat; form1.Longitud.value = coords.lng;}
				
		});
	</script>
	<script src="<c:url value="/js/corrales.js"/>"></script>
	
</body>
</html>