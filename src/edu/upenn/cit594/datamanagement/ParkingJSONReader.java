package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.ParkingViolation;

public class ParkingJSONReader extends ParkingReader{
	protected String parkingFilename;
	
	List<ParkingViolation> allParkingViolations;
	
	public ParkingJSONReader(String parkingFilename) {
		this.loadParkingViolations(parkingFilename);
	}

	private void loadParkingViolations(String parkingFilename) {
		//load data from input file to parkingViolations list. 
		// create a parser
		allParkingViolations = new ArrayList<ParkingViolation>();
		JSONParser parser = new JSONParser();
		try {
			// open the file and get the array of JSON objects
			JSONArray fluTweets = (JSONArray)parser.parse(new FileReader(parkingFilename));
			// use an iterator to iterate over each element of the array
			Iterator iter = fluTweets.iterator();
			// iterate while there are more objects in array
			while (iter.hasNext()) {
			// get the next JSON object
			JSONObject parkingViolation = (JSONObject) iter.next();
			// use the "get" method to print the value associated with that key
			ParkingViolation parkingEntry = new ParkingViolation(Integer.valueOf(parkingViolation.get("fine").toString()),
					parkingViolation.get("zip_code").toString());
			allParkingViolations.add(parkingEntry);
			}
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
        } 
		catch (IOException e) {
			e.printStackTrace();
			} 
		catch (ParseException e) {
			e.printStackTrace();
			}
	}

	
	@Override
	public List<ParkingViolation> readAllParkingViolations() {
		// TODO Auto-generated method stub
		return allParkingViolations;
	}
	
	

}
