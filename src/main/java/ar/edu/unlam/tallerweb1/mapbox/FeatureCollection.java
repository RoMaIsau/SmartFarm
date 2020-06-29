package ar.edu.unlam.tallerweb1.mapbox;

import java.util.LinkedList;
import java.util.List;

public class FeatureCollection {

	private String type;
	private List<Feature> features;

	public FeatureCollection() {
		this.type = "FeatureCollection";
		this.features = new LinkedList<Feature>();
	}

	public String getType() {
		return type;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void agregarFeature(Feature feature) {
		this.features.add(feature);
	}
}
