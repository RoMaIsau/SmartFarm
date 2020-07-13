package ar.edu.unlam.tallerweb1.modelo;

import java.awt.geom.Path2D;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

@Entity
public class Corral {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	/**
	 * Decidimos ir por el OneToMany porque cada vez que queramos recuperar un corral tb
	 * vamos a querer tener sus vertices. No vamos a trabajar por separado con los vertices, no
	 * tienen sentido sin un corral
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("numero")
	private List<Vertice> vertices;

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public List<Vertice> getVertices() {
		return vertices;
	}
	public void setVertices(List<Vertice> vertices) {
		this.vertices = vertices;
	}

	@Transient
	public boolean contiene(double latitud, double longitud) {

		Path2D.Double poligono = new Path2D.Double();
		Vertice primerPunto = this.vertices.get(0);

		poligono.moveTo(primerPunto.getLongitud().doubleValue(), primerPunto.getLatitud().doubleValue());
		for (int i = 1; i < this.vertices.size() - 1; i++) {
			Vertice punto = this.vertices.get(i);
			poligono.lineTo(punto.getLongitud().doubleValue(), punto.getLatitud().doubleValue());
		}
		poligono.closePath();
		return poligono.contains(longitud, latitud);
	}
}
