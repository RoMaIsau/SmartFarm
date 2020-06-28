(function($) {
  "use strict";
  
  //Mandar id a modal de eliminar usuario
  $(document).on("click", ".open-Modal", function() {
		var id = $(this).data('id');
		$('.botonEliminarGastos').attr('href', "eliminarGasto?id=" + id);
	});

})(jQuery); // End of use strict
