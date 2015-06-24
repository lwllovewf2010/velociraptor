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
package uk.co.thisishillman.menus.loading;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;

/**
 * Handles reading and storing pieces of text to display in loading screens/menus (things
 * I'm calling 'snippets').
 * 
 * @author Michael Hillman
 */
public class SnippetPool {

	// List of snippets
	private static List<String> snippets;
	
	// Initialise snippets
	static {
		snippets = new ArrayList<>();
		readSnippetsFile();
	}
	
	/**
	 * Read the snippets.json file
	 */
	private static void readSnippetsFile() {
		JsonParserFactory factory = JsonParserFactory.getInstance();
		JSONParser parser = factory.newJsonParser();
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) classLoader = Class.class.getClassLoader();
		
		InputStream inStream = null;
		
		try {
			inStream = classLoader.getResourceAsStream("snippets.json");
			Map data = parser.parseJson(inStream, "UTF-8");
			
			List quotes = (List) data.get("quote");
			
			for(int i = 0; i < quotes.size(); i++) {
				Map quote = (Map) quotes.get(i);
				snippets.add(quote.get("text").toString());
			}
			
		} catch(Exception ioExcep) {
			System.err.println("ERROR: Could not read in snippets.json file.");
			
		} finally {
			try {
				inStream.close();
			} catch(IOException closeExcep) { }
			
		}
	}
	
	/**
	 * Returns a randomly selected snippet
	 * 
	 * @return
	 */
	public static String getRandomSnippet() {
		int index = new Random().nextInt(snippets.size());
		return snippets.get(index);
	}

}
// End of class