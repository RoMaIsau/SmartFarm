(function($) {
	"use strict";
	jQuery(window).on("load", function() {

		$("body").on('submit', '#formPlanAlimentario', function(evento) {
			evento.preventDefault();
			$.ajax({
				type: "POST",
				data: $(this).serialize(),
				url:'agregarCronograma',
				success: function(respuesta) {
					$('#detallePlanAlimentario').html(respuesta);
				}
			})
		});
		
		$(document).on("click", "#botonModalEliminarCronograma", function() {
			var idCronograma = $(this).data('cronograma');
			var idPlan = $(this).data('plan');
			$("#idCronogramaParaEliminar").val(idCronograma);
			$("#idPlanDeCronogramaEliminado").val(idPlan);
		});
		
		$(document).on("click", "#botonEliminarCronograma", function() {
			var formulario = $('#formEliminarCronograma');
			$.ajax({
				type: "POST",
				data: formulario.serialize(),
				url:'eliminarCronograma',
				success: function(respuesta) {
					$('#modalEliminarCronograma').modal('toggle');
					$('#cronogramaDeAlimentacion').html(respuesta);					
				}
			})
		});

		$(document).on("click", "#botonEditarCronograma", function() {
			var idCronograma = $(this).data('cronograma');
			$.ajax({
				type: "GET",
				url: 'editarCronograma',
				data:{
					"idCronograma":idCronograma
				},
				success:function(respuesta) {
					$('#contenedorModalEditarCronograma').html(respuesta);
					$('#modalEditarCronograma').modal('toggle');
				}
			})
		});

		$(document).on("click", "#botonAceptarEdicionCronograma", function() {
			var formulario = $('#formEdicionDeCronograma');
			var html;
			$.ajax({
				type: 'POST',
				url: 'editarCronograma',
				data: formulario.serialize(),
				success:function(respuesta) {
					$('#modalEditarCronograma').modal('toggle');
					$('#cronogramaDeAlimentacion').html(respuesta);
				}
			})
		});

	});	
})(jQuery);