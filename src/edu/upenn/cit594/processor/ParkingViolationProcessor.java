package edu.upenn.cit594.processor;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.data.ParkingData;
import edu.upenn.cit594.datamanagement.ParkingReader;


public abstract class ParkingProcessor {
	
	protected ParkingReader parkingReader;
	protected List<ParkingData> allParkingData;
	
	public ParkingProcessor(String parkingFilename) {
	}
	
	protected abstract ParkingReader createReader(String parkingFilename);

}
