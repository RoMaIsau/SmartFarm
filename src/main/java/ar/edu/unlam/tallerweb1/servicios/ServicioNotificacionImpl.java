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
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("servicioNotificacion")
@Transactional
public class ServicioNotificacionImpl implements ServicioNotificacion {

	private RepositorioNotificacion repositorioNotificacion;
	private RepositorioAlimento repositorioAlimento;
	private RepositorioUsuario repositorioUsuario;

	@Autowired
	public ServicioNotificacionImpl(RepositorioNotificacion repositorioNotificacion,
			RepositorioAlimento repositorioAlimento, RepositorioUsuario repositorioUsuario) {
		this.repositorioNotificacion = repositorioNotificacion;
		this.repositorioAlimento = repositorioAlimento;
		this.repositorioUsuario = repositorioUsuario;
	}

	@Override
	public void crearNotificacionStock() {

		List<Alimento> alimentos = repositorioAlimento.listarAlimentos();
		List<Usuario> empleados = repositorioUsuario.consultarUsuariosEmpleados();

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fecha = (LocalDate.now().format(dateFormat));

		for (Alimento a : alimentos) {
			Notificacion notificacion = new Notificacion();
			
			if (a.getCantidad().equals(a.getStockMinimo()) || a.getCantidad() < a.getStockMinimo()) {
				notificacion.setFecha(fecha);
				notificacion.setTitulo("Stock");
				notificacion.setDetalles(a.getNombre() + " ha llegado a su stock minimo");
				notificacion.setEstado(false);
				notificacion.setUsuarios(empleados);
				repositorioNotificacion.crearNotificacionStock(notificacion);
			}
		}
	}

	@Override
	public List<Notificacion> listarNotificaciones(Long idUsuario) {
		return repositorioNotificacion.listarNotificaciones(idUsuario);
	}

}
