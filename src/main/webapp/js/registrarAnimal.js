(function($) {
	"use strict"; // Start of use strict
	jQuery(window).on("load", function(){

		// Cada vez que se selecciona un tipo de animal
		$('#tipo').change(function() {
			cargarRazas($(this).val());
		});

	});

	function cargarRazas(idTipoAnimal) {
		$.getJSON('cargarRazas', {
			idTipoAnimal : idTipoAnimal
		}, function(respuesta) {

			var opciones = '';
			var cantidadDeRazas = respuesta.length;

			for (var i = 0; i < cantidadDeRazas; i++) {

				var seleccionada = i == 0 ? 'selected="selected"' : '';

				var raza = respuesta[i];

				opciones += '<option value="' + raza.id + '" ' + seleccionada + '>' + raza.nombre + '</option>';
			}

			$('#raza').html(opciones);

		});
	}
})(jQuery); // End of use strict

window.onload = function() {
	mostrarErrores();

	function mostrarErrores() {
		if (hayError !== undefined && hayError == true) {
			$('#peso').addClass('is-invalid');
		}

	}
};