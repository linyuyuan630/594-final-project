package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Property;

public class PropertyReader {
		
	private List<Property> propertyList;
	
	public PropertyReader(String propertyFilename) {
		this.loadProperties(propertyFilename);
	}
	
	/**
	 * load properties from the file
	 */
	private void loadProperties(String propertyFilename) {
		propertyList = new ArrayList<Property>();
		try {
			FileReader fr = new FileReader(propertyFilename); 
	        BufferedReader br = new BufferedReader(fr); 
	        String line = br.readLine();
	        String columnNames[] = line.split(",");
	        int marketValueIndex = 0;
	        int totalLivableAreaIndex = 0;
	        int zipCodeIndex = 0;
	        Double marketValue;
	        Integer totalLivableArea;
	        String zipCode;
	        for (int i = 0; i < columnNames.length; i++) {
	        	if (columnNames[i].equalsIgnoreCase("market_value")) {
	        		marketValueIndex = i;
	        	}
	        	else if (columnNames[i].equalsIgnoreCase("total_livable_area")) {
	        		totalLivableAreaIndex = i;
	        	}
	        	else if (columnNames[i].equalsIgnoreCase("zip_code")) {
	        		zipCodeIndex = i;
	        	}
	        }
	        line = br.readLine();
	        
			while (line != null) {
				String[] lineInfo = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				try {
					marketValue = Double.valueOf(lineInfo[marketValueIndex]);
				}catch (Exception e) {
					marketValue = null;
				}
				
				try {
					totalLivableArea = Integer.valueOf(lineInfo[totalLivableAreaIndex]);
				}catch (Exception e) {
					totalLivableArea = null;
				}
				
				zipCode = lineInfo[zipCodeIndex];
				if (zipCode.length()<5) {
					zipCode = null;
				}
				else {
					zipCode = zipCode.substring(0, 5);
				}
				
				Property propertyEntry = new Property(marketValue, totalLivableArea, zipCode);
				propertyList.add(propertyEntry);
				line = br.readLine();
			} 
	        
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public List<Property> readAllProperties() {
		return propertyList;
	}

}




