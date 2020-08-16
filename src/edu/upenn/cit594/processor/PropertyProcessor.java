package edu.upenn.cit594.processor;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.PropertyReader;

/**
 * 
 * @author zhongliu
 *
 */
public class PropertyProcessor {
	private ParkingViolationProcessor parkingProcessor;
	private PopulationProcessor populationProcessor;
	private PropertyReader propertyReader;
	private List<Property> propertyList;
//	private String populationFilename;
//	private String parkingFilename;
	
	public PropertyProcessor(String propertyFilename, ParkingViolationProcessor parkingProcesso, PopulationProcessor populationProcessor) throws IOException {
		propertyReader = PropertyReader.getInstance(propertyFilename);
		propertyList = propertyReader.readAllProperties();
		
		this.parkingProcessor = parkingProcesso;
		this.populationProcessor = populationProcessor;
		
//		this.populationFilename = populationFilename;
//		this.parkingFilename = parkingFilename;
	}
		
	/**
	 * 3. Average Market Value
	 * @param zipCode
	 * @return
	 */
	public double averageMarketValue(String zipCode) {
		Average averageMktValue = new Average(new AverageMarketValue());
		return averageMktValue.computeStrategy(zipCode, propertyList);

	}
	
	/**
	 * 4. Average Total Livable Area
	 * TODO: Use the Strategy design pattern to implement the average residential market
	 *  value (Step #3) and average residential total living area (Step #4) features. 
	 *  Think about what similarities these two features have and how you can specify 
	 *  different strategies for each.
	 * @param zipCode
	 * @return
	 */
	public double averageTotalLivableArea(String zipCode) {
		Average averageLivAreaValue = new Average(new AverageTotalLivableArea());
		return averageLivAreaValue.computeStrategy(zipCode, propertyList);
	}
	

	/**
	 * 5. Total Residential Market Value Per Capita
	 * @return
	 * @throws IOException 
	 */
	public double totalResidentialMktValuePerCapita(String zipCode) throws IOException {
//		PopulationProcessor populations = new PopulationProcessor(populationFilename);
		int populationInZipCode = this.populationProcessor.totalPopulationInZipCode(zipCode);
		double totalValue = 0;
		for (int i = 0; i < propertyList.size(); i++ ) {
			String propertyZipCode = propertyList.get(i).getZipCode();
			Double propertyMktValue = propertyList.get(i).getMarketValue();
			if (propertyMktValue != null && propertyZipCode != null) {
				if (zipCode.equalsIgnoreCase(propertyZipCode)) {
					totalValue += propertyMktValue;
				}	
			}
			
		}
		if (populationInZipCode == 0) {
			return 0;
		}
		else {
			return totalValue *1.0 / populationInZipCode;
	
		}
	}
	
	/**
	 * 6. cross-correlation coefficient between fine per capita and residential market value per Capita for all zip codes
	 * @throws IOException 
	 */

	public double fineMktValueCorrelation() throws IOException {
		Map<String, Double> propertyMktValueOfZipCode = new HashMap<String, Double>();
		for (int i = 0; i < propertyList.size(); i++ ) {
			String propertyZipCode = propertyList.get(i).getZipCode();
			Double propertyMktValue = propertyList.get(i).getMarketValue();
			if (propertyMktValue != null && propertyZipCode != null) {
				if(propertyMktValueOfZipCode.keySet().contains(propertyZipCode)) {
					double totalValue = propertyMktValueOfZipCode.get(propertyZipCode);
					totalValue = totalValue + Double.valueOf(propertyMktValue);
					propertyMktValueOfZipCode.put(propertyZipCode, totalValue);
				}
				else {
					propertyMktValueOfZipCode.put(propertyZipCode, propertyMktValue);
				}
			}
		}
		Map<String, Double> mktValuePerCapita = new HashMap<String, Double>();
		for (String code : propertyMktValueOfZipCode.keySet()) {
//			PopulationProcessor populations = new PopulationProcessor(populationFilename);
			int populationInZipCode = this.populationProcessor.totalPopulationInZipCode(code);
			mktValuePerCapita.put(code, propertyMktValueOfZipCode.get(code) / populationInZipCode * 0.1);
		}
//		ParkingViolationProcessor parkingViolations = new ParkingViolationProcessor (parkingFilename, populationFilename);
		Map<String, Double> finePerCapita = this.parkingProcessor.TotalFinePerCapita();
		
		double varFinePerCapita = StatisticalComputer.computeCovariance(finePerCapita, finePerCapita);
		double varMktPerCapita = StatisticalComputer.computeCovariance(mktValuePerCapita, mktValuePerCapita);
		double cov = StatisticalComputer.computeCovariance(mktValuePerCapita, finePerCapita);
		double CorrelationCoefficient = cov / Math.sqrt(varFinePerCapita * varMktPerCapita);
		
		return CorrelationCoefficient;
		
	}
	
}