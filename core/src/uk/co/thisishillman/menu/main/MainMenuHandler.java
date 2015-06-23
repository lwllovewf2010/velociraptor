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
package uk.co.thisishillman.menu.main;

import uk.co.thisishillman.MainGame;
import uk.co.thisishillman.Settings;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Handles switching between screens in a single menu context.
 * 
 * @author Michael Hillman
 */
public class MainMenuHandler {

	// Main game object
	private final MainGame game;
	
	// Main stage object
	private final Stage stage;
	
	// Common background music
	private Sound backgroundMusic;
	
	// Common click SFX
	private Sound clickSound;
	
	// ========== Menu Screens ========== //
	private Screen mainScreen;
	
	private Screen exitScreen;
	
	private Screen optionsScreen;
	
	private Screen creditsScreen;
	// ========== Menu Screens ========== //
	
	/**
	 * Initialise a new handler for the main menu system.
	 * 
	 * @param game main game object
	 * @param stage main stage object
	 */
	public MainMenuHandler(MainGame game, Stage stage) {
		this.game = game;
		this.stage = stage;
	}
	
	/**
	 * Initialise variables
	 */
	public void initialise() {
		// Setup audio
		backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("audio/protofunk.ogg"));
		//backgroundMusic.loop(Settings.MUSIC_VOLUME);
		clickSound = Gdx.audio.newSound(Gdx.files.internal("audio/mouse_click.ogg"));
		
		// Input processing
		Gdx.input.setInputProcessor(stage);
		
		// Initialise screens
		this.mainScreen = new MainScreen(this);
		this.exitScreen = new ExitScreen(this);
		this.optionsScreen = new OptionsScreen(this);
		this.creditsScreen = new CreditsScreen(this);
	}
	
	/**
	 * Return the mouse click sound
	 * 
	 * @return
	 */
	public Sound getClickSound() {
		return clickSound;
	}
	
	/**
	 * Return the main game object
	 * 
	 * @return
	 */
	public Game getGameObject() {
		return game;
	}
	
	/**
	 * Return the current stage object
	 * 
	 * @return
	 */
	public Stage getStageObject() {
		return stage;
	}
	
	/**
	 * Switch to the main menu screen
	 */
	public void goToMainScreen() {
		this.game.setScreen(this.mainScreen);
		this.mainScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	/** 
	 * Switch to the exit confirmation screen
	 */
	public void goToExitScreen() {
		this.game.setScreen(this.exitScreen);
		this.exitScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	/**
	 * Switch to the options screen
	 */
	public void goToOptionsScreen() {
		this.game.setScreen(this.optionsScreen);
		this.optionsScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	/**
	 * Switch to the creidts screen
	 */
	public void goToCreditsScreen() {
		this.game.setScreen(this.creditsScreen);
		this.creditsScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	/**
	 * Dispose of loaded assets 
	 */
	public void dispose() {
		backgroundMusic.dispose();
		clickSound.dispose();
		
		mainScreen.dispose();
		optionsScreen.dispose();
		exitScreen.dispose();
	}
	
}
// End of class.