package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;

public interface RepositorioNotificacion {

	void crearNotificacionStock(Notificacion notificacion);

	List<Notificacion> listarNotificaciones(Long idUsuario);

	Notificacion notificacionPorDetalles(String detalles, String fecha);

	Notificacion notificacionPorId(Long id);

	void actualizarNotificacion(Notificacion notificacion);

	void crearNotificacionAnimal(Notificacion notificacion);

	Notificacion BuscarNotificacionDeAnimalPorDetalles(String detalles, String fecha);

}
