package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.Population;

public class PopulationReader {
		
	private List<Population> populationList;
	//protected String populationFilename;
	
	public PopulationReader(String populationFilename) {
		//this.populationFilename = populationFilename;
		this.loadPopulations(populationFilename);
	}
	
	private void loadPopulations(String populationFilename) {
		//to load data from input file to populationList
		populationList = new ArrayList<Population>();
		try {
			FileReader fr = new FileReader(populationFilename); 
	        BufferedReader br = new BufferedReader(fr); 
	        String line = br.readLine();
			while (line != null) {
				String[] lineInfo = line.split("\\s");
				Integer population = Integer.valueOf(lineInfo[1]);
				String zipCode = lineInfo[0];
				Population populationEntry = new Population(zipCode, population);
				populationList.add(populationEntry);
				line = br.readLine();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Population> readAllPopulation() {
		return populationList;	
	}
	/**
	public int totalPopulation() {
		int total = 0;
		for (int i = 0; i < populationList.size(); i++)  {
			int population = populationList.get(i).getPopulation();
			total = total + population;
		}
		return total;
	}
	
	public int totalPopulation(String zipCode) {
		int totalInZipCode = 0;
		for(int i = 0; i < populationList.size(); i++) {
			if (zipCode.equals(populationList.get(i).getZipCode())) {
				 totalInZipCode = totalInZipCode + populationList.get(i).getPopulation();
			}
		}
		return totalInZipCode;
	}
	**/


}

