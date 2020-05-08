package ar.edu.unlam.tallerweb1.modelo;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Clase que modela el concepto de Usuario, la anotacion @Entity le avisa a hibernate que esta clase es persistible
// el paquete ar.edu.unlam.tallerweb1.modelo esta indicado en el archivo hibernateCOntext.xml para que hibernate
// busque entities en él
@Entity
public class Vacuna {

	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private int edadAplicacionMeses;
	private Calendar fechaAplicacion;
	private Boolean disponile;
	
	public Calendar getFechaAplicacion() {
		return fechaAplicacion;
	}
	public void setFechaAplicacion(Calendar fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombe) {
		this.nombre = nombe;
	}
	public int getEdadAplicacionMeses() {
		return edadAplicacionMeses;
	}
	public void setEdadAplicacionMeses(int edadAplicacion) {
		this.edadAplicacionMeses = edadAplicacion;
	}
	public Boolean getDisponile() {
		return disponile;
	}
	public void setDisponile(Boolean disponile) {
		this.disponile = disponile;
	}
	
	
	
}

