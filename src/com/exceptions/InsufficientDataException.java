package com.exceptions;
import java.io.IOException;
import java.util.logging.*;


public class InsufficientDataException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(InsufficientDataException.class.getName());
	
	static {
		try {
			log.setLevel(Level.FINE);
			log.addHandler(new FileHandler("exceptionlog.xml"));
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
