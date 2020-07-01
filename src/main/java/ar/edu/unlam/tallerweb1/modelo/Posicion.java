package ar.edu.unlam.tallerweb1.modelo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Posicion {

	private double longitud;
	private double latitud;
	private String identificador;
	private DecimalFormat decimalFormat;
	
	public Posicion() {
		
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');
		
		this.decimalFormat = new DecimalFormat("##.#######", simbolos);
	}

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
		return String.format("Identificador:%s - Latitud:%s - Longitud:%s",
				this.identificador, this.decimalFormat.format(this.latitud), this.decimalFormat.format(this.longitud));
	}
}
