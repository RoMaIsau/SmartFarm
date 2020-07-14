package ar.edu.unlam.tallerweb1.modelo;

import java.util.Comparator;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Enfermedad implements Comparable<Enfermedad> {

	// La anotacion id indica que este atributo es el utilizado como clave primaria de la entity, se indica que el valor es autogenerado.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String tratamientoA;
	
	private String tratamientoB;
	
	
	
	private Date inicioTratamiento;
	
	private Date finTratamiento;
	
	private Date fecha;
	
	@ManyToOne
    HistoriaClinica historia;


	



	public String getTratamientoA() {
		return tratamientoA;
	}

	public void setTratamientoA(String tratamientoA) {
		this.tratamientoA = tratamientoA;
	}

	public String getTratamientoB() {
		return tratamientoB;
	}

	public void setTratamientoB(String tratamientoB) {
		this.tratamientoB = tratamientoB;
	}

	
	public Date getInicioTratamiento() {
		return inicioTratamiento;
	}

	public void setInicioTratamiento(Date inicioTratamiento) {
		this.inicioTratamiento = inicioTratamiento;
	}

	public Date getFinTratamiento() {
		return finTratamiento;
	}

	public void setFinTratamiento(Date finTratamiento) {
		this.finTratamiento = finTratamiento;
	}

	public HistoriaClinica getHistoria() {
		return historia;
	}

	public void setHistoria(HistoriaClinica historia) {
		this.historia = historia;
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	

	@Override
	public int compareTo(Enfermedad e) {
		// TODO Auto-generated method stub
		 return e.nombre.compareTo(this.nombre);
	}
      
    
}
