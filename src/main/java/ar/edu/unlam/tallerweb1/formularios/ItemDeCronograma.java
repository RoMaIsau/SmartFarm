package ar.edu.unlam.tallerweb1.formularios;

import java.time.LocalDate;
import java.time.LocalTime;

import ar.edu.unlam.tallerweb1.modelo.Alimento;

public class ItemDeCronograma {
	
	private int cantidad;
	private LocalDate fecha;
	private Alimento alimento;
	private LocalTime horario;
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public LocalTime getHorario() {
		return horario;
	}

}
