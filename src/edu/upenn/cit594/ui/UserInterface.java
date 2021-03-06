package edu.upenn.cit594.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.ParkingViolationProcessor;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.PropertyProcessor;


public class UserInterface {
	private ParkingViolationProcessor parkingViolationProcessor;
	private PropertyProcessor propertyProcessor;
	private PopulationProcessor populationProcessor;
	Scanner in;
	
	
	public UserInterface(ParkingViolationProcessor parkingViolationProcessor, PropertyProcessor propertyProcessor, 
			PopulationProcessor populationProcessor) {
		this.parkingViolationProcessor = parkingViolationProcessor;
		this.propertyProcessor = propertyProcessor;
		this.populationProcessor = populationProcessor;
		in = new Scanner(System.in);
	}
	
	public void start() {
		this.run();
	}
	
	public void run() {
		System.out.print("\nEnter 0: exit \n"
				+ "Enter 1: Total Population for All ZIP Codes \n"
				+ "Enter 2: Total Fines Per Capita \n"
				+ "Enter 3: Average Market Value \n"
				+ "Enter 4: Average Total Livable Area \n"
				+ "Enter 5: Total Residential Market Value Per Capita \n"
				+ "Enter 6: Cross correlation Coefficient betWeen Fine And MarketValue Per Capita \n");
		
		try {
			int choice = in.nextInt();
			//logging
			Logger.getInstance().log(System.currentTimeMillis()+" "+"user's choice:"+choice);
			if (choice == 1) {
				totalPopulationforAllZiPCodes();
				this.run();
			}
			else if (choice == 2) {
				totalFinesPerCapita();
				this.run();
			}
			else if (choice == 3) {
				averageMarketValue();
				this.run();
			}
			else if (choice == 4) {
				averageTotalLivableArea();
				this.run();
			}
			else if (choice == 5) {
				totalResidentialMarketValuePerCapita();
				this.run();
			}
			else if (choice == 6) {
				correlationCoefficientOfFineAndMarketValue();
				this.run();
			}else if (choice == 0) {// simplify return
				return;
			}else{
				throw new Exception("Wrong input, please enter a number between 0 to 6");
			}
			in.close();
		}catch(Exception e) {
			System.out.println("Wrong input, please re-run the program, enter a number between 0 to 6. ");
			in.close();
//			e.printStackTrace();
		}
	}
	
	private Integer totalPopolationCache = null; 
	private void totalPopulationforAllZiPCodes() {
		if(totalPopolationCache==null) { //memoization/cache assuming data file won't be changed during this program run time or it doesn't matter
//			System.out.println("totalPopolationCache cache miss");
			totalPopolationCache = populationProcessor.totalPopulation();
		}
		System.out.println(totalPopolationCache);
	}
	
	private Map<String, Double> totalFinesPerCapitaMapCache = null;
	//The ZIP Codes must be written to the screen in ascending numerical order 
	//and the total fines per capita must be displayed with a precision of four digits after the decimal point, 
	//as in the example above. The values must be ​truncated​, not rounded, e.g. 1.23459999 should be shown as 1.2345 
	//and not 1.2346. Additionally, your program should display trailing zeros that occur at the end of the four digits 
	//after the decimal point, e.g. it should show 1.2300 and not just 1.23.
	private void totalFinesPerCapita() throws IOException {
		if(totalFinesPerCapitaMapCache==null) {//memoization
//			System.out.println("totalFinesPerCapitaMapCache cache miss");
			totalFinesPerCapitaMapCache = parkingViolationProcessor.TotalFinePerCapita();
		}
		for (Entry<String, Double> entry : totalFinesPerCapitaMapCache.entrySet()) {
		     System.out.println(entry.getKey() + ":  " + this.displayValueOfFinePerCapita(entry.getValue()));
		}
	}
	
	/**
	 * Helper method. Can be moved out to this class. But given this project is small, leave it here for now. 
	 * to get a displayable value per requested in the spec. 
	 * @param origFine original value in Double
	 * @return formated value in String
	 */
	private String displayValueOfFinePerCapita(Double origFine) {
		if(origFine==null) return null;
		
		String orig = String.valueOf(origFine);
//		System.out.println("orig="+orig);
		String doubleInfo[] = orig.split("\\.");
		if(doubleInfo.length<1) return null;
		String intPart = doubleInfo[0];
		String fracPart="";
		if(doubleInfo.length>1) {
			fracPart = doubleInfo[1];
		}
		if(fracPart.length()<4) {
			fracPart = fracPart+"0000".substring(0,4-fracPart.length());
		}else {
			fracPart = fracPart.substring(0,4);
		}
		return intPart+"."+fracPart;
	}
	
	private HashMap<String,Double> averageMktValueCacheMap = new HashMap<>();
	/**
	 * The average residential market value that your program displays must be ​truncated​ to an integer (not rounded!), 
	 * and your program should display 0 if there are no homes in that ZIP Code listed in the properties input file.
	 */
	private void averageMarketValue() {
		System.out.print("Enter a zip code: ");
		String zipCode = in.next();
		//logging
		Logger.getInstance().log(System.currentTimeMillis()+" "+"user's input zipcode:"+zipCode);
		Double averageMktValueCache = averageMktValueCacheMap.get(zipCode);
		if(averageMktValueCache==null) {//memoization
//			System.out.println("averageMktValueCache cache miss, zip="+zipCode);
			averageMktValueCache = propertyProcessor.averageMarketValue(zipCode);
			averageMktValueCacheMap.put(zipCode, averageMktValueCache);
		}
		//The average residential market value that your program displays must be ​truncated​ to an integer (not rounded!), 
		//and your program should display 0 if there are no homes in that ZIP Code listed in the properties input file.
		//Your program must not write ​any​ other information to the console.​
		System.out.println((int)averageMktValueCache.doubleValue());
//		System.out.println("The avarage market value of " + zipCode + " is : " + (int)averageMktValue);
	}
	
	private HashMap<String,Double> averageTLACacheMap = new HashMap<>();
	/**
	 * Your program should then display (to the console) the average total livable area for residences in that ZIP Code, 
	 * i.e. the sum of the total livable areas for all homes in the ZIP Code divided by the number of homes.
	 * Note that you are dividing by the number of homes in that ZIP Code as listed in the property values input file, 
	 * and not the population of that ZIP Code from the population input file.
	 * The average residential total livable area must be displayed as a truncated integer, 
	 * and your program should display 0 if there are no homes in that ZIP Code listed in the properties input file.
	 */
	private void averageTotalLivableArea() {
		System.out.print("Enter a zip code: ");
		String zipCode = in.next();
		//logging
		Logger.getInstance().log(System.currentTimeMillis()+" "+"user's input zipcode:"+zipCode);
		
		Double averageTLACache = averageTLACacheMap.get(zipCode);
		
		if(averageTLACache==null) {//memoization
//			System.out.println("averageTLACache cache miss, zip="+zipCode);
			averageTLACache = propertyProcessor.averageTotalLivableArea(zipCode);
			averageTLACacheMap.put(zipCode, averageTLACache);
		}
		//The average residential total livable area must be displayed as a truncated integer, 
		//and your program should display 0 if there are no homes in that ZIP Code listed in the properties input file.
		//Your program must not write ​any​ other information to the console.​
//		System.out.println(averageTLA);
		System.out.println((int)averageTLACache.doubleValue());
//		System.out.println("The avarage total livable area of " + zipCode + " is : " + averageTLA);
	}
	
	
	private HashMap<String,Double> totalMktValuePerCapitaCacheMap = new HashMap<>();
	/**
	 * Your program should then display (to the console) the total market value per capita for that ZIP Code, 
	 * i.e. the total market value for all residences in the ZIP Code divided by the population of that ZIP Code, 
	 * as provided in the population input file.
	 * The residential market value per capita must be displayed as a truncated integer, 
	 * and your program should display 0 if the total residential market value for the ZIP Code is 0, 
	 * if the population of the ZIP Code is 0, or if the user enters a ZIP Code that is not listed in the population input file.
	 * @throws IOException
	 */
	private void totalResidentialMarketValuePerCapita() throws IOException {
		System.out.print("Enter a zip code: ");
		String zipCode = in.next();
		//logging
		Logger.getInstance().log(System.currentTimeMillis()+" "+"user's input zipcode:"+zipCode);
		
		Double mktValuePerCapitaCache = totalMktValuePerCapitaCacheMap.get(zipCode);
		if(mktValuePerCapitaCache==null) {//cache
//			System.out.println("mktValuePerCapitaCache cache miss, zip="+zipCode);
			mktValuePerCapitaCache = propertyProcessor.totalResidentialMktValuePerCapita(zipCode);
			totalMktValuePerCapitaCacheMap.put(zipCode, mktValuePerCapitaCache);
		}
		
//		System.out.println(mktValuePerCapitaCache);
		System.out.println((int)mktValuePerCapitaCache.doubleValue());
//		System.out.println("Total Residential Market Value Per Capita of " + zipCode + " is : " + mktValuePerCapita);
	}
	
	private Double fineMktValueCorrelationCache = null;
	private void correlationCoefficientOfFineAndMarketValue() throws IOException {
		if(fineMktValueCorrelationCache==null) { //cache
//			System.out.println("fineMktValueCorrelationCache cache miss");
			fineMktValueCorrelationCache = propertyProcessor.fineMktValueCorrelation();
		}
		System.out.println("cross-correlation coefficient between fine per capita and residential "
				+ "market value per Capita for all zip codes  is : " + fineMktValueCorrelationCache);
	}

}
