package edu.upenn.cit594.logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Logger {
	public static String LOGFILENAME;
	private PrintWriter out;
	private Logger(String filename) {
		try {
			FileWriter f = new FileWriter(filename,true);
			BufferedWriter bw = new BufferedWriter(f);
			out = new PrintWriter(bw);
		}
		catch (Exception e) {}
	}

	private static Logger instance = null;;
	public static Logger getInstance() {
		if(Logger.LOGFILENAME==null) {
			return null;
		}
		if(instance==null) {
			instance = new Logger(Logger.LOGFILENAME);
		}
		return instance;
	}
	
	//need to change
	
	public void log(String message) {
		out.println(message);
		out.flush();
	}
}
