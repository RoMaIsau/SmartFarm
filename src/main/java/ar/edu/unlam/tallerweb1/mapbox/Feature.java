package ar.edu.unlam.tallerweb1.mapbox;

import java.util.List;

public class Feature {
	private String type;
	private Geometry geometry;
	private Properties properties;

	public Feature() {}

	public Feature(Long id, String nombre, List<Point> points) {
		this.type = "Feature";
		this.geometry = new Geometry(points);
		this.properties = new Properties("corral", id, nombre);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
