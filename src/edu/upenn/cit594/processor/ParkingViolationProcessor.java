package edu.upenn.cit594.processor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.datamanagement.ParkingViolationReader;



public class ParkingViolationProcessor {
	
	private ParkingViolationReader parkingViolationReader;
	private List<ParkingViolation> parkingViolationList;
	private String populationFilename;
	
	public ParkingViolationProcessor(String parkingFileFormat,String parkingFilename, String populationFilename) throws IOException {
		parkingViolationReader = ParkingViolationReader.getInstance(parkingFileFormat, parkingFilename);
		parkingViolationList = parkingViolationReader.readAllParkingViolations();
		this.populationFilename = populationFilename;
		
	}
	
    /**
     * this is a helper method
     * @return total fine in each zipcode area
     */
	private Map<String, Integer> totalFineEachArea() {
		
		Map<String, Integer> locationParkingFines = new HashMap<String, Integer>();
		for (int i = 0; i < parkingViolationList.size(); i++ ) {
			String parkingViolationZipCode = parkingViolationList.get(i).getZipCode();
			Integer parkingFine = parkingViolationList.get(i).getFine();
			if (parkingViolationZipCode !=null && parkingFine != null) {
				 if(locationParkingFines.keySet().contains(parkingViolationZipCode)) {
					int totalFine = locationParkingFines.get(parkingViolationZipCode);
					totalFine = totalFine + parkingFine;
					locationParkingFines.put(parkingViolationZipCode, totalFine);
				}else {
					locationParkingFines.put(parkingViolationZipCode, parkingFine);
					}
				 }
			}
		/**
		for (Entry<String, Integer> entry : locationParkingFines.entrySet()) {
		     System.out.println(entry.getKey() + ":  " + entry.getValue());
		}
		**/
		return locationParkingFines;
	}
	
	/**
	 * 2. Total Fines Per Capita
	 * @return a map that includes total fine per capital by each zipcode
	 * @throws IOException 
	 */
	
	public Map<String, Double> TotalFinePerCapita() throws IOException {
		Map<String, Double> finePerCapita = new TreeMap<String, Double>();
		PopulationProcessor populations = new PopulationProcessor(populationFilename);
		Map<String, Integer> TotalFines = totalFineEachArea();
		double averageFine = 0;
		for (String zipCode : TotalFines.keySet()) {
//			System.out.println("zip="+zipCode+",total fine = "+TotalFines.get(zipCode)+",pop="+populations.totalPopulationInZipCode(zipCode));
			averageFine = TotalFines.get(zipCode) * 1.0 / populations.totalPopulationInZipCode(zipCode);
			finePerCapita.put(zipCode, averageFine);
		}
		

		return finePerCapita;
	}
	

}