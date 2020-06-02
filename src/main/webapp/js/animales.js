(function($) {
	"use strict"; // Start of use strict

	$(document).on("click", "#botonModalEliminarAnimal", function() {
		var id = $(this).data('id');
		$("#idAnimalParaEliminar").val(id);
	});

})(jQuery); // End of use strict