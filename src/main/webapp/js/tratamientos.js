$(document).on("click", "#buscar", function() {
			var formulario = $('#formBuscar');
			$.ajax({
				type: "POST",
				data: formulario.serialize(),
				url:'buscarEnfermedad',
				success: function(respuesta) {
					$('#modalBuscar').modal('toggle');
					$('#busqueda').html(respuesta);					
				}
			})
		});