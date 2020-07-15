package ar.edu.unlam.tallerweb1.controladores;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.formularios.FormularioDeAsignacionDeAnimales;

import ar.edu.unlam.tallerweb1.mapbox.Feature;
import ar.edu.unlam.tallerweb1.mapbox.FeatureCollection;
import ar.edu.unlam.tallerweb1.mapbox.Point;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
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
				points.add(new Point(vertice.getLatitud().doubleValue(), vertice.getLongitud().doubleValue()));
			}
			Feature feature = new Feature(corral.getId(), corral.getNombre(), points);
			mapa.agregarFeature(feature);
		}

		return mapa;
	}

	@RequestMapping(value = "corrales/guardar", consumes = "application/json", produces = "text/plain")
	@ResponseBody
	public String guardarCorral(@RequestBody Feature feature) {

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
			vertice.setLatitud(BigDecimal.valueOf(puntos.get(i).getLatitud()));
			vertice.setLongitud(BigDecimal.valueOf(puntos.get(i).getLongitud()));
			vertices.add(vertice);
		}

		corral.setVertices(vertices);
		this.servicioCorral.guardar(corral);
		return "ok";
	}

	@RequestMapping(value = "corrales/eliminar", consumes = "application/json", produces = "text/plain")
	@ResponseBody
	public String eliminarCorral(@RequestBody Corral corral) {
		this.servicioCorral.eliminar(corral);
		return "ok";
	}

	@RequestMapping("corrales/detalle")
	public ModelAndView mostrarDetalle(@RequestParam("idCorral")Long idCorral) {
		String nombreCorral = this.servicioCorral.obtenerNombre(idCorral);
		List<AnimalDeGranja> animalesEnCorral = this.servicioCorral.obtenerAnimalesPorCorral(idCorral);
		List<AnimalDeGranja> animalesSinCorral = this.servicioCorral.obtenerAnimalesSinCorral();
		FormularioDeAsignacionDeAnimales asignacion = new FormularioDeAsignacionDeAnimales();
		asignacion.setIdCorral(idCorral);

		ModelMap modelo = new ModelMap();
		modelo.put("nombre", nombreCorral);
		modelo.put("animalesDelCorral", animalesEnCorral);
		modelo.put("animalesSueltos", animalesSinCorral);
		modelo.put("asignacion", asignacion);
		return new ModelAndView("modalDetalleCorral", modelo);
	}

	@RequestMapping(value = "corrales/asignar", method = RequestMethod.POST, produces = "text/plain")
	@ResponseBody
	public String asignarAnimales(@ModelAttribute FormularioDeAsignacionDeAnimales asignacion)
	{
		this.servicioCorral.asignarAnimales(asignacion.getIdCorral(), asignacion.getAnimalesSeleccionados());
		return "ok";
	}

}
