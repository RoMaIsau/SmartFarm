package ar.edu.unlam.tallerweb1.modelo;

public class ServerLocation {
	private float latitude;	
	private float longitude;
	int value;
	
	public ServerLocation(float latitude,float longitude,int value){
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
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
}