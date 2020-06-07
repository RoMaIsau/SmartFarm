$(document).ready(function(){
	var cantidadNoti = $("#notificaciones > a").length;
	$('#contador').html(cantidadNoti - 1);
	
	if($('#contador').html() == 0){
		$('#contador').html("");
	}
	
	$(document).on("click", ".fa-bell", function() {
		$('#contador').html("");
	});
});

