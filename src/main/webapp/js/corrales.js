map.on('draw.selectionchange', corralSeleccionado);
map.on('draw.delete', eliminarCorral);

var corralSeleccionado;

function dibujarCorrales() {
	draw.deleteAll();
	$.getJSON('corrales', function(featuresCollection) {
		var corrales = featuresCollection.features;
		for (i = 0; i < corrales.length; i++) {
			draw.add(corrales[i]);
		}
	});
}

function corralSeleccionado(seleccion) {

	if (seleccion.features.length > 0) {
		corralSeleccionado = seleccion.features[0];
		var nombre = corralSeleccionado.properties.nombre;
		var idCorral = corralSeleccionado.properties.idCorral;
		$('#corral-seleccionado').html(generarFormulario(idCorral, nombre));

	} else {
		$('#corral-seleccionado').html("");
		corralSeleccionado = undefined;
	}
}

function generarFormulario(idCorral, nombreCorral) {
	nombreCorral = nombreCorral === undefined ? "" : nombreCorral;
	return `<div class="container-fluid">
				<form id="formGuardarCorral" class="form-inline">
					<div class="form-group mx-sm-1">
						<label for="corral" class="sr-only">Nobre corral</label>
						<input type="text" class="form-control" id="corral" value="${nombreCorral}" placeholder="Ingresar nombre del corral...">
					</div>
					<button id="botonGuardarCorral" type="submit" class="btn btn-primary">Guardar</button>
				</form>
			</div>`;
}

function eliminarCorral(evento) {
	var corral = evento.features[0]
	var id = corral.properties.idCorral;
	$('#corral-seleccionado').html("");
	if (id !== undefined) {
		var corralParaEliminar = { "id" : id };
		$.ajax({
			type: 'post',
			url: 'corrales/eliminar',
			contentType : 'application/json',
			dataType: 'text',
			data: JSON.stringify(corralParaEliminar),
			success: function(respuesta) {
				dibujarCorrales();
			}
		});
	}
}

(function($) {
	"use strict";
	jQuery(window).on("load", function() {

		$("body").on('submit', '#formGuardarCorral', function(evento) {
			evento.preventDefault();
			corralSeleccionado.properties.nombre = $("#corral").val();
			$.ajax({
				type:'post',
				url: 'corrales/guardar',
				contentType : 'application/json',
				dataType: 'text',
				data: JSON.stringify(corralSeleccionado),
				success: function(respuesta) {
					dibujarCorrales();
				}
			});
		});
	});
})(jQuery);