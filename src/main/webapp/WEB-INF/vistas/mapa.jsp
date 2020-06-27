<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../../parts/meta.jsp"%>
<script src='https://api.mapbox.com/mapbox-gl-js/v1.10.1/mapbox-gl.js'></script>
<link href='https://api.mapbox.com/mapbox-gl-js/v1.10.1/mapbox-gl.css'
	rel='stylesheet' />
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

					<h1 class="h3 mb-2 text-gray-800">Mapa</h1>
					<div id="map" style="height: 500px; width: 100%;"></div>
					<div class="card shadow mb-4 mt-2">
						<div class="card-header py-3 mx-0 row justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary">Cantidad de
								animales ${fn:length(lista)}</h6>
						</div>

						<div class="card-body">
							<div class="table-responsive">
								<table class="table text-center" id="tabla" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>ID Animal</th>
											<th hidden>Latitud</th>
											<th hidden>Longitud</th>
											<th>Tipo</th>
											<th>Raza</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${lista}" var="lista">
											<td>${lista.animal.id}</td>
											<td hidden>${lista.ultimaUbicacion.longitud}</td>
											<td hidden>${lista.ultimaUbicacion.latitud}</td>
											<td>${lista.animal.tipo.nombre}</td>
											<td>${lista.animal.raza.nombre}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
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
	<script>
		var map;
		
		mapboxgl.accessToken = 'pk.eyJ1IjoiZXplMjEiLCJhIjoiY2tiODBxcDYzMGIxYTMwcWFtZ2pncmNjdCJ9.XzKHl3aCknUpCwDcMSMlJg';
		map = new mapboxgl.Map({
			container : "map",
			style : "mapbox://styles/mapbox/satellite-streets-v11",
			zoom : 14,
			center : [ -59.241913, -35.276381 ]
		});
		
		var marker, i;
		
		<c:forEach items="${lista}" var="lista">
			var imagen = '${lista.animal.tipo.nombre}'.toLowerCase();
			var tipo = imagen.toUpperCase();
			var raza = '${lista.animal.raza.nombre}';
			
		
			var icono = document.createElement('div');
			icono.classList.add("icon");
			icono.id = imagen;
			
	    	var popup = new mapboxgl.Popup({
	    		offset : 25
	    	}).setHTML('<div><div class="row no-gutters align-items-center"><div class="col mr-2">'+
					'<div class="text-xs font-weight-bold text-success text-uppercase mb-1" style="font-size: 15px;">'+${lista.animal.id}+'</div>'+
	                 '<div class="h6 mb-0 text-gray-900" style="font-size: 12px;">'+ tipo +' - '+ raza +'</div> ' +
	                 '<div class="mt-2 text-center"><a href="verAnimal?id='+${lista.animal.id}+'" style="font-size: 11px;" class="badge badge-success p-1">Ver animal</a></div></div> ' +
	                  '<div class="col-auto mt-2"><img class="img-profile" style="height:35px; width: 35px;" src="/SmartFarm/img/'+imagen+'.png"/></div></div> </div>');
		
	    	new mapboxgl.Marker(icono).setLngLat([ ${lista.ultimaUbicacion.longitud}, ${lista.ultimaUbicacion.latitud} ])
			.setPopup(popup).addTo(map);
	    	
		</c:forEach>
	
	
	</script>
</body>
</html>