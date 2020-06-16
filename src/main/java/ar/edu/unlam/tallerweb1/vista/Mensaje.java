package ar.edu.unlam.tallerweb1.vista;

public class Mensaje {

	private enum Gravedad {

		EXITO("alert-success"),
		ERROR("alert-danger");

		private String nombreClase;
		
		private Gravedad(String nombreClase) {
			this.nombreClase = nombreClase;
		}
		
		public String getNombreClase() {
			return nombreClase;
		}
	}

	private String titulo;
	private String detalle;
	private Gravedad gravedad;

	private Mensaje(Gravedad gravedad) {
		this.gravedad = gravedad;
	}

	public static Mensaje crearMensajeDeExito() {
		return new Mensaje(Gravedad.EXITO);
	}

	public static Mensaje crearMensajeDeError() {
		return new Mensaje(Gravedad.ERROR);
	}

	public Mensaje conTitulo(String titulo) {
		this.titulo = titulo;
		return this;
	}

	public Mensaje conDetalle(String detalle) {
		this.detalle = detalle;
		return this;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDetalle() {
		return detalle;
	}
	
	public String getGravedad() {
		return gravedad.getNombreClase();
	}
}
