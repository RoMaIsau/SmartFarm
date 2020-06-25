package ar.edu.unlam.tallerweb1.modelo;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	
	
	
	
}
