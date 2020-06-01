package ar.edu.unlam.tallerweb1.modelo;

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
	
	private Double gastosAlimenticios;
	private Double gastosTecnologicos;
	private Double gastosEmpresariales;
	private Double gastosMedicos;
	private Double gastosTotal;
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
	public Double getGastosAlimenticios() {
		return gastosAlimenticios;
	}
	public void setGastosAlimenticios(Double gastosAlimenticios) {
		this.gastosAlimenticios = gastosAlimenticios;
	}
	public Double getGastosTecnologicos() {
		return gastosTecnologicos;
	}
	public void setGastosTecnologicos(Double gastosTecnologicos) {
		this.gastosTecnologicos = gastosTecnologicos;
	}
	public Double getGastosEmpresariales() {
		return gastosEmpresariales;
	}
	public void setGastosEmpresariales(Double gastosEmpresariales) {
		this.gastosEmpresariales = gastosEmpresariales;
	}
	public Double getGastosMedicos() {
		return gastosMedicos;
	}
	public void setGastosMedicos(Double gastosMedicos) {
		this.gastosMedicos = gastosMedicos;
	}
	public Double getGastosTotal() {
		return gastosTotal;
	}
	public void setGastosTotal(Double gastosTotal) {
		this.gastosTotal = gastosTotal;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
}
