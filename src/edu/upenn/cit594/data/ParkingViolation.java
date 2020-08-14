package edu.upenn.cit594.data;

public class ParkingViolation {
	private Integer fine;
	private String zipCode;
	
	public ParkingViolation(Integer fine, String zipCode) {
		this.fine = fine;
		this.zipCode = zipCode;
		
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public Integer getFine() {
		return fine;
	}

}
