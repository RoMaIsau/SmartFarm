package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;

public interface ServicioNotificacion {
	
	void crearNotificacionStock();
	List<Notificacion> listarNotificaciones(Long idUsuario);
	Notificacion notificacionPorDetalles(String detalles, String fecha);
	Notificacion notificacionPorId(Long id);
	void actualizarNotificacion(Notificacion notificacion);
	void crearNotificacionAnimalFueraDeLugar(Long id);
	List<Notificacion> obtenerNotificacionesPendientesDeAnimalFueraDeCorral();
	void marcarComoVista(Notificacion notificacion);
	void crearNotificacionDeVacunaVencida(AnimalDeGranja v);
}
