package ar.edu.unlam.tallerweb1.modelo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class AnimalDeGranja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private TipoAnimal tipo;

	@ManyToOne
	private Raza raza;

	@ManyToOne
	private Genero genero;

	private Calendar fechaNacimiento;

	private Double peso;

	@OneToOne(cascade= {CascadeType.ALL})
	private HistoriaClinica historia;

	@ManyToMany(fetch=FetchType.EAGER)
	private List<Vacuna> vacunasParaAplicar=new ArrayList<Vacuna>();

	@Column(unique = true)
	private String identificadorGps;

	@ManyToOne(fetch=FetchType.LAZY)
	private Corral corral;

	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public HistoriaClinica getHistoria() {
		return historia;
	}

	public void setHistoria(HistoriaClinica historia) {
		this.historia = historia;
	}

	public List<Vacuna> getVacunasParaAplicar() {
		return vacunasParaAplicar;
	}

	public void setVacunasParaAplicar(List<Vacuna> vacunasParaAplicar) {
		this.vacunasParaAplicar = vacunasParaAplicar;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public TipoAnimal getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoAnimal tipo) {
		this.tipo = tipo;
	}

	public void setRaza(Raza raza) {
		this.raza = raza;		
	}
	
	public Raza getRaza() {
		return raza;
	}
	
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	public Genero getGenero() {
		return genero;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public void setIdentificadorGps(String identificadorGps) {
		this.identificadorGps = identificadorGps;
	}

	public String getIdentificadorGps() {
		return identificadorGps;
	}

	public void setCorral(Corral corral) {
		this.corral = corral;
	}

	public Corral getCorral() {
		return corral;
	}

	@Override
    public boolean equals(Object objeto) {

        boolean iguales = (this == objeto);

        if (! iguales && (objeto != null) && this.getClass().isAssignableFrom(objeto.getClass())) {

            AnimalDeGranja otroAnimal = AnimalDeGranja.class.cast(objeto);

            iguales = (this.id != null) && (otroAnimal.id != null) &&
                      this.id.equals(otroAnimal.id);
        }
        return iguales;
    }

	@Override
    public int hashCode() {

        return this.id != null ? this.id.hashCode() : super.hashCode();
    }
}
