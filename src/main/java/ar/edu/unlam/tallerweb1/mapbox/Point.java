package ar.edu.unlam.tallerweb1.mapbox;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = PointSerializer.class)
@JsonDeserialize(using = PointDeserializer.class)
public class Point {

	private double latitud;
	private double longitud;

	public Point(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public double[] toArray() {
		return new double[]{this.latitud, this.longitud};
	}
}
