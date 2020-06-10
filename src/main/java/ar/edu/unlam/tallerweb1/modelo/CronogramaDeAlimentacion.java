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
}
