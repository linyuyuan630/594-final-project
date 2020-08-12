package edu.upenn.cit594.data;

public class ParkingViolation {
	private int fine;
	private String zipCode;
	
	public ParkingViolation(int fine, String zipCode) {
		this.fine = fine;
		this.zipCode = zipCode;
		
	}
	
	public String getZipCode() {
		return "";
	}
	
	public int getFine() {
		return -1;
	}

}
