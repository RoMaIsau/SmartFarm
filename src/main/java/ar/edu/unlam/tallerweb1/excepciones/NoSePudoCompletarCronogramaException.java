package ar.edu.unlam.tallerweb1.excepciones;

public class NoSePudoCompletarCronogramaException extends Exception {

	private static final long serialVersionUID = -363238012173046368L;

	public NoSePudoCompletarCronogramaException(String mensaje) {
		super(mensaje);
	}
}
