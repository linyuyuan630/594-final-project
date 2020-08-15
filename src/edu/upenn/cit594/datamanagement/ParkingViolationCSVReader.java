package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.logging.Logger;

public class ParkingViolationCSVReader extends ParkingViolationReader{
	List<ParkingViolation> allParkingViolations;
	
	
	private static ParkingViolationCSVReader reader;
	
	public static ParkingViolationCSVReader getInstance(String parkingFilename) throws IOException {
		if(reader==null) {
			reader = new ParkingViolationCSVReader(parkingFilename);
		}
		return reader;
	}
	
	private ParkingViolationCSVReader(String parkingFilename) throws IOException {
		if(allParkingViolations==null) {//optimization - assume the data set cannot be changed during run time
			this.loadParkingViolations(parkingFilename);
		}
	}
	
	private void loadParkingViolations(String parkingFilename) throws IOException {
		allParkingViolations = new ArrayList<ParkingViolation>();
		try {
			FileReader fr = new FileReader(parkingFilename); 
	        BufferedReader br = new BufferedReader(fr); 
	        //logging
			Logger.getInstance().log(System.currentTimeMillis()+" Openning file:"+parkingFilename);
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
			throw e;
		} 
	}

	@Override
	public List<ParkingViolation> readAllParkingViolations() {
		// TODO Auto-generated method stub
		return allParkingViolations;
	}
}
