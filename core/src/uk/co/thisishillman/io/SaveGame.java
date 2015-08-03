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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Represents a single game state saved by the user.
 * 
 * @author Michael Hillman
 */
public class SaveGame implements Comparable<SaveGame> {

	// Location on disk
	private final Path location;
	
	/**
	 * Initialise a new save with the input file location
	 * 
	 * @param location
	 */
	public SaveGame(Path location) {
		this.location = location;
	}
	
	public void read() {
		// TODO - Read in save data
	}
	
	public void write() {
		// TODO - Write out save data
	}
	
	/**
	 * Returns the save game name
	 * @return
	 */
	public String getSaveName() {
		return location.getFileName().toString();
	}
	
	/**
	 * Returns the date/time of this save's last modification
	 * 
	 * @return
	 */
	public LocalDateTime getSaveTime() {
		try {
			BasicFileAttributes attrs = Files.readAttributes(location, BasicFileAttributes.class);
			return LocalDateTime.ofInstant(attrs.lastModifiedTime().toInstant(), ZoneId.systemDefault());
			
		} catch (IOException ioExcep) {
			ioExcep.printStackTrace(System.err);
		}
		return LocalDateTime.now();
	}

	/**
	 * Compares two saves via save time then name.
	 */
	@Override
	public int compareTo(SaveGame obj) {
		if(obj == null) return 0;
		if( !(obj instanceof SaveGame) ) return 0;
		
		SaveGame that = (SaveGame) obj;
		
		int dates = this.getSaveTime().compareTo(that.getSaveTime());
		
		if(dates == 0) {
			return this.getSaveName().compareTo(that.getSaveName());
		}
		return dates;
	}
	
}
// End of class.