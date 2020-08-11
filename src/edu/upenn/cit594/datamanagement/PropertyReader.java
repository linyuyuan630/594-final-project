package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import edu.upenn.cit594.data.ParkingData;
import edu.upenn.cit594.data.PopulationData;
import edu.upenn.cit594.data.PropertyData;

public class PropertyReader {
	/**
	 * the reader reads all the entries in the property file in csv or text format and returns
	 * an ArrayList contains all of the entries
	 */
	
	protected String propertyFilename;
	
	/*
	 * pattern for numeric content
	 * private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	 */
	
	
	
	public PropertyReader(String propertyFilename) {
		this.propertyFilename = propertyFilename;	
	}
	
	public List<PropertyData> readAllProperties() {
		List<PropertyData> allProperties = new ArrayList<PropertyData>();
		try {
			FileReader fr = new FileReader(propertyFilename); 
	        BufferedReader br = new BufferedReader(fr); 
	        String line = br.readLine();
	        String columnNames[] = line.split(",");
	        int marketValueIndex = 0;
	        int totalLivableAreaIndex = 0;
	        int zipCodeIndex = 0;
	        String marketValue;
	        String totalLivableArea;
	        String zipCode;
	        for (int i = 0; i < columnNames.length - 1; i++) {
	        	if (columnNames[i].equals("market_value")) {
	        		marketValueIndex = i;
	        	}
	        	else if (columnNames[i].equals("total_livable_area")) {
	        		totalLivableAreaIndex = i;
	        	}
	        	else if (columnNames[i].equals("zipcode")) {
	        		zipCodeIndex = i;
	        	}
	        }
	        line = br.readLine();
			while (line != null) {
				String[] lineInfo = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				marketValue = lineInfo[marketValueIndex];
				totalLivableArea = lineInfo[totalLivableAreaIndex];
				zipCode = lineInfo[zipCodeIndex];
				PropertyData propertyEntry = new PropertyData(marketValue, totalLivableArea, zipCode);
				allProperties.add(propertyEntry);
				line = br.readLine();
			} 
	        
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return allProperties;	
	}
	
	/*
	 *check if the content is numeric
	 *	private static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false; 
	    }
	    return pattern.matcher(strNum).matches();
	}
	 */
	



}
