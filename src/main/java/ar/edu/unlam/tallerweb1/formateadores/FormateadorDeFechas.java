package ar.edu.unlam.tallerweb1.formateadores;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.format.Formatter;

public class FormateadorDeFechas implements Formatter<LocalDate> {

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Override
	public String print(LocalDate fecha, Locale locale) {
		return fecha.format(this.formatter);
	}

	@Override
	public LocalDate parse(String fecha, Locale locale) throws ParseException {
		return LocalDate.parse(fecha, this.formatter);
	}
}
