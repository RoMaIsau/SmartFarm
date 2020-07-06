package ar.edu.unlam.tallerweb1.mapbox;

public class AnimalEnMapa {

	private double latitud;
	private double longitud;
	private String tipoAnimal;

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setTipoAnimal(String tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}
	public String getTipoAnimal() {
		return tipoAnimal;
	}
}
