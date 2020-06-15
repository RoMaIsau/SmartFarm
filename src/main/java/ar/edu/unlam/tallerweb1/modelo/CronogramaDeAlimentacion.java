package ar.edu.unlam.tallerweb1.modelo;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class CronogramaDeAlimentacion {

	public enum EstadoCronograma {
		PENDIENTE,
		COMPLETO
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date fecha;
	private Integer cantidad;
	private LocalTime horario;
	@ManyToOne
	private PlanAlimentario planAlimentario;
	@ManyToOne
	private Alimento alimento;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EstadoCronograma estado;

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

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public LocalTime getHorario() {
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

	public EstadoCronograma getEstado() {
		return estado;
	}

	public void setEstado(EstadoCronograma estado) {
		this.estado = estado;
	}
}