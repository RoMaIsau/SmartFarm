package ar.edu.unlam.tallerweb1.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javax.persistence.Transient;


@Entity
public class CronogramaDeAlimentacion {

	public enum EstadoCronograma {
		PENDIENTE,
		COMPLETO,
		VENCIDO
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate fecha;
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

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalDate getFecha() {
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

	@Transient //Se anota como transient para que Hibernate lo ignore a la hora de mapear
	public LocalDateTime getFechaHora() {
		return LocalDateTime.of(this.fecha, this.horario);
	}
}