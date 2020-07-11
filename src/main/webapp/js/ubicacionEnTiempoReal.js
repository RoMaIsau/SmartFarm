$(document).ready(function(){
    setInterval(buscarPosiciones, 10000);
});

var markers = [];
function buscarPosiciones() {
	$.ajax({
		type: 'get',
		url: contextPath + '/ubicaciones/ultimas',
		contentType: 'application/json',
		success: function(posiciones) {
			for(i = 0; i < posiciones.length; i++) {

				var posicion = posiciones[i];
				var marker = buscarMarkerPorId(posicion.id);
				if (marker !== undefined) {
					marker.setLngLat([posicion.longitud, posicion.latitud])
				} else {
					var icono = document.createElement('div');
					icono.classList.add("icon");
					icono.id = posicion.tipoAnimal.toLowerCase();
					var markerNuevo = new mapboxgl.Marker(icono)
								.setLngLat([posicion.longitud, posicion.latitud])
								.addTo(map);
					markerNuevo.id = posicion.id;
					markers.push(markerNuevo);
				}
			}
		},
		complete:function(data){
   			setTimeout(buscarPosiciones, 10000);
   		}
	});
}

function buscarMarkerPorId(id) {
	var markerEncontrado = false;
	var iteracion = 0;
	var markerBuscado;
	while(markerEncontrado == false && iteracion < markers.length) {
		var marker = markers[iteracion];
		if (marker.id === id) {
			markerEncontrado = true;
			markerBuscado = marker;
		}
		iteracion++;
	}
	return markerBuscado;
}