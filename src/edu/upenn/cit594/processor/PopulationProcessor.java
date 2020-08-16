package edu.upenn.cit594.processor;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.datamanagement.PopulationReader;

/**
 * 
 * @author zhongliu
 *
 */
public class PopulationProcessor {
	
	protected PopulationReader populationReader;
	private List<Population> populationList;
	private Set<String> zipCodeSet;
	//private PopulationReader populationReader = null;
	
	public PopulationProcessor(String populationFilename) throws IOException {
		populationReader = PopulationReader.getInstance(populationFilename);
		populationList = populationReader.readAllPopulation();
		zipCodeSet = createZipCodeSet(); 
	}
	
	private Set<String> createZipCodeSet() {
		Set<String> codeSet = new HashSet<String>();
		for (int i = 0; i < populationList.size(); i++) {
			codeSet.add(populationList.get(i).getZipCode());
		}
		return codeSet;
	}
	
	/**
	 * 
	 * @return totalPopulation
	 */
	public int totalPopulation() {
		int total = 0;
		for (int i = 0; i < populationList.size(); i++)  {
			int population = populationList.get(i).getPopulation();
			total = total + population;
		}
		return total;
	}
	
	/**
	 * total population in given zipcode
	 * @param zipCode
	 * @return
	 */
	public int totalPopulationInZipCode(String zipCode) {
		int totalInZipCode = 0;
		for(int i = 0; i < populationList.size(); i++) {
			if (zipCode.equalsIgnoreCase(populationList.get(i).getZipCode())) {
				 totalInZipCode = totalInZipCode + populationList.get(i).getPopulation();
			}
		}
		return totalInZipCode;
	}

}