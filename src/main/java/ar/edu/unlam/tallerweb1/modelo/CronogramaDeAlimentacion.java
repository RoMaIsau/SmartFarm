package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CronogramaDeAlimentacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date fecha;
	private Integer cantidad;
	private Date horario;
	@ManyToOne
	private PlanAlimentario planAlimentario;
	@ManyToOne
	private Alimento alimento;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public Date getHorario() {
		return horario;
	}

	public void setPlanAlimentario(PlanAlimentario planAlimentario) {
		this.planAlimentario = planAlimentario;
	}

	public PlanAlimentario getPlanAlimentario() {
		return planAlimentario;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public Alimento getAlimento() {
		return alimento;
	}
}
