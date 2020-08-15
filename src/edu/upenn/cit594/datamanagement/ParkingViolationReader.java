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
	public static ParkingViolationReader getInstance(String fileName) {
		if (fileName == null) {
			return null;
		}
		String fileExtension = "";
		int i = fileName.lastIndexOf('.');
		if (i >= 0) { 
			fileExtension = fileName.substring(i+1); 
			}
		
		if (fileExtension.toLowerCase().equals("csv")) {
			return new ParkingViolationCSVReader(fileName);
		}
		else if (fileExtension.toLowerCase().equals("json")) {
			return new ParkingViolationJSONReader(fileName);
		}
		return null;
		
	}
	
	/**
	public int totalFinePerCapital() {
		return -1;
	}
	
	public int totalFinePerCapital(String zipCode) {
		return -1;
	}
	**/


}