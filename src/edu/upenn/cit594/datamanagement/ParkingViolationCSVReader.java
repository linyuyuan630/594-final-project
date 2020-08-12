package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.ParkingViolation;

public class ParkingViolationCSVReader extends ParkingViolationReader{
	List<ParkingViolation> parkingViolations;
	
	public ParkingViolationCSVReader(String parkingFilename) {
		this.loadParkingViolations(parkingFilename);
	}
	
	private void loadParkingViolations(String parkingFilename) {
		//load data from input file to parkingViolations list. 
	}

	@Override
	public List<ParkingViolation> readAllParkingViolations() {
		// TODO Auto-generated method stub
		return null;
	}
}
