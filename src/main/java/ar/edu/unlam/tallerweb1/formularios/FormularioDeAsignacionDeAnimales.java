package ar.edu.unlam.tallerweb1.formularios;

public class FormularioDeAsignacionDeAnimales {
	
	private Long idCorral;
	private Long[] animalesSeleccionados;
	
	public void setIdCorral(Long idCorral) {
		this.idCorral = idCorral;
	}
	
	public Long getIdCorral() {
		return idCorral;
	}
	
	public void setAnimalesSeleccionados(Long[] animalesSeleccionados) {
		this.animalesSeleccionados = animalesSeleccionados;
	}
	
	public Long[] getAnimalesSeleccionados() {
		return animalesSeleccionados;
	}
}
