package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioNotificacion {
	
	void crearNotificacionStock();
	List<Notificacion> listarNotificaciones(Usuario usuario);
}
