package ar.edu.unlam.tallerweb1.modelo;

public class Posicion {

	private double longitud;
	private double latitud;
	private String identificador;

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	@Override
	public String toString() {
		return String.format("Identificador:%s - Latitud:%.7f - Longitud:%.7f",
				this.identificador, this.latitud, this.longitud);
	}
}
