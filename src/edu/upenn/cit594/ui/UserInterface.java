package edu.upenn.cit594.ui;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

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
		System.out.print("Enter 1: Total Population for All ZIP Codes \n"
				+ "Enter 2: Total Fines Per Capita \n"
				+ "Enter 3: Average Market Value \n"
				+ "Enter 4: Average Total Livable Area \n"
				+ "Enter 5: Total Residential Market Value Per Capita \n"
				+ "Enter 6: Cross correlation Coefficient betWeen Fine And MarketValue Per Capita \n");
		
		try {
			int choice = in.nextInt();
			if (choice == 1) {
				totalPopulationforAllZiPCodes();
			}
			else if (choice == 2) {
				totalFinesPerCapita();
			}
			else if (choice == 3) {
				averageMarketValue();
			}
			else if (choice == 4) {
				averageTotalLivableArea();
			}
			else if (choice == 5) {
				totalResidentialMarketValuePerCapita();
			}
			else if (choice == 6) {
				correlationCoefficientOfFineAndMarketValue();
			}else {
				throw new Exception("Wrong input, please enter a number between 1 to 6");
			}
			in.close();
		}catch(Exception e) {
			System.out.println("Wrong input, please re-run the program, enter a number between 1 to 6. ");
			in.close();
		}
	}
	
	private void totalPopulationforAllZiPCodes() {
		System.out.println(populationProcessor.totalPopulation());
	}
	
	private void totalFinesPerCapita() {
		Map<String, Double> tm = parkingViolationProcessor.TotalFinePerCapita();
		for (Entry<String, Double> entry : tm.entrySet()) {
		     System.out.println(entry.getKey() + ":  " + entry.getValue());
		}
	}
	
	
	private void averageMarketValue() {
		System.out.print("Enter a zip code: ");
		String zipCode = in.next();
		double averageMktValue = propertyProcessor.averageMarketValue(zipCode);
		System.out.println("The avarage market value of " + zipCode + " is : " + averageMktValue);
	}
	
	
	private void averageTotalLivableArea() {
		System.out.print("Enter a zip code: ");
		String zipCode = in.next();
		double averageTLA = propertyProcessor.averageTotalLivableArea(zipCode);
		System.out.println("The avarage total livable area of " + zipCode + " is : " + averageTLA);
	}
	
	private void totalResidentialMarketValuePerCapita() {
		System.out.print("Enter a zip code: ");
		String zipCode = in.next();
		double mktValuePerCapita = propertyProcessor.totalResidentialMktValuePerCapita(zipCode);
		System.out.println("Total Residential Market Value Per Capita of " + zipCode + " is : " + mktValuePerCapita);
	}
	
	private void correlationCoefficientOfFineAndMarketValue() {
		double fineMktValueCorrelation = propertyProcessor.fineMktValueCorrelation();
		System.out.println("cross-correlation coefficient between fine per capita and residential "
				+ "market value per Capita for all zip codes  is : " + fineMktValueCorrelation);
	}

}
