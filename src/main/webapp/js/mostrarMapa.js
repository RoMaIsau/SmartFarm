var map;

cantidadAnimales = document.getElementById("tabla").rows.length;

var locations = new Array(cantidadAnimales);

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
	zoom : 14.57,
	center : [ -59.2335, -35.2766 ]
});

var marker, i;

for (i = 1; i < locations.length; i++) {
	
	var popup = new mapboxgl.Popup({ offset: 25 }).setHTML(
			locations[i][0] + ' - '+ locations[i][3]+' - '+ locations[i][4]
			);
	
	var icono = document.createElement('div');
	icono.classList.add("icon");
	
	if(locations[i][3] == 'VACUNO'){
		icono.id = 'vacuno';
	}

	if(locations[i][3] == 'OVINO'){
		icono.id = 'ovino';
	}
	
	if(locations[i][3] == 'CAPRINO'){
		icono.id = 'caprino';
	} 
	
	if(locations[i][3] == 'EQUINO'){
		icono.id = 'equino';
	}
	
	if(locations[i][3] == 'PORCINO'){
		icono.id = 'porcino';
	}
	
	new mapboxgl.Marker(icono)
		.setLngLat([locations[i][1], locations[i][2] ])
		.setPopup(popup)
		.addTo(map);
}
