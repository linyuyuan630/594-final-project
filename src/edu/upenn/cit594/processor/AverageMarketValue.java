package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.Property;

public class AverageMarketValue implements AverageInZipCode{
	@Override
	public double averageInZipCode(String zipCode, List<Property> propertyList) {
		int propertyCounter = 0;
		int totalValue = 0;
		for (int i = 0; i < propertyList.size(); i++ ) {
			String propertyZipCode = propertyList.get(i).getZipCode();
			Integer propertyMktValue = propertyList.get(i).getMarketValue();
			if (propertyMktValue != null && propertyZipCode != null) {
				if (zipCode.equals(propertyZipCode)) {
					totalValue += propertyMktValue;
					propertyCounter++;
				}	
			}
			
		}
		if (propertyCounter == 0) {
			return 0;
		}
		else {
			return totalValue / propertyCounter * 1.0;
		}
	}
}
