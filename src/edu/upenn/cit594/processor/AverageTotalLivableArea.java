package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.Property;

public class AverageTotalLivableArea implements AverageInZipCode{
	@Override
	public double averageInZipCode(String zipCode, List<Property> propertyList) {
		int propertyCounter = 0;
		int totalAreaValue = 0;
		for (int i = 0; i < propertyList.size(); i++ ) {
			String propertyZipCode = propertyList.get(i).getZipCode();
			Integer propertyLivableArea = propertyList.get(i).getTotalLiverableArea();
			if (propertyLivableArea != null && propertyZipCode != null) {
				if (zipCode.equals(propertyZipCode)) {
					totalAreaValue += propertyLivableArea;
					propertyCounter++;
				}	
			}
			
		}
		if (propertyCounter == 0) {
			return 0;
		}
		else {
			return totalAreaValue / propertyCounter * 1.0;
		}
	}
}
