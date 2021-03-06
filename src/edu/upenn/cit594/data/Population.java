package edu.upenn.cit594.data;

/**
 * Population data object
 * @author zhongliu
 *
 */
public class Population {
	private String zipCode;
	private Integer population;
	
	public Population(String zipCode, int population) {
		this.zipCode = zipCode;
		this.population = population;
	}
	
	
	public String getZipCode() {
		return zipCode;
	}
	
	public Integer getPopulation() {
		return population;
	}

}
