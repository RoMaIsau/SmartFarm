package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioVacunas;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;
import ar.edu.unlam.tallerweb1.servicios.ServicioGanado;
import ar.edu.unlam.tallerweb1.servicios.ServicioHistoriaClinica;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioVacunaImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioVacunas;

public class TestDeVacunas {
	private ControladorVeterinario controladorVeterinario;
	
	private ServicioNotificacion servicioNotificacion;
	private ServicioGanado servicioGanado;
	private ServicioVacunas servicioVacunas;
	private ServicioDeAnimales servicioDeAnimales;
	private ServicioHistoriaClinica servicioHistoriaClinica;
	
	private List<Raza> crearRazas() {
		Raza caballoArabe = new Raza();
		caballoArabe.setId(1L);
		caballoArabe.setNombre("CABALLO ARABE");
		List<Raza> razas = new LinkedList<Raza>();
		razas.add(caballoArabe);
		return razas;
	}
	private List<TipoAnimal> crearTiposDeAnimales() {
		TipoAnimal vacuno = new TipoAnimal();
		vacuno.setId(1L);
		vacuno.setNombre("VACUNO");
		List<TipoAnimal> tiposDeAnimales = new LinkedList<TipoAnimal>();
		tiposDeAnimales.add(vacuno);
		return tiposDeAnimales;
	}
	private List<Genero> crearGeneros() {
		Genero femenino = new Genero();
		femenino.setId(1L);
		femenino.setNombre("FEMENINO");
		List<Genero> generos = new LinkedList<Genero>();
		generos.add(femenino);
		return generos;
	}
	private ServicioDeAnimales crearMockServicioDeAnimales() {
		List<TipoAnimal> tiposDeAnimales = this.crearTiposDeAnimales();
		List<Raza> razas = this.crearRazas();
		List<Genero> sexos = this.crearGeneros();
		ServicioDeAnimales servicio = mock(ServicioDeAnimales.class);
		when(servicio.obtenerTiposDeAnimales()).thenReturn(tiposDeAnimales);
		when(servicio.obtenerRazasPorTipoAnimal(any(TipoAnimal.class))).thenReturn(razas);
		when(servicio.obtenerGeneros()).thenReturn(sexos);
		return servicio;
	}
	private HttpServletRequest configurarRolLogueado(String rol) {
		HttpServletRequest pedido = mock(HttpServletRequest.class);
		HttpSession sesionHttp = mock(HttpSession.class);
		when(pedido.getSession()).thenReturn(sesionHttp);
		when(sesionHttp.getAttribute("ROL")).thenReturn(rol);
		return pedido;
	}
	
	@Before	
	public void inicializar() {
		this.servicioDeAnimales = crearMockServicioDeAnimales();
		this.servicioNotificacion = mock(ServicioNotificacion.class);
		this.servicioHistoriaClinica = mock(ServicioHistoriaClinica.class);
		this.servicioGanado = mock(ServicioGanado.class);
		this.servicioVacunas = mock(ServicioVacunas.class);
		
		this.controladorVeterinario = new ControladorVeterinario(this.servicioNotificacion, this.servicioGanado,
				this.servicioVacunas, this.servicioDeAnimales, this.servicioHistoriaClinica);
	}
	
	
	
	
	
	@Test
	public void testVerVacunas(){
		//Preparacion  
	    AnimalDeGranja animalMock=  mock(AnimalDeGranja.class);
	    when(animalMock.getId()).thenReturn(1L);
	    
	    Vacuna vacunaMock=mock(Vacuna.class);
	    when(vacunaMock.getNombre()).thenReturn("vacuna");
	    
	    RepositorioVacunas servicioVacunaDaoMock=mock(RepositorioVacunas.class);
	    when(servicioVacunaDaoMock.getVacuna("vacuna")).thenReturn(vacunaMock);
	    
	    ServicioVacunaImpl servicio= new ServicioVacunaImpl(servicioVacunaDaoMock);
	    
	    //Ejecucion   
		Vacuna resultado= servicio.getVacuna("vacuna");
	    
		//verificacion
	    assertThat(resultado.getNombre()).isEqualTo("vacuna");
	}
	
	@Test
	public void testServicioVacunar(){
		//Preparacion
		HttpServletRequest request = configurarRolLogueado("Veterinario");
	    AnimalDeGranja animalMock = mock(AnimalDeGranja.class);
	    
	    when(this.servicioGanado.ver(1L)).thenReturn(animalMock);
	    List<Vacuna>listaVacunas= new ArrayList<Vacuna>();
	    when(this.servicioVacunas.alarmaVacuna(animalMock)).thenReturn(listaVacunas);  
	    
	    ModelAndView modelAndView = this.controladorVeterinario.vacunar(1L, request);
	    List<Vacuna>vencidas = (List<Vacuna>) modelAndView.getModelMap().get("vencidas");
	    
	    //verificacion 
	    assertTrue(vencidas.equals(listaVacunas));
	}
}
