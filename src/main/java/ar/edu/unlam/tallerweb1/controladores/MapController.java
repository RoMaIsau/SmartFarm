package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Location;
import ar.edu.unlam.tallerweb1.modelo.ServerLocation;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;

@Controller
public class MapController {
	
	@RequestMapping("/mapa")
	public ModelAndView irAMapa(HttpServletRequest request, ModelMap model) {
		String rol = (String) request.getSession().getAttribute("ROL");
		if (!rol.equals("Admin") && !rol.equals("Empleado") && !rol.equals("")) {
			return new ModelAndView("redirect:/login");
		}
		
		Map<String,Location> mapLocation = getMapLocation();
		model.addAttribute("lists",mapLocation);
		return new ModelAndView("mapa", model);
	}
	
	/*
	//Endpoint where the request would be coming after the user clicks on click here link in the welcome page
	@RequestMapping("/hello")
	public String helloWorld(ModelMap model) {
		//Creating a list to return to the front end.This list contains the details of the locations to be displayed on the screen.
		Map<String,Location> mapLocation = getMapLocation();
		//Creating and filling with data in the ModelAndView object.
		model.addAttribute("lists",mapLocation);
		return "hellopage";
	}*/
	
	/**
	 * This method returns the filtered results based on some business rules.
	 * 
	 * @return List<String>
	 */
	private Map<String,Location> getMapLocation() {

		Map mapLocation=new HashMap();
		
		List<Location> locationList = getFilteredLocations();
		for (Location loc : locationList) {
			mapLocation.put(loc.getLocName(),loc.getServerLocation());
		}
		return mapLocation;
	}

	
	/**
	 * This method returns the exactly set of locations which are fetched based on some conditions
	 * 
	 * @return List<Location>
	 */
	public List<Location> getFilteredLocations() {
		int rangeKm = 2;
		Location centreLocation = new Location(new ServerLocation(-34.624646f, -58.671073f,0), "INTA");
		
		List<Location> allLocations = getLocationsFromDB();
		List<Location> filteredList = new ArrayList<Location>();
		filteredList.add(centreLocation);
		float distance;
		System.out.println("The nearby locations in the range are as follows : ");
		for (Location loc : allLocations) {
			if (!(loc.getServerLocation().getLatitude() == centreLocation.getServerLocation().getLatitude())
					|| !(loc.getServerLocation().getLongitude() == centreLocation.getServerLocation().getLongitude())) {

				distance = distFrom(centreLocation.getServerLocation().getLatitude(),
						centreLocation.getServerLocation().getLongitude(), loc.getServerLocation().getLatitude(),
						loc.getServerLocation().getLongitude());
				if (distance <= (rangeKm * 1000)) {
					filteredList.add(loc);
				}
			}
		}

	
		return filteredList;

	}
	
	/**
	 * This method creates the list of available locations data to us.
	 * We can use SQL to hit the db and get the data of locations.
	 * 
	 * @return List<Location>
	 */
	
	@Inject
	private ServicioDeAnimales servicioDeAnimales;
	
	public List<Location> getLocationsFromDB() {
		List<Location> locationList = new ArrayList<Location>();
		ServerLocation serverLocation = new ServerLocation(0, 0, 0);
		
		List<AnimalDeGranja> animales = servicioDeAnimales.obtenerTodos();
		
		for(AnimalDeGranja animal : animales){
			serverLocation.setLatitude(serverLocation.setearLatitudAleatorea());
			serverLocation.setLongitude(serverLocation.setearLongitudAleatorea());
			long id = animal.getId();
			serverLocation.setValue(id);
			
			Location location = new Location(new ServerLocation(-serverLocation.getLatitude(), -serverLocation.getLongitude(), serverLocation.getValue()), animal.getTipo().getNombre());
			locationList.add(location);
		}
		
		return locationList;
	}
	
	
	/**
	 * This method calculates the distance between two locations.
	 * This method uses the coordinates of the two locations and applies some logic and get the distance between two
	 * locations in metres unit.
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * 
	 * @return distance in metres.
	 */
	public float distFrom(float lat1, float lng1, float lat2, float lng2) {
		double earthRadius = 6371000; // meters
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		float dist = (float) (earthRadius * c);

		return dist;
	}
}