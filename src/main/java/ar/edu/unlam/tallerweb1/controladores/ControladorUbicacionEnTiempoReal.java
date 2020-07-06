package ar.edu.unlam.tallerweb1.controladores;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.mapbox.AnimalEnMapa;

@Controller
public class ControladorUbicacionEnTiempoReal {

	@RequestMapping("ubicaciones/mapa")
	ModelAndView mostrarMapa() {
		return new ModelAndView("mapaEnTiempoReal");
	}

	@RequestMapping(value = "ubicaciones/ultimas", produces = "application/json")
	@ResponseBody
	public List<AnimalEnMapa> obtenerUltimasPosiciones() {

		List<AnimalEnMapa> animalesEnMapa = new LinkedList<>();

		return animalesEnMapa;
	}
}
