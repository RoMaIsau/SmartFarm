package ar.edu.unlam.tallerweb1.mapbox;

public class Properties {
	private String type;
	private String nombre;
	private Long idCorral;

	public Properties() {}

	public Properties(String type, Long idCorral, String nombre) {
		this.type = type;
		this.nombre = nombre;
		this.idCorral = idCorral;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdCorral() {
		return idCorral;
	}

	public void setIdCorral(Long idCorral) {
		this.idCorral = idCorral;
	}
}
