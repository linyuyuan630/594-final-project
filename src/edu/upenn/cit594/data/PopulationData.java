package edu.upenn.cit594.data;

/**
 * 
 * @author yuyuan.lin
 * Each population entry is an instance of PopulationData
 *
 */
public class PopulationData {
	private String zipCode;
	private String population;
	
	public PopulationData(String zipCode, String population) {
		this.zipCode = zipCode;
		this.population = population;
	}
	
	
	public String getZipCode() {
		return zipCode;
	}
	
	public String getPopulation() {
		return population;
	}

}
