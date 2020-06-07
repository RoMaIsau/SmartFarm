package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private ServerLocation serverLocation;
	private String locName;
	
	public Location(ServerLocation serverLocation,String locName){
		this.serverLocation=serverLocation;
		this.locName=locName;
	}
	
	public ServerLocation getServerLocation() {
		return serverLocation;
	}
	
	public void setServerLocation(ServerLocation serverLocation) {
		this.serverLocation = serverLocation;
	}
	
	public String getLocName() {
		return locName;
	}
	
	public void setLocName(String locName) {
		this.locName = locName;
	}
}