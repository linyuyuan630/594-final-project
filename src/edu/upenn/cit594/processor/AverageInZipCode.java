package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.Property;

public interface AverageInZipCode {
	public double averageInZipCode(String zipCode, List<Property> propertyList);

}
