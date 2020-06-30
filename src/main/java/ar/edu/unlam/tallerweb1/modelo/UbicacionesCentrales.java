package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UbicacionesCentrales {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double latitudCaprinoCentral;
	private Double longitudCaprinoCentral;
	private Double latitudEquinoCentral;
	private Double longitudEquinoCentral;
	private Double latitudOvinoCentral;
	private Double longitudOvinoCentral;
	private Double latitudPorcinoCentral;
	private Double longitudPorcinoCentral;
	private Double latitudVacunoCentral;
	private Double longitudVacunoCentral;
	
	public UbicacionesCentrales(){};
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getLatitudVacunoCentral() {
		return latitudVacunoCentral;
	}
	
	public void setLatitudVacunoCentral(Double latitudVacunoCentral) {
		this.latitudVacunoCentral = latitudVacunoCentral;
	}
	
	public Double getLongitudVacunoCentral() {
		return longitudVacunoCentral;
	}
	
	public void setLongitudVacunoCentral(Double longitudVacunoCentral) {
		this.longitudVacunoCentral = longitudVacunoCentral;
	}
	
	public Double getLatitudCaprinoCentral() {
		return latitudCaprinoCentral;
	}
	
	public void setLatitudCaprinoCentral(Double latitudCaprinoCentral) {
		this.latitudCaprinoCentral = latitudCaprinoCentral;
	}
	
	public Double getLongitudCaprinoCentral() {
		return longitudCaprinoCentral;
	}
	
	public void setLongitudCaprinoCentral(Double longitudCaprinoCentral) {
		this.longitudCaprinoCentral = longitudCaprinoCentral;
	}
	
	public Double getLatitudEquinoCentral() {
		return latitudEquinoCentral;
	}
	
	public void setLatitudEquinoCentral(Double latitudEquinoCentral) {
		this.latitudEquinoCentral = latitudEquinoCentral;
	}
	
	public Double getLongitudEquinoCentral() {
		return longitudEquinoCentral;
	}
	
	public void setLongitudEquinoCentral(Double longitudEquinoCentral) {
		this.longitudEquinoCentral = longitudEquinoCentral;
	}
	
	public Double getLatitudOvinoCentral() {
		return latitudOvinoCentral;
	}
	
	public void setLatitudOvinoCentral(Double latitudOvinoCentral) {
		this.latitudOvinoCentral = latitudOvinoCentral;
	}
	
	public Double getLongitudOvinoCentral() {
		return longitudOvinoCentral;
	}
	
	public void setLongitudOvinoCentral(Double longitudOvinoCentral) {
		this.longitudOvinoCentral = longitudOvinoCentral;
	}
	
	public Double getLatitudPorcinoCentral() {
		return latitudPorcinoCentral;
	}
	
	public void setLatitudPorcinoCentral(Double latitudPorcinoCentral) {
		this.latitudPorcinoCentral = latitudPorcinoCentral;
	}
	
	public Double getLongitudPorcinoCentral() {
		return longitudPorcinoCentral;
	}

	public void setLongitudPorcinoCentral(Double longitudPorcinoCentral) {
		this.longitudPorcinoCentral = longitudPorcinoCentral;
	}
	
}
