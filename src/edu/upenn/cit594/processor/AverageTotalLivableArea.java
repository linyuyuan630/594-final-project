package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.Property;

/**
 * 
 * @author zhongliu
 *
 */
public class AverageTotalLivableArea implements AverageInZipCode{
	@Override
	public double averageInZipCode(String zipCode, List<Property> propertyList) {
		int propertyCounter = 0;
		double totalAreaValue = 0;
		for (int i = 0; i < propertyList.size(); i++ ) {
			String propertyZipCode = propertyList.get(i).getZipCode();
			Double propertyLivableArea = propertyList.get(i).getTotalLiverableArea();
			if (propertyLivableArea != null && propertyZipCode != null) {
				if (zipCode.equals(propertyZipCode)) {
					totalAreaValue += propertyLivableArea;
					propertyCounter++;
				}	
			}
		}
//		System.out.println("totalAreaValue="+totalAreaValue);
//		System.out.println("propertyCounter="+propertyCounter);
		if (propertyCounter == 0) {
			return 0;
		}
		else {
			return totalAreaValue / propertyCounter * 1.0;
		}
	}
}
