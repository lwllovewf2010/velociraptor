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

import uk.co.thisishillman.menus.loading.LoadingScreen;
import uk.co.thisishillman.menus.loading.SplashRotator;
import uk.co.thisishillman.menus.loading.SplashScreen;
import uk.co.thisishillman.menus.main.MainMenuHandler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * 
 * 
 * @author Michael Hillman
 */
public class MainGame extends Game  {

	// Handles rotating splash screens
	private SplashRotator splashRotator;
	
	// Main menu handler
	private MainMenuHandler menuHandler;
	
	// Scene2D stage
	private Stage stage;
	
	private LoadingScreen loading;
	
	/**
	 * Initialise scene 2d variables
	 */
	@Override
	public void create() {
		stage = new Stage();
		
		// Initialise the splash rotator
		splashRotator = new SplashRotator(
				new SplashScreen(stage, "lib_gdx_logo.png"),
				new SplashScreen(stage, "hillman_logo.png")
		);
		splashRotator.setAudio(Gdx.audio.newSound(Gdx.files.internal("audio/mouse_click.ogg")));
	}
	
	/**
	 * 
	 */
	public void moveToMainMenu() {
		menuHandler = new MainMenuHandler(this, stage);
		menuHandler.initialise();
		menuHandler.goToMainScreen();
	}
	
	/**
	 * Dispose of loaded variables
	 */
	@Override
	public void dispose() {
		if(menuHandler != null) menuHandler.dispose();
		stage.dispose();
	}

	/**
	 * Update after window resize
	 * 
	 * @param width new width
	 * @param height new height
	 */
	@Override
	public void resize(int width, int height) {
		// TODO
	}
	
	/**
	 * Main draw loop
	 */
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        
        if(splashRotator != null) splashRotator.update(this);
        if(loading != null) loading.render(0.0f);
	}

	// Not used
	@Override
	public void pause() { }

	// Not used
	@Override
	public void resume() { }

}
// End of class.