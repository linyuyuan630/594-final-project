package edu.upenn.cit594.ui;

import java.io.IOException;
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
		}
	}
	
	private void totalPopulationforAllZiPCodes() {
		System.out.println(populationProcessor.totalPopulation());
	}
	
	private void totalFinesPerCapita() throws IOException {
		Map<String, Double> tm = parkingViolationProcessor.TotalFinePerCapita();
		for (Entry<String, Double> entry : tm.entrySet()) {
		     System.out.println(entry.getKey() + ":  " + entry.getValue());
		}
	}
	
	
	private void averageMarketValue() {
		System.out.print("Enter a zip code: ");
		String zipCode = in.next();
		//logging
		Logger.getInstance().log(System.currentTimeMillis()+" "+"user's input zipcode:"+zipCode);
		double averageMktValue = propertyProcessor.averageMarketValue(zipCode);
		//The average residential market value that your program displays must be ​truncated​ to an integer (not rounded!), 
		//and your program should display 0 if there are no homes in that ZIP Code listed in the properties input file.
		//Your program must not write ​any​ other information to the console.​
		System.out.println((int)averageMktValue);
//		System.out.println("The avarage market value of " + zipCode + " is : " + (int)averageMktValue);
	}
	
	
	private void averageTotalLivableArea() {
		System.out.print("Enter a zip code: ");
		String zipCode = in.next();
		//logging
		Logger.getInstance().log(System.currentTimeMillis()+" "+"user's input zipcode:"+zipCode);
		double averageTLA = propertyProcessor.averageTotalLivableArea(zipCode);
		//The average residential total livable area must be displayed as a truncated integer, 
		//and your program should display 0 if there are no homes in that ZIP Code listed in the properties input file.
		//Your program must not write ​any​ other information to the console.​
		System.out.println((int)averageTLA);
//		System.out.println("The avarage total livable area of " + zipCode + " is : " + averageTLA);
	}
	
	private void totalResidentialMarketValuePerCapita() throws IOException {
		System.out.print("Enter a zip code: ");
		String zipCode = in.next();
		//logging
		Logger.getInstance().log(System.currentTimeMillis()+" "+"user's input zipcode:"+zipCode);
		double mktValuePerCapita = propertyProcessor.totalResidentialMktValuePerCapita(zipCode);
//		System.out.println(mktValuePerCapita);
		System.out.println((int)mktValuePerCapita);
//		System.out.println("Total Residential Market Value Per Capita of " + zipCode + " is : " + mktValuePerCapita);
	}
	
	private void correlationCoefficientOfFineAndMarketValue() throws IOException {
		double fineMktValueCorrelation = propertyProcessor.fineMktValueCorrelation();
		System.out.println("cross-correlation coefficient between fine per capita and residential "
				+ "market value per Capita for all zip codes  is : " + fineMktValueCorrelation);
	}

}
