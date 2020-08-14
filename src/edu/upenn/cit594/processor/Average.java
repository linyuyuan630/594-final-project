package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.Property;

public class Average {
	private AverageInZipCode averageStrategy;
	public Average(AverageInZipCode averageStrategy) {
		this.averageStrategy = averageStrategy;
	}
	
	public double computeStrategy(String zipCode, List<Property> propertyList) {
		return averageStrategy.averageInZipCode(zipCode, propertyList);
	}
	
}