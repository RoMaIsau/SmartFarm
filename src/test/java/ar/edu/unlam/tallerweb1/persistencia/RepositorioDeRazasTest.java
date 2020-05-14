package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeRazas;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeRazasImpl;

@Transactional
public class RepositorioDeRazasTest extends SpringTest {

	private RepositorioDeRazas repositorioDeRazas;

	@Before
	public void inicializarRepositorio() {

		this.repositorioDeRazas = new RepositorioDeRazasImpl(this.sessionFactory);
	}

	@Test
	public void listaLasRazasSegunElTipoDeAnimal() {
		
		TipoAnimal equino = this.crearTipoAnimal("EQUINO");
		TipoAnimal vacuno = this.crearTipoAnimal("VACUNO");
		
		Raza caballoArabe = this.crearRaza("CABALLO ARABE", equino);
		Raza frison = this.crearRaza("FRISON", equino);
		Raza holstein = this.crearRaza("HOLSTEIN", vacuno);
		
		List<Raza> razasEquinas = this.repositorioDeRazas.listarPorTipoAnimal(equino); 
		
		assertThat(razasEquinas).hasSize(2);
		assertThat(razasEquinas).extracting("nombre").contains(caballoArabe.getNombre(), frison.getNombre());
		assertThat(razasEquinas).extracting("nombre").doesNotContain(holstein.getNombre());
	}
	
	@Test
	public void cuandoNoHayUnaRazaDeUnTipoDevuelveUnaListaVacia() {
		
		TipoAnimal equino = this.crearTipoAnimal("EQUINO");
		TipoAnimal vacuno = this.crearTipoAnimal("VACUNO");
		
		this.crearRaza("CABALLO ARABE", equino);
		
		List<Raza> razasEquinas = this.repositorioDeRazas.listarPorTipoAnimal(vacuno);
		
		assertThat(razasEquinas).isEmpty();
	}
	
	private TipoAnimal crearTipoAnimal(String nombre) {
		
		TipoAnimal tipoAnimal = new TipoAnimal();
		tipoAnimal.setNombre(nombre);
		
		this.sessionFactory.getCurrentSession().save(tipoAnimal);
		return tipoAnimal;
	}
	
	private Raza crearRaza(String nombre, TipoAnimal tipoAnimal) {
		
		Raza raza = new Raza();
		raza.setNombre(nombre);
		raza.setTipo(tipoAnimal);
		
		this.sessionFactory.getCurrentSession().save(raza);
		
		return raza;
	}

}
