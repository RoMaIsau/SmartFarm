package ar.edu.unlam.tallerweb1.modelo;

public class Location {
	ServerLocation serverLocation;
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