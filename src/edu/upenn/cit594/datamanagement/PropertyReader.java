package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Property;

public class PropertyReader {
		
	private List<Property> properties;
	
	public PropertyReader(String propertyFilename) {
		this.loadProperties(propertyFilename);
	}
	
	/**
	 * load properties from the file
	 */
	private void loadProperties(String propertyFilename) {
		//todo
	}
	
	public List<Property> readAllProperties() {
		return null;
	}
	
	/**
	 * 3. Average Market Value
	 * @param zipCode
	 * @return
	 */
	public double averageMarketValue(String zipCode) {
		return -1;
	}
	
	/**
	 * 4. Average Total Livable Area
	 * TODO: Use the Strategy design pattern to implement the average residential market
	 *  value (Step #3) and average residential total living area (Step #4) features. 
	 *  Think about what similarities these two features have and how you can specify 
	 *  different “strategies” for each.
	 * @param zipCode
	 * @return
	 */
	public double averageTotalLivableArea(String zipCode) {
		return -1;
	}

	/**
	 * 5. Total Residential Market Value Per Capita
	 * @return
	 */
	public double totalResidentialMktValuePerCapita(String zipCode) {
		return -1;
	}

}
