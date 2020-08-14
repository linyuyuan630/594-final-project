package edu.upenn.cit594;

import java.io.File;

import edu.upenn.cit594.datamanagement.ParkingReader;
import edu.upenn.cit594.processor.ParkingProcessor;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.PropertyProcessor;
import edu.upenn.cit594.ui.UserInterface;

public class Main {
	public static void main(String[] args) {
		/** check if the input runtime argument is valid 
		if (args.length != 4) {
			System.out.println("run time arguments not correct!");
			System.exit(1);
		}
		
		if (args[0].toLowerCase().equals("json") == false && args[0].toLowerCase().equals("text") == false) {
			System.out.println("file format not correct!");
			System.exit(1);
		}
		
		File tempTweetFile = new File(args[1]);
		boolean tweetFileExists = tempTweetFile.exists();
		File tempStateFile = new File(args[2]);
		boolean stateFileExists = tempStateFile.exists();
		
		if (tweetFileExists == false || stateFileExists == false) {
			System.out.println("file not exist");
			System.exit(1);
		}
		
		String tweetsFileFormat = args[0];
		String tweetsFilename = args[1];
		String statesFilename = args[2];
		String logFilename = args[3];
		String fileFormat = tweetsFileFormat.toLowerCase();
		
		The format of the parking violations input file, either “csv” or “json”
        The name of the parking violations input file
        The name of the property values input file
        The name of the population input file
        The name of the log file (described below)
		**/
		
		
		
		String parkingFileFormat = "json";
		String parkingFilename = "parkingTest.csv";
		String propertyFilename = "propertyTest.csv";
		String populationFilename = "populationTest.txt";
		String logFilename = "log.txt";
		String fileFormat = parkingFileFormat.toLowerCase();
		

		ParkingProcessor parkingProcessor = new ParkingProcessor(parkingFilename, populationFilename);
		PopulationProcessor populationProcessor = new PopulationProcessor(populationFilename);
		PropertyProcessor propertyProcessor = new PropertyProcessor(propertyFilename, 
				populationFilename, parkingFilename);
		UserInterface ui = new UserInterface(parkingProcessor, propertyProcessor, populationProcessor);
		ui.start();
	}
}
