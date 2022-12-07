package de.niclasjanssen.studycasedb;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Station {
	
	@Id
	private String ds100;
	@Column
	private String name;
	@Column
	private double laenge;
	@Column
	private double breite;
	@Column 
	private String verkehr; 
	
	
	public String getVerkehr() {
		return verkehr;
	}
	public void setVerkehr(String verkehr) {
		this.verkehr = verkehr;
	}
	public String getDs100() {
		return ds100;
	}
	public void setDs100(String ds100) {
		this.ds100 = ds100;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLaenge() {
		return laenge;
	}
	public void setLaenge(double laenge) {
		this.laenge = laenge;
	}
	public double getBreite() {
		return breite;
	}
	public void setBreite(double breite) {
		this.breite = breite;
	}

}
