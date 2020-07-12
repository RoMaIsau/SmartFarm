(function($) {
	"use strict";
	jQuery(window).on("load", function() {
		setInterval(buscarPosiciones, 5000);
		setInterval(buscarNotificaciones, 10000);

		$(document).on("click", ".btn-notificacion-vista", function(evento) {
			var id = $(this).attr('id').split("-")[1];
			var notificacion = {
				"id": id
			};

			$.ajax({
				type:'post',
				url: contextPath + '/ubicaciones/notificacionVista',
				data: JSON.stringify(notificacion),
				contentType: 'application/json',
				success: function(respuesta) {
					console.log(respuesta);
					buscarNotificaciones();
				}
			})
		});
	});
})(jQuery);

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
			setTimeout(buscarPosiciones, 5000);
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

function buscarNotificaciones() {
	$.ajax({
		type: 'get',
		url: contextPath + '/ubicaciones/notificaciones',
		success: function(notificaciones) {
			$('.toast').toast('hide');
			$('#contenedorNotificaciones').html(notificaciones);
			$('.toast').toast('show');
		},
		complete:function(data){
			setTimeout(buscarNotificaciones, 15000);
		}
	});
}