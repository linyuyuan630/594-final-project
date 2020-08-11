package edu.upenn.cit594.data;

/**
 * 
 * @author yuyuan.lin
 * Each parking entry is an instance of ParkingData
 *
 */

public class ParkingData {
	private String fine;
	private String zipCode;
	
	public ParkingData(String fine, String zipCode) {
		this.fine = fine;
		this.zipCode = zipCode;
		
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public String getFine() {
		return zipCode;
	}

}
