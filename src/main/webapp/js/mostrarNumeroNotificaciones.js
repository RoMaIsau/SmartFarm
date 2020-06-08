$(document).ready(function(){
	var cantidadNoti = $("#notificaciones > .font-weight-bold").length;
	$('#contador').html(cantidadNoti);
	
	if($('#contador').html() == 0){
		$('#contador').html("");
	}
	
	$(document).on("click", ".fa-bell", function() {
		$('#contador').html("");
	});
});

