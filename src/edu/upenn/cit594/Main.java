package edu.upenn.cit594;

import java.io.File;

import edu.upenn.cit594.datamanagement.ParkingViolationReader;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.ParkingViolationProcessor;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.PropertyProcessor;
import edu.upenn.cit594.ui.UserInterface;

public class Main {
	public static void main(String[] args) {
		//check if the input runtime argument is valid 
		if (args.length < 5) { //The number of runtime arguments is incorrect
			System.out.println("Run-time arguments not correct!");
			System.out.println("Arguments should be: \n"+
					"The format of the parking violations input file, either csv or json\n"+
					"The name of the parking violations input file\n"+
					"The name of the property values input file\n"+
					"The name of the population input file\n"+
					"The name of the log file\n"
					);
			System.exit(1);
		}
		
		String parkingFileFormat = args[0];
		String parkingFilename = args[1];
		String propertyFilename = args[2];
		String populationFilename = args[3];
		String logFilename = args[4];
		
		//The first argument is neither “csv” or “json” (case-sensitive)
		if (!parkingFileFormat.equals("json") && !parkingFileFormat.equals("csv")) {
			System.out.println("format of the parking violations should be either csv or json!");
			System.exit(1);
		}
		
		try {
	
				
	//		String parkingFileFormat = "json";
	//		String parkingFilename = "parkingTest.csv";
	//		String propertyFilename = "propertyTest.csv";
	//		String populationFilename = "populationTest.txt";
	//		String logFilename = "log.txt";
	//		String fileFormat = parkingFileFormat.toLowerCase();
			
			//config global log file
			Logger.LOGFILENAME = logFilename;
			Logger log = Logger.getInstance();
			
			//When the program starts, it should write the current time 
			//(using the standard Java time-as-milliseconds format from System.currentTimeMillis()) followed by a single whitespace, 
			//followed by each of the runtime arguments, each of which is separated by a single whitespace.
			log.log(System.currentTimeMillis()+
					" "+parkingFileFormat+
					" "+parkingFilename+
					" "+propertyFilename+
					" "+populationFilename+
					" "+logFilename
					);
	
			System.out.println("Pre-loading files to run program faster when it's done...This might take a few minutes vary mainly depending on memory size of your machine...");
			
			PopulationProcessor populationProcessor = new PopulationProcessor(populationFilename);
			
			ParkingViolationProcessor parkingViolationProcessor = new ParkingViolationProcessor(parkingFileFormat,parkingFilename, populationProcessor);
			
			PropertyProcessor propertyProcessor = new PropertyProcessor(propertyFilename, 
					parkingViolationProcessor, populationProcessor);
			
			UserInterface ui = new UserInterface(parkingViolationProcessor, propertyProcessor, populationProcessor);
			
			ui.start();
			
		}catch (Exception e) {
			System.out.println("Run time arguments not correct! Please make sure all input files exist");
			System.exit(1);
		}
	}
}
