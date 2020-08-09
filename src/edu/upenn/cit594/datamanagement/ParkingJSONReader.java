package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.ParkingData;

public class ParkingJSONReader implements ParkingReader{
	protected String parkingFilename;
	
	public ParkingJSONReader(String parkingFilename) {
		this.parkingFilename = parkingFilename;	
	}

	@Override
	public List<ParkingData> readAllParkings() {
		// TODO Auto-generated method stub
		return null;
	}

}
