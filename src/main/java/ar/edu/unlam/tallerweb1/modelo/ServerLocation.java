package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServerLocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private float latitude;	
	private float longitude;
	private long value;
	
	public ServerLocation(float latitude,float longitude,long value){
		this.latitude=latitude;
		this.longitude=longitude;
		this.value=value;
	}
	
	public float getLatitude() {
		return latitude;
	}
	
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	public float getLongitude() {
		return longitude;
	}
	
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	public long getValue() {
		return value;
	}
	
	public void setValue(long value) {
		this.value = value;
	}
	
	public float setearLatitudAleatorea() {
		float n = (float) ((float) (Math.random() * (34.620000 - 34.610000)) + 34.610000);
		return this.latitude = n;
	}
	
	public float setearLongitudAleatorea() {
		float n = (float) ((float) (Math.random() * (58.650000 - 58.680000)) + 58.680000);
		return this.longitude = n;
	}
	
}