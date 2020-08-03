package ar.edu.unlam.tallerweb1.modelo;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sintomas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private Long idAnimal;
    
    private Calendar fecha;
    
    
    private boolean Ulceras;
    
    private boolean anorexia;
    
    private boolean debilidad;
    
    private boolean bajaProduccionLeche;
    
    private boolean diarrea;
    
    private boolean conjuntivitis;
    
    private boolean salivacionEspumosa;
    
    private boolean tos;
    
    private boolean secrecionNasal;
    
    private boolean temperaturaElevada;
    
    private String tiempo;
    
    private Date fechaSignosVitales;
    
    

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	

	

	public boolean isTemperaturaElevada() {
		return temperaturaElevada;
	}

	public void setTemperaturaElevada(boolean temperaturaElevada) {
		this.temperaturaElevada = temperaturaElevada;
	}

	public boolean isUlceras() {
		return Ulceras;
	}

	public void setUlceras(boolean ulceras) {
		Ulceras = ulceras;
	}

	public boolean isAnorexia() {
		return anorexia;
	}

	public void setAnorexia(boolean anorexia) {
		this.anorexia = anorexia;
	}

	public boolean isDebilidad() {
		return debilidad;
	}

	public void setDebilidad(boolean debilidad) {
		this.debilidad = debilidad;
	}

	public boolean isBajaProduccionLeche() {
		return bajaProduccionLeche;
	}

	public void setBajaProduccionLeche(boolean bajaProduccionLeche) {
		this.bajaProduccionLeche = bajaProduccionLeche;
	}

	public boolean isDiarrea() {
		return diarrea;
	}

	public void setDiarrea(boolean diarrea) {
		this.diarrea = diarrea;
	}

	public boolean isConjuntivitis() {
		return conjuntivitis;
	}

	public void setConjuntivitis(boolean conjuntivitis) {
		this.conjuntivitis = conjuntivitis;
	}

	public boolean isSalivacionEspumosa() {
		return salivacionEspumosa;
	}

	public void setSalivacionEspumosa(boolean salivacionEspumosa) {
		this.salivacionEspumosa = salivacionEspumosa;
	}

	public boolean isTos() {
		return tos;
	}

	public void setTos(boolean tos) {
		this.tos = tos;
	}

	public boolean isSecrecionNasal() {
		return secrecionNasal;
	}

	public void setSecrecionNasal(boolean secrecionNasal) {
		this.secrecionNasal = secrecionNasal;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public Date getFechaSignosVitales() {
		return fechaSignosVitales;
	}

	public void setFechaSignosVitales(Date fechaSignosVitales) {
		this.fechaSignosVitales = fechaSignosVitales;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
	}
    
    

}
