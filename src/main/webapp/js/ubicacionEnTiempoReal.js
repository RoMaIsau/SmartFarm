$(document).ready(function(){
    setInterval(buscarPosiciones, 10000);
});

function buscarPosiciones() {
	$.ajax({
		type: 'get',
		url: contextPath + '/ubicaciones/ultimas',
		contentType: 'application/json',
		success: function(posiciones) {
			alert("Dibujar posiciones: " + posiciones)
		},
		complete:function(data){
   			setTimeout(buscarPosiciones, 10000);
   		}
	});
}