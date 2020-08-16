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
import edu.upenn.cit594.logging.Logger;

/**
 * 
 * @author zhongliu
 *
 */
public class ParkingViolationJSONReader extends ParkingViolationReader{
	protected String parkingFilename;
	
	List<ParkingViolation> allParkingViolations;
	
	private static ParkingViolationJSONReader reader;
	
	public static ParkingViolationJSONReader getInstance(String parkingFilename) throws IOException {
		if(reader==null) {
			reader = new ParkingViolationJSONReader(parkingFilename);
		}
		return reader;
	}
	
	private ParkingViolationJSONReader(String parkingFilename) throws IOException {
		if(allParkingViolations==null) {//optimization - assume the data set cannot be changed during run time
			this.loadParkingViolations(parkingFilename);
		}
	}

	private void loadParkingViolations(String parkingFilename) throws IOException {
		//load data from input file to parkingViolations list. 
		// create a parser
		allParkingViolations = new ArrayList<ParkingViolation>();
		JSONParser parser = new JSONParser();
		try {
			// open the file and get the array of JSON objects
			FileReader fr = new FileReader(parkingFilename);
			JSONArray fluTweets = (JSONArray)parser.parse(fr);
			//logging
			Logger.getInstance().log(System.currentTimeMillis()+" Openning file:"+parkingFilename);
			// use an iterator to iterate over each element of the array
			Iterator iter = fluTweets.iterator();
			// iterate while there are more objects in array
			while (iter.hasNext()) {
			// get the next JSON object
			JSONObject parkingViolation = (JSONObject) iter.next();
			// use the "get" method to print the value associated with that key
			ParkingViolation parkingEntry = new ParkingViolation(Integer.valueOf(parkingViolation.get("fine").toString()),
					parkingViolation.get("zip_code").toString(),parkingViolation.get("state").toString());
			allParkingViolations.add(parkingEntry);
			}
		}
		catch (IOException e) {
			throw e;
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
