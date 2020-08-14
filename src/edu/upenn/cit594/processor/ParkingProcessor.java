package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.datamanagement.ParkingReader;



public class ParkingProcessor {
	
	private ParkingReader parkingViolationReader;
	private List<ParkingViolation> parkingViolationList;
	private String populationFilename;
	
	public ParkingProcessor(String parkingFilename, String populationFilename) {
		parkingViolationReader = ParkingReader.getInstance(parkingFilename);
		parkingViolationList = parkingViolationReader.readAllParkingViolations();
		this.populationFilename = populationFilename;
		
	}
	
    /**
     * this is a helper method
     * @return
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
					}
				else {
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
	 * @return
	 */
	
	public Map<String, Double> TotalFinePerCapita() {
		Map<String, Double> finePerCapita = new TreeMap<String, Double>();
		PopulationProcessor populations = new PopulationProcessor(populationFilename);
		Map<String, Integer> TotalFines = totalFineEachArea();
		double averageFine = 0;
		for (String i : TotalFines.keySet()) {
			averageFine = TotalFines.get(i) * 1.0 / populations.totalPopulationInZipCode(i);
			finePerCapita.put(i, averageFine);
		}
		

		return finePerCapita;
	}
	

}