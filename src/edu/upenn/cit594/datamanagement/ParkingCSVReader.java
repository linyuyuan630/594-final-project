package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.ParkingData;

public class ParkingCSVReader implements ParkingReader{
	protected String parkingFilename;
	
	public ParkingCSVReader(String parkingFilename) {
		this.parkingFilename = parkingFilename;	
	}

	@Override
	public List<ParkingData> readAllParkings() {
		// TODO Auto-generated method stub
		return null;
	}

}
