package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GeneratorType;

@Entity
public class GanadoVacuno {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Calendar fechaNacimiento;
	private Double peso;
	private String frecuenciaCardiaca;
	
	//@OneToOne
	//private Dieta dieta;
	
	//@OneToMany
	//private List<Vacuna> vacunasAplicadas = new ArrayList<Vacuna>();
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Vacuna> vacunasParaAplicar= new ArrayList<Vacuna>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	public String getFrecuenciaCardiaca() {
		return frecuenciaCardiaca;
	}
	public void setFrecuenciaCardiaca(String frecuenciaCardiaca) {
		this.frecuenciaCardiaca = frecuenciaCardiaca;
	}
	/*public List<Vacuna> getVacunasAplicadas() {
		return vacunasAplicadas;
	}
	public void setVacunasAplicadas(List<Vacuna> vacunasAplicadas) {
		this.vacunasAplicadas = vacunasAplicadas;
	}*/
	public List<Vacuna> getVacunasParaAplicar() {
		return vacunasParaAplicar;
	}
	public void setVacunasParaAplicar(List<Vacuna> vacunasParaAplicar) {
		this.vacunasParaAplicar = vacunasParaAplicar;
	}
	
	
	
	
	/*public Dieta getDieta() {
		return dieta;
	}
	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}
	
	*/}
