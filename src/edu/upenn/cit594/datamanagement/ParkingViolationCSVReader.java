package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.ParkingViolation;

public class ParkingViolationCSVReader extends ParkingViolationReader{
	List<ParkingViolation> allParkingViolations;
	
	public ParkingViolationCSVReader(String parkingFilename) {
		this.loadParkingViolations(parkingFilename);
	}
	
	private void loadParkingViolations(String parkingFilename) {
		allParkingViolations = new ArrayList<ParkingViolation>();
		try {
			FileReader fr = new FileReader(parkingFilename); 
	        BufferedReader br = new BufferedReader(fr); 
	        String line = br.readLine();
			while (line != null) {
				String[] lineInfo = line.split(",");
				Integer fine = Integer.valueOf(lineInfo[1]);
				String zipCode = lineInfo[lineInfo.length - 1];
				//System.out.println(fine);
				//System.out.println(zipCode);
				ParkingViolation parkingEntry = new ParkingViolation(fine, zipCode);
				allParkingViolations.add(parkingEntry);
				line = br.readLine();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public List<ParkingViolation> readAllParkingViolations() {
		// TODO Auto-generated method stub
		return allParkingViolations;
	}
}
