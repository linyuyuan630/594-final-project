package edu.upenn.cit594.logging;

import java.io.File;
import java.io.PrintWriter;

public class Logger {
	public static String logfileName;
	//private static String filename;
	private PrintWriter out;
	private Logger(String filename) {
		try {
			out = new PrintWriter(new File(filename));
		}
		catch (Exception e) {}
	}

	private static Logger instance = new Logger("log.txt");
	public static Logger getInstance() {
		return instance;
	}
	
	//need to change
	
	public void log(String message) {
		out.println(message);
		out.flush();
	}
}
