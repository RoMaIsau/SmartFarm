package ar.edu.unlam.tallerweb1.mapbox;

import java.util.List;

public class Feature {
	private String type;
	private Geometry geometry;
	private Properties properties;

	public Feature(String name, List<Point> points) {
		this.type = "Feature";
		this.geometry = new Geometry(points);
		this.properties = new Properties("corral", name);
	}

	public String getType() {
		return type;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public Properties getProperties() {
		return properties;
	}
}
