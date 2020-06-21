var map;

cantidadAnimales = document.getElementById("tabla").rows.length;

var locations = new Array(cantidadAnimales);

console.log(cantidadAnimales);

for (i = 1; i < cantidadAnimales; i++) {

	locations[i] = new Array(
			document.getElementById("tabla").rows[i].cells.length);

	for (j = 0; j < document.getElementById("tabla").rows[i].cells.length; j++) {
		locations[i][j] = document.getElementById("tabla").rows[i].cells[j].innerText;

	}
}

mapboxgl.accessToken = 'pk.eyJ1IjoiZXplMjEiLCJhIjoiY2tiODBxcDYzMGIxYTMwcWFtZ2pncmNjdCJ9.XzKHl3aCknUpCwDcMSMlJg';
map = new mapboxgl.Map({
	container : "map",
	style : "mapbox://styles/mapbox/satellite-streets-v11",
	zoom : 14,
	center : [ -59.241913, -35.276381 ]
});

var marker, i;

for (i = 1; i < locations.length; i++) {

	var icono = document.createElement('div');
	icono.classList.add("icon");

	switch (locations[i][3]) {

	case 'VACUNO':
		icono.id = 'vacuno';
		imagen = 'vacuno';
		break;
	case 'OVINO':
		icono.id = 'ovino';
		imagen = 'ovino';
		break;
	case 'CAPRINO':
		icono.id = 'caprino';
		imagen = 'caprino';
		break;
	case 'EQUINO':
		icono.id = 'equino';
		imagen = 'equino';
		break;
	case 'PORCINO':
		icono.id = 'porcino';
		imagen = 'porcino';
		break;
	}
	
	var popup = new mapboxgl.Popup({
		offset : 25
	}).setHTML('<div><div class="row no-gutters align-items-center"><div class="col mr-2">'+
				'<div class="text-xs font-weight-bold text-success text-uppercase mb-1" style="font-size: 15px;">'+locations[i][0]+'</div>'+
                 '<div class="h6 mb-0 text-gray-900" style="font-size: 12px;">'+locations[i][3]+' - '+locations[i][4]+'</div>'+
                 '<div class="mt-1"><a href="verAnimal?id='+locations[i][0]+'" style="font-size: 10px;">Ver animal</a></div></div> ' +
                  '<div class="col-auto mt-2"><img class="img-profile" style="height:35px; width: 35px;" src="/SmartFarm/img/'+imagen+'.png"/></div></div> </div>');


	new mapboxgl.Marker(icono).setLngLat([ locations[i][1], locations[i][2] ])
			.setPopup(popup).addTo(map);
}
