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

import java.util.Arrays;
import java.util.LinkedList;

import uk.co.thisishillman.MainGame;
import uk.co.thisishillman.Settings;

import com.badlogic.gdx.audio.Sound;

/**
 * Handles rotating splash screens untils all have been displayed
 * 
 * @author Michael Hillman
 */
public class SplashRotator {

	// Milliseconds each splash screen should display
	public static final int SCREEN_TIME = 4500;
	
	// Queue of all splash screens
	private final LinkedList<SplashScreen> screens;
	
	// CUrrently displayed screen
	private SplashScreen currentScreen;
	
	// System time that last screen started (in ms)
	private int screenStart;
	
	// Sound to play at each new screen
	private Sound sfx;

	/**
	 * Initialises a new splash screen rotator with the input screens
	 * 
	 * @param screens array of all splash screens
	 */
	public SplashRotator(SplashScreen... screens) {
		this.screens = new LinkedList<>();
		this.screens.addAll( Arrays.asList(screens) );
	}
	
	/**
	 * Set audio to play at the start of each new screen
	 * 
	 * @param sfx Sound to play at each new screen
	 */
	public void setAudio(Sound sfx) {
		this.sfx = sfx;
	}
	
	
	/**
	 * Update the game screen to the next splash screen (if any exists)
	 * 
	 * @param game
	 */
	public void update(MainGame game) {
		
		// Dispose and do nothing if no screens are left
		if(screens.isEmpty() && currentScreen == null) return;
		
		if(currentScreen != null) currentScreen.render(0.0f);
		
		// Check to see if the limit is up for the current screen
		int deltaTime = ((int) System.currentTimeMillis()) - screenStart;
		
		if(deltaTime >= SCREEN_TIME) {
			
			// If no screens are left then dispose of sound & move onto next thing
			if(screens.isEmpty()) {
				if(sfx != null)  {
					sfx.dispose();
				}
				
				currentScreen = null;
				game.moveToMainMenu();
				return;
			}
			
			// Dispose the old screen and move to next splash screen
			SplashScreen newScreen = screens.poll();
			game.setScreen(newScreen);
			
			if(currentScreen != null) currentScreen.dispose();
			currentScreen = newScreen;
			
			screenStart = (int) System.currentTimeMillis();
			if(sfx != null) {
				sfx.play(Settings.EFFECT_VOLUME);
			}
			
		}
	}
	
}
// End of class