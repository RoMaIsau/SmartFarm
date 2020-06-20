package ar.edu.unlam.tallerweb1.modelo;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SignosVitales {
	
	
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		private double temperatura;
		
		private double frecuenciaCardiaca;
		
		private double frecuenciaRespiratoria;
		
		private double pulso;
		
		private Date fecha;
		
		

		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public double getTemperatura() {
			return temperatura;
		}

		public void setTemperatura(double temperatura) {
			this.temperatura = temperatura;
		}

		public double getFrecuenciaCardiaca() {
			return frecuenciaCardiaca;
		}

		public void setFrecuenciaCardiaca(double frecuenciaCardiaca) {
			this.frecuenciaCardiaca = frecuenciaCardiaca;
		}

		public double getFrecuenciaRespiratoria() {
			return frecuenciaRespiratoria;
		}

		public void setFrecuenciaRespiratoria(double frecuenciaRespiratoria) {
			this.frecuenciaRespiratoria = frecuenciaRespiratoria;
		}

		public double getPulso() {
			return pulso;
		}

		public void setPulso(double pulso) {
			this.pulso = pulso;
		}
		
		

}
