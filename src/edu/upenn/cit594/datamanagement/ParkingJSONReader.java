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

import edu.upenn.cit594.data.ParkingData;

/**
 * the reader reads all the entries in the property file in JSON format and returns
 * an ArrayList contains all of the entries
 */

public class ParkingJSONReader implements ParkingReader{
	protected String parkingFilename;
	
	public ParkingJSONReader(String parkingFilename) {
		this.parkingFilename = parkingFilename;	
	}

	@Override
	public List<ParkingData> readAllParkings() {
		// create a parser
		List<ParkingData> allParkings = new ArrayList<ParkingData>();
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
			ParkingData parkingEntry = new ParkingData(parkingViolation.get("fine").toString(),
					parkingViolation.get("zip_code").toString());
			allParkings.add(parkingEntry);
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
		return allParkings;
	}
}

