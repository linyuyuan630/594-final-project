package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.datamanagement.PopulationReader;

public class PopulationProcessor {
	
	private PopulationReader populationReader = null;
	
	/**
	 * 1. Total Population for All ZIP Codes
	 * @return
	 */
	public int totalPopulation() {
		return populationReader.totalPopulation();
	}
	
	public int totalPopulation(String zipCode) {
		return populationReader.totalPopulation(zipCode);
	}

}
