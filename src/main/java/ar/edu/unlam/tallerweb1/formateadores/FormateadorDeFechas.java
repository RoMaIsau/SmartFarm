package ar.edu.unlam.tallerweb1.formateadores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class FormateadorDeFechas implements Formatter<Date> {

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public String print(Date fecha, Locale locale) {		
		return simpleDateFormat.format(fecha);
	}

	@Override
	public Date parse(String fecha, Locale locale) throws ParseException {
		return simpleDateFormat.parse(fecha);
	}
}
