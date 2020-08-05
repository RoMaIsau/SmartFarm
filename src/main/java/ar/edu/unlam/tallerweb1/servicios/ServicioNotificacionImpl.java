package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeAnimales;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;

@Service("servicioNotificacion")
@Transactional
public class ServicioNotificacionImpl implements ServicioNotificacion {

	private static final String ANIMAL_FUERA_DE_LUGAR = "Animal fuera de rango";

	private RepositorioNotificacion repositorioNotificacion;
	private RepositorioAlimento repositorioAlimento;
	
	@Inject
	private ServicioDeAnimales servicioDeAnimales;
	
	@Autowired
	public ServicioNotificacionImpl(RepositorioNotificacion repositorioNotificacion,
			RepositorioAlimento repositorioAlimento) {
		this.repositorioNotificacion = repositorioNotificacion;
		this.repositorioAlimento = repositorioAlimento;
	}

	@Override
	public void crearNotificacionStock() {

		List<Alimento> alimentos = repositorioAlimento.listarAlimentos();

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fecha = (LocalDate.now().format(dateFormat));

		for (Alimento a : alimentos) {

			if (a.getCantidad().equals(a.getStockMinimo()) || a.getCantidad() < a.getStockMinimo()) {

				String detalles = a.getNombre() + " ha llegado a su stock minimo";
				
				Notificacion notificacionGuardada = notificacionPorDetalles(detalles, fecha);
				

				if (notificacionGuardada == null) {
					Notificacion notificacion = new Notificacion();

					notificacion.setFecha(fecha);
					notificacion.setTitulo("Stock");
					notificacion.setDetalles(detalles);
					notificacion.setEstado(false);
					
					repositorioNotificacion.crearNotificacionStock(notificacion);
				}

			}
		}
	}

	@Override
	public List<Notificacion> listarNotificaciones(Long idUsuario) {
		return repositorioNotificacion.listarNotificaciones(idUsuario);
	}

	@Override
	public Notificacion notificacionPorDetalles(String detalles, String fecha) {
		return repositorioNotificacion.notificacionPorDetalles(detalles, fecha);
	}

	@Override
	public Notificacion notificacionPorId(Long id) {
		return repositorioNotificacion.notificacionPorId(id);
	}

	@Override
	public void actualizarNotificacion(Notificacion notificacion) {
		repositorioNotificacion.actualizarNotificacion(notificacion);
	}

	@Override
	public void crearNotificacionAnimalFueraDeLugar(Long id) {
		AnimalDeGranja animal = (AnimalDeGranja) servicioDeAnimales.obtenerPorId(id);
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fecha = (LocalDate.now().format(dateFormat));

		String detalles = "El " + animal.getTipo().getNombre() + " con ID " + animal.getId() + " ha salido de su ubicación habitual";
		
		Notificacion notificacion = new Notificacion();
		notificacion.setFecha(fecha);
		notificacion.setTitulo(ANIMAL_FUERA_DE_LUGAR);
		notificacion.setDetalles(detalles);
		notificacion.setEstado(false);
		repositorioNotificacion.crearNotificacionAnimal(notificacion);
	}

	@Override
	public List<Notificacion> obtenerNotificacionesPendientesDeAnimalFueraDeCorral() {
		return this.repositorioNotificacion.listarNotificacionesPendientesPorTitulo(ANIMAL_FUERA_DE_LUGAR);
	}

	@Override
	public void marcarComoVista(Notificacion notificacion) {
		Notificacion notificacionVista = this.repositorioNotificacion.notificacionPorId(notificacion.getId());
		notificacionVista.setEstado(true);
		actualizarNotificacion(notificacionVista);
	}
	
	@Override
	public void crearNotificacionDeVacunaVencida(AnimalDeGranja v) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fecha = (LocalDate.now().format(dateFormat));

		String detalles = "El animal " + v.getRaza().getNombre() + " tiene vacunas vencidas.";
		Notificacion notificacionGuardada = notificacionPorDetalles(detalles, fecha);
		
		if (notificacionGuardada == null) {
			Notificacion notificacion = new Notificacion();
			notificacion.setFecha(fecha);
			notificacion.setTitulo("Vacuna vencida");
			notificacion.setDetalles(detalles);
			notificacion.setEstado(false);
			
			repositorioNotificacion.crearNotificacionDeVacunaVencida(notificacion);
		}
	}
	
}
