package edu.upenn.cit594.processor;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.datamanagement.ParkingViolationReader;


public class ParkingViolationProcessor {
	
	private ParkingViolationReader parkingViolationReader;
	
	public ParkingViolationProcessor(String parkingFilename) {
		
	}
	
	/**
	 * 2. Total Fines Per Capita
	 * @return
	 */
	public int totalFinePerCapital() {
		return -1;
	}
	
	/**
	 * 2. Total Fines Per Capita
	 * @param zipCode
	 * @return
	 */
	public int totalFinePerCapital(String zipCode) {
		return -1;
	}

}
