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
		marker = new mapboxgl.Marker()
				.setLngLat([locations[i][1], locations[i][2] ])
				.setPopup(new mapboxgl.Popup()
					.setHTML("<p>" + locations[i][3] + "</p><p>" + locations[i][0] + "</p> <p>" + locations[i][4] + "</p>")
					)
				.addTo(map);	
	}

