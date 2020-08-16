package edu.upenn.cit594.processor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.datamanagement.ParkingViolationReader;


/**
 * 
 * @author zhongliu
 *
 */
public class ParkingViolationProcessor {
	
	private ParkingViolationReader parkingViolationReader;
	private List<ParkingViolation> parkingViolationList;
	private PopulationProcessor populationProcessor;
//	private String populationFilename;
	
	public ParkingViolationProcessor(String parkingFileFormat,String parkingFilename, PopulationProcessor populationProcessor) throws IOException {
		parkingViolationReader = ParkingViolationReader.getInstance(parkingFileFormat, parkingFilename);
		parkingViolationList = parkingViolationReader.readAllParkingViolations();
		this.populationProcessor = populationProcessor;
//		this.populationFilename = populationFilename;
		
	}
	
    /**
     * Return total fine in each zipcode area in given state
     * @param state, if null then include all state
     * @return total fine in each zipcode area
     */
	private Map<String, Integer> totalFineEachArea(String state) {
		
		Map<String, Integer> locationParkingFines = new HashMap<String, Integer>();
		ParkingViolation pViolation = null;
		for (int i = 0; i < parkingViolationList.size(); i++ ) {
			pViolation = parkingViolationList.get(i);
			String parkingViolationZipCode = pViolation.getZipCode();
			Integer parkingFine = pViolation.getFine();
			String pState = pViolation.getState();
			if(state!=null && !state.equalsIgnoreCase(pState)) { //not in the specific state, skip
				continue;
			}
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
	 * Ignore any parking violations in the input file for which the ZIP Code is unknown or 
	 * for which the vehicle’s license plate state is not “PA”, 
	 * and should not display any ZIP Code for which the total aggregate fines is 0 or for which the population is 0 or unknown, 
	 * e.g. if the ZIP Code is not listed in the population input file.
	 * @return a map that includes total fine per capital by each zipcode
	 * @throws IOException 
	 */
	
	public Map<String, Double> TotalFinePerCapita() throws IOException {
		Map<String, Double> finePerCapita = new TreeMap<String, Double>();
//		PopulationProcessor populations = new PopulationProcessor(populationFilename);
		Map<String, Integer> totalFines = totalFineEachArea("PA"); //only for PA
		double totalFine = 0;
		int totalPop = 0;
		for (String zipCode : totalFines.keySet()) {
//			System.out.println("zip="+zipCode+",total fine = "+TotalFines.get(zipCode)+",pop="+populations.totalPopulationInZipCode(zipCode));
			
			totalFine = totalFines.get(zipCode);
			totalPop = this.populationProcessor.totalPopulationInZipCode(zipCode);
//			System.out.println("total fine="+totalFine+",totalPop="+totalPop);
			if(totalFine>0 && totalPop>0) { //
				finePerCapita.put(zipCode, totalFine/totalPop);
			}
		}
		

		return finePerCapita;
	}
	

}