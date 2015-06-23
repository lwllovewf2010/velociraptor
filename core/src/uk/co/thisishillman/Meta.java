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

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class containing final static variables used as program-wide meta data.
 * 
 * @author Michael Hillman
 */
public class Meta {
	
	// Human readable project title
	public static final String PROJECT_TITLE = "The Velociraptor Project";
	
	// Short project ID
	public static final String PROJECT_ID = "veloproj";
	
	// Author name
	public static final String AUTHOR = "M Hillman";
	
	// Current version
	public static final String VERSION = "0.01";
	
	// Debug flag (for developers only, pass -D argument)
	public static boolean DEBUG = false;
	
	// Location of folder containing JAR file
	public static Path INSTALL_DIR;
	
	// Get the installation directory
	static {
		determineInstallDirectory();
		System.out.println(INSTALL_DIR);
	}
	
	/** 
     * Determines the installation directory (where this jar file is).
     */
    private static void determineInstallDirectory() {
        try {
            URI location = Meta.class.getProtectionDomain().getCodeSource().getLocation().toURI();
            INSTALL_DIR = Paths.get( new File(location).getParentFile().getAbsolutePath() );
            
        } catch(URISyntaxException excep) {
            System.err.println("ERROR: Cannot determine the installation directory.");
        }
    }

}
//End of class