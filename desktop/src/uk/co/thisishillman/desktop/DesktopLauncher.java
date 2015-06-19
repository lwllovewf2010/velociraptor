/*
 * Copyright (c) 2015 M Hillman - All Rights Reserved
 * 
 * This application and all inherent data, source files, information and graphics are 
 * the copyright and sole property of M Hillman (thisishillman.co.uk).
 * 
 * Any unauthorised redistribution or reproduction of part, or all, of the contents of this 
 * application in any form is prohibited under UK Copyright Law. You may not, except with the 
 * express written permission of CMCL Innovations, distribute or commercially exploit this
 * application or it's content. All other rights reserved.
 * 
 * For more information please contact business<@>thisishillman.co.uk
 */
package uk.co.thisishillman.desktop;

import java.util.HashSet;
import java.util.Set;

import uk.co.thisishillman.MainGame;
import uk.co.thisishillman.Meta;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Launcher for Desktop platforms
 * 
 * @author M Hillman
 */
public class DesktopLauncher {
	
	// Hold command line arguments
	private static final Set<String> ARG_SET = new HashSet<>();
	
	/**
	 * Entry point for desktop platforms
	 * 
	 * @param arg CLI args
	 */
	public static void main (String[] args) {
		
		// Add arguments to hash set
		for(String arg : args) ARG_SET.add(arg);
		
		readArguments();
		
		// Set config and launch
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title      = Meta.PROJECT_TITLE + " - " + Meta.VERSION;
		config.resizable  = false;
		config.width      = 800;
		config.height     = 600;
		
		new LwjglApplication(new MainGame(), config);
	}
	
	/**
	 * Processes command line arguments
	 */
	private static void readArguments() {
		for(String arg : ARG_SET) {
			
			switch(arg) {
				
				case "-d" :
				case "-D" :
					Meta.DEBUG = true;
					System.out.println("Running in hard-coded debug mode!");
				break;
			}
		}
	}
	
}
//End of class