package ar.edu.unlam.tallerweb1.modelo;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Gastos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Usuario usuario;
	
	private String tipoDeGasto;
	private Double gasto;
	private String fecha;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getTipoDeGasto() {
		return tipoDeGasto;
	}
	public void setTipoDeGasto(String tipoDeGasto) {
		this.tipoDeGasto = tipoDeGasto;
	}
	public Double getGasto() {
		return gasto;
	}
	public void setGasto(Double gasto) {
		this.gasto = gasto;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
