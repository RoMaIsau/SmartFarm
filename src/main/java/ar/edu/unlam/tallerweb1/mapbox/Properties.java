package ar.edu.unlam.tallerweb1.mapbox;

public class Properties {
	private String type;
	private String nombre;

	public Properties(String type, String nombre) {
		this.type = type;
		this.nombre = nombre;
	}

	public String getType() {
		return type;
	}

	public String getNombre() {
		return nombre;
	}
}
