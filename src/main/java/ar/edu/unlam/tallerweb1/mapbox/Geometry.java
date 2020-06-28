package ar.edu.unlam.tallerweb1.mapbox;

import java.util.LinkedList;
import java.util.List;

public class Geometry {

	private String type;
	private List<List<Point>> coordinates;

	public Geometry() {}

	public Geometry(List<Point> points) {
		this.type = "Polygon";
		this.coordinates = new LinkedList<List<Point>>();
		this.coordinates.add(points);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<List<Point>> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<List<Point>> coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String toString() {
		return this.coordinates.toString();
	}
}
