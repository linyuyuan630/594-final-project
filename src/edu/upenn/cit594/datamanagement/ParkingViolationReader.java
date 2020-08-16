package edu.upenn.cit594.datamanagement;

import java.io.IOException;
import java.util.List;

import edu.upenn.cit594.data.ParkingViolation;

public abstract class ParkingViolationReader {	
	
	/**
	 * get a list of parking violations
	 * @return
	 */
	public abstract List<ParkingViolation> readAllParkingViolations();
	
	/**
	 * to get a right version of ParkingViolation reader based on file format.  
	 * @param file
	 * @return ParkingViolationReader, null if file extension is neither "csv" nor "json"
	 * @throws IOException 
	 */
	public static ParkingViolationReader getInstance(String parkingFileFormat, String fileName) throws IOException {
		if (fileName == null) {
			return null;
		}
		//removed following. should use given file format from user. 
//		String fileExtension = "";
//		int i = fileName.lastIndexOf('.');
//		if (i >= 0) { 
//			fileExtension = fileName.substring(i+1); 
//			}
		
		if (parkingFileFormat.equalsIgnoreCase("csv")) {
			return ParkingViolationCSVReader.getInstance(fileName);
		}
		else if (parkingFileFormat.equalsIgnoreCase("json")) {
			return ParkingViolationJSONReader.getInstance(fileName);
		}
		return null;
		
	}

}
