/*
 * Copyright (c) 2015 M Hillman - All Rights Reserved
 * 
 * This application and all inherent data, source files, information and graphics are 
 * the copyright and sole property of M Hillman (thisishillman.co.uk).
 * 
 * Any unauthorised redistribution or reproduction of part, or all, of the contents of this 
 * application in any form is prohibited under UK Copyright Law. You may not, except with the 
 * express written permission of M Hillman, distribute or commercially exploit this
 * application or it's content. All other rights reserved.
 * 
 * For more information please contact business<@>thisishillman.co.uk
 */
package uk.co.thisishillman;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Class containing user configurable settings & properties
 * 
 * @author Michael Hillman
 */
public class Settings {

	// Path to ini file
	private static Path INI_FILE;
	
	// Properties object
	private static Properties PROPERTIES;
	
	/**
	 * Gets the property value with the input key (or null)
	 * 
	 * @param key unique key
	 * @return resulting value (or null)
	 */
	public static String getSetting(String key) {
		if(PROPERTIES == null) {
			initialiseProperties();
		}
		return PROPERTIES.getProperty(key, null);
	}
	
	/**
	 * Add the input key/value pair into the settings
	 * 
	 * @param key unique key
	 * @param value associated value
	 */
	public static void putSetting(String key, String value) {
		if(PROPERTIES == null) {
			initialiseProperties();
		}
		PROPERTIES.put(key, value);
	}
	
	/**
	 * Write the current properties object to disk.
	 */
	public static void saveSettings() {
		FileOutputStream out = null;
        
        try {
            out = new FileOutputStream(INI_FILE.toString());
            PROPERTIES.store(out, "--- User Settings ---");
            
        } catch(Exception excep) {
        	System.err.println("ERROR: Cannot write properties to settings.ini file.");
            
        } finally {
        	if(out != null) {
				try {
					out.close();
				} catch (IOException e) { }
			}
        }
	}
	
	/**
	 * Initialise class variables
	 */
	private static void initialiseProperties() {
		INI_FILE = Paths.get(Meta.INSTALL_DIR.toString(), "settings.ini");
		PROPERTIES = new Properties();
		
		if(Files.exists(INI_FILE, LinkOption.NOFOLLOW_LINKS)) {
			FileReader fileReader = null;
			
			try {
				fileReader = new FileReader(INI_FILE.toString());
				PROPERTIES.load(fileReader);
				
			} catch(IOException ioExcep) {
				System.err.println("ERROR: Cannot read in settings.ini file!");
				
			} finally {
				if(fileReader != null) {
					try {
						fileReader.close();
					} catch (IOException e) { }
				}
			}
			
		}
	}
	
}
//End of class