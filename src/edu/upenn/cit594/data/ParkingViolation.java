package edu.upenn.cit594.data;

/**
 * Parking Violation data object
 * @author zhongliu
 *
 */
public class ParkingViolation {
	private Integer fine;
	private String zipCode;
	private String state;
	
	public ParkingViolation(Integer fine, String zipCode, String state) {
		this.fine = fine;
		this.zipCode = zipCode;
		this.state = state;
		
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public Integer getFine() {
		return fine;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	

}
