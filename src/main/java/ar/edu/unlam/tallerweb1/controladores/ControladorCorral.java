package ar.edu.unlam.tallerweb1.controladores;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.edu.unlam.tallerweb1.mapbox.Feature;
import ar.edu.unlam.tallerweb1.mapbox.FeatureCollection;
import ar.edu.unlam.tallerweb1.mapbox.Point;
import ar.edu.unlam.tallerweb1.modelo.Corral;
import ar.edu.unlam.tallerweb1.modelo.Vertice;
import ar.edu.unlam.tallerweb1.servicios.ServicioCorral;

@Controller
public class ControladorCorral {

	private ServicioCorral servicioCorral;

	@Autowired
	public ControladorCorral(ServicioCorral servicioCorral) {
		this.servicioCorral = servicioCorral;
	}

	@RequestMapping(value = "corrales", produces = "application/json")
	@ResponseBody
	public FeatureCollection obtenerCorrales() {

		FeatureCollection mapa = new FeatureCollection();
		List<Corral> corrales = this.servicioCorral.obtenerTodosLosCorrales();
		for (Corral corral :corrales) {			
			List<Point> points = new LinkedList<>();
			for(Vertice vertice : corral.getVertices()) {
				points.add(new Point(vertice.getLatitud(), vertice.getLongitud()));
			}
			Feature feature = new Feature(corral.getId(), corral.getNombre(), points);
			mapa.agregarFeature(feature);
		}

		return mapa;
	}

	@RequestMapping(value = "corrales/guardar", consumes = "application/json")
	public void guardarCorral(@RequestBody Feature feature) {

		Corral corral = new Corral();
		corral.setNombre(feature.getProperties().getNombre());
		if (feature.getProperties().getIdCorral() != null) {
			corral.setId(feature.getProperties().getIdCorral());
		}
		List<Point> puntos = feature.getGeometry().getCoordinates().get(0);
		List<Vertice> vertices = new LinkedList<Vertice>();
		for (int i = 0; i < puntos.size(); i++) {
			Vertice vertice = new Vertice();
			vertice.setNumero(i+1);
			vertice.setLatitud(puntos.get(i).getLatitud());
			vertice.setLongitud(puntos.get(i).getLongitud());
			vertices.add(vertice);
		}

		corral.setVertices(vertices);
		this.servicioCorral.guardar(corral);
	}
}
