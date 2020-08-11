package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.PropertyReader;

public class PropertyProcessor {
	private PropertyReader propertyReader;
	
	public double averageMarketValue(String zipCode) {
		return propertyReader.averageMarketValue(zipCode);
	}
	
	public double averageTotalLivableArea(String zipCode) {
		return propertyReader.averageTotalLivableArea(zipCode);
	}
	
	public double totalResidentialMktValuePerCapita(String zipCode) {
		return propertyReader.totalResidentialMktValuePerCapita(zipCode);
	}

}
