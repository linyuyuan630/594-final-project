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
import edu.upenn.cit594.logging.Logger;

public class PropertyReader {
		
	private List<Property> propertyList;
	
	private static PropertyReader reader;
	
	/**
	 * Singleton design pattern
	 * @param propertyFilename
	 * @return PropertyReader
	 * @throws IOException 
	 */
	public static PropertyReader getInstance(String propertyFilename) throws IOException {
		if(reader==null) {
			reader = new PropertyReader(propertyFilename);
		}
		return reader;
	}
	
	private PropertyReader(String propertyFilename) throws IOException {
		if(propertyList==null) {//optimization - assume the data set cannot be changed during run time
			this.loadProperties(propertyFilename);
		}
	}
	
	/**
	 * load properties from the file
	 * @throws IOException 
	 */
	private void loadProperties(String propertyFilename) throws IOException {
		propertyList = new ArrayList<Property>();
		try {
			FileReader fr = new FileReader(propertyFilename); 
	        BufferedReader br = new BufferedReader(fr); 
	        //logging
			Logger.getInstance().log(System.currentTimeMillis()+" Openning file:"+propertyFilename);
	        String line = br.readLine();
	        String columnNames[] = line.split(",");
	        int marketValueIndex = 0;
	        int totalLivableAreaIndex = 0;
	        int zipCodeIndex = 0;
	        Double marketValue;
	        Double totalLivableArea;
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
//	        int i=1;
			while (line != null) {
				//to check this logic
				String[] lineInfo = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				try {
					marketValue = Double.valueOf(lineInfo[marketValueIndex].trim());
				}catch (Exception e) {
					marketValue = null;
				}
//				String totalLivableAreaStr = lineInfo[totalLivableAreaIndex];
				try {
					totalLivableArea = Double.valueOf(lineInfo[totalLivableAreaIndex].trim());
				}catch (Exception e) {
					totalLivableArea = null;
				}
				
				zipCode = lineInfo[zipCodeIndex];

//				System.err.println(i+":"+"zipCode="+zipCode+", marketValue"+marketValue+" ,totalLivableAreaStr"+totalLivableAreaStr+" ,totalLivableArea"+totalLivableArea);
//				i++;
				Property propertyEntry = new Property(marketValue, totalLivableArea, zipCode);
				propertyList.add(propertyEntry);
				line = br.readLine();
			} 
	        
		}
		catch (IOException e) {
			throw e;
		} 
	}
	
	/**
	 * 
	 * @return all properties
	 */
	public List<Property> readAllProperties() {
		return propertyList;
	}

}




