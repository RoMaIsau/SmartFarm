package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;

@Service("servicioNotificacion")
@Transactional
public class ServicioNotificacionImpl implements ServicioNotificacion {

	private RepositorioNotificacion repositorioNotificacion;
	private RepositorioAlimento repositorioAlimento;

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
				Notificacion notificacion = new Notificacion();
				notificacion.setFecha(fecha);
				notificacion.setTitulo("Stock");
				notificacion.setDetalles(a.getNombre() + " ha llegado a su stock minimo");
				notificacion.setEstado(false);
				repositorioNotificacion.crearNotificacionStock(notificacion);
			}
		}
	}

	@Override
	public List<Notificacion> listarNotificaciones(Usuario usuario) {
		return repositorioNotificacion.listarNotificaciones(usuario);
	}

}
