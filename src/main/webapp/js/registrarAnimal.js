(function($) {
  "use strict"; // Start of use strict
   $('#tipo').change(function(){	  
	  
	  $.getJSON('cargarRazas', {
		  idTipoAnimal : $(this).val()
	  }, function(respuesta) {
		  
		  var opciones = '';
		  var cantidadDeRazas = respuesta.length;
		  
		  for (var i = 0;  i < cantidadDeRazas; i++) {
			  
			  var seleccionada = i == 0 ? 'selected="selected"' : '';
			  
			  var raza = respuesta[i];
			  
			  opciones += '<option value="' + raza.id + '" ' + seleccionada + '>' + raza.nombre + '</option>';
		  }	  
		  
		  $('#raza').html(opciones);
		  
	  });
  });
  

})(jQuery); // End of use strict
