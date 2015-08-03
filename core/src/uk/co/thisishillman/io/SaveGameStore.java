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
package uk.co.thisishillman.io;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import uk.co.thisishillman.Meta;

/**
 * Store all save game instances
 * 
 * @author Michael Hillman
 */
public class SaveGameStore {

	// Location of save games
	private static Path SAVE_FOLDER;
	
	// All save games
	private static List<SaveGame> SAVEGAMES;
	
	// Initialise list and read in saves.
	static {
		SAVEGAMES = new ArrayList<>();
		SAVE_FOLDER = Paths.get(Meta.INSTALL_DIR.toString(), "saves");
		
		readSaves();
	}
	
	/**
	 * Read in meta-data on each save game
	 */
	private static void readSaves() {
		try {
			if(!Files.exists(SAVE_FOLDER, LinkOption.NOFOLLOW_LINKS)) {
				Files.createDirectory(SAVE_FOLDER);
			}
			
			DirectoryStream<Path> dirStream = Files.newDirectoryStream(SAVE_FOLDER);
			for(Path saveFile : dirStream) {
				SAVEGAMES.add(new SaveGame(saveFile));
			}
			
			Collections.sort(SAVEGAMES);
				
		} catch (IOException ioExcep) {
			ioExcep.printStackTrace(System.err);
		}
	}
}
// End of class.