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
package uk.co.thisishillman.text;

import java.util.HashMap;
import java.util.Map;

import uk.co.thisishillman.Meta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * Acts as a static store for all bitmap fonts.
 * 
 * @author Michael Hillman
 */
public class FontStore {

	// Bitmap fonts stored with unique name as the key
	private static final Map<String, BitmapFont> FONTS = new HashMap<>();

	// Font to use incase other font loading fails
	private static BitmapFont DEFAULT;
	
	// Statically initialise default font
	static {
		FileHandle handle = Gdx.files.internal("fonts/minecraftia.ttf");
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(handle);
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 14;
		DEFAULT = generator.generateFont(parameter);
	}
	
	/**
	 * Returns the font with the input name (loading it in if needs be). 
	 * Returns null if the font cannot be found/loaded.
	 * 
	 * @param name unique name of font
	 * @return resulting bitmap font (or null)
	 */
	public static BitmapFont getFont(String name, int size) {
		if( !FONTS.containsKey(name + "_" + size) ) loadFont(name, size);
		
		BitmapFont font = FONTS.get(name + "_" + size);
		return (font != null) ? font : DEFAULT;
	}
	
	/**
	 * Loads the bitmap font in from file and adds it to the FONTS maps
	 * 
	 * @param name name of font to load
	 */
	private static void loadFont(String name, int size) {
		FileHandle handle = Gdx.files.internal("fonts/" + name + ".ttf");
		
		if(!handle.exists()) {
			FONTS.put(name, null);
			if(Meta.DEBUG) System.out.println("Could not find font file: fonts/" + name + ".ttf");
			
		} else {
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(handle);
			FreeTypeFontParameter parameter = new FreeTypeFontParameter();
			parameter.size = size;
			
			FONTS.put(name + "_" + size, generator.generateFont(parameter));
		}
	}
	
}
// End of class.