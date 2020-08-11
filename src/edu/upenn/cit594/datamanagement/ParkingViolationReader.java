package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.ParkingViolation;

public abstract class ParkingViolationReader {	
	
	
	public abstract List<ParkingViolation> readAllParkingViolations();
	
	/**
	 * to get a right version of ParkingViolation reader based on file format.  
	 * @param file
	 * @return
	 */
	public static ParkingViolationReader getInstance(String file) {
		return null;
	}
	
	public int totalFinePerCapital() {
		return -1;
	}
	
	public int totalFinePerCapital(String zipCode) {
		return -1;
	}


}

