package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.Property;

/**
 *  
 * @author zhongliu
 *
 */
public interface AverageInZipCode {
	/**
	 * to calculate average in a zipcode from given property list
	 * @param zipCode
	 * @param propertyList
	 * @return
	 */
	public double averageInZipCode(String zipCode, List<Property> propertyList);

}
