package ar.edu.unlam.tallerweb1.mapbox;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class PointDeserializer extends JsonDeserializer<Point> {

	@Override
	public Point deserialize(JsonParser parser, DeserializationContext contexto) throws IOException, JsonProcessingException {

		double latitud = this.extraerValor(parser);
		double longitud = this.extraerValor(parser);		
		
		return new Point(latitud, longitud);
	}

	private double extraerValor(JsonParser parser) throws IOException {
		JsonToken token = parser.nextToken();
		if (token == null) {
			throw new IllegalStateException("Ocurrió un error parseando las coordenadas. Token nulo");
		}
		return parser.getDoubleValue();
	}
}
