package ar.edu.unlam.tallerweb1.excepciones;

public class AnimalSinIdentificadorGpsException extends Exception {

	private static final long serialVersionUID = -1835252969856012484L;
	
	public AnimalSinIdentificadorGpsException(String identificador) {
		super("No se encontró un animal asociado al identificador: " + identificador);
	}

}
