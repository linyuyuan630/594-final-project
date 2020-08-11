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

	private static Logger instance = null;
	
	/**
	 * 1. Use the Singleton design pattern to implement the file logger in Step #7.
	 * @return Logger
	 */
	public static Logger getInstance() {
		if(instance==null) {
			instance = new Logger("log.txt");
		}
		return instance;
	}
	
	//need to change
	
	public void log(String state, String tweet) {
		out.println(state + "\t" + tweet);
		out.flush();
	}
}
