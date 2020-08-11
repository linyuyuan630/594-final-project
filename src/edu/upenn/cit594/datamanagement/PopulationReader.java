package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.Population;

public class PopulationReader {
		
	private List<Population> populationList;
	
	public PopulationReader(String populationFilename) {
		this.loadPopulations(populationFilename);
	}
	
	
	private void loadPopulations(String populationFilename) {
		//to load data from input file to populationList
	}
	
	
	public List<Population> readAllPopulation() {
		return null;
	}
	
	public int totalPopulation() {
		return -1;
	}
	
	public int totalPopulation(String zipCode) {
		return -1;
	}


}
