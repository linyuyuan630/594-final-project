package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.ParkingData;

/**
 * the reader reads all the entries in the property file in csv format and returns
 * an ArrayList contains all of the entries
 */

public class ParkingCSVReader implements ParkingReader{
	protected String parkingFilename;
	
	public ParkingCSVReader(String parkingFilename) {
		this.parkingFilename = parkingFilename;	
	}

	@Override
	public List<ParkingData> readAllParkings() {
		List<ParkingData> allParkings = new ArrayList<ParkingData>();
		try {
			FileReader fr = new FileReader(parkingFilename); 
	        BufferedReader br = new BufferedReader(fr); 
	        String line = br.readLine();
			while (line != null) {
				String[] lineInfo = line.split(",");
				String fine = lineInfo[1];
				String zipCode = lineInfo[lineInfo.length - 1];
				ParkingData parkingEntry = new ParkingData(fine, zipCode);
				allParkings.add(parkingEntry);
				line = br.readLine();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return allParkings;	
	}

}
