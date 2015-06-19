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
package uk.co.thisishillman.menu;

import uk.co.thisishillman.text.FontStore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

/**
 * Displays a single screen with centre aligned text.
 * 
 * @author Michael Hillman
 */
public class MainMenu implements Screen {

	// Label text
	private final String text;
	
	// Main stage
	private final Stage stage;
	
	// For text display
	private Label label;
	
	// Looping background music
	private Sound backgroundMusic;
	
	/**
	 * Initialise a new TextScreen with the input text
	 * 
	 * @param stage
	 * @param text
	 */
	public MainMenu(Stage stage) {
		this.stage = stage;
		
		this.text = "New Game  |  Options  |  Credits  |  Exit";
	}
	
	/** 
	 * Create sprite batches and initialise fonts
	 */
	@Override
	public void show() {
		// Setup menu label
		BitmapFont font  = FontStore.getFont("minecraftia", 16); 
		this.label = new Label(text, new LabelStyle(font, Color.WHITE));
		stage.addActor(label);
		
		// Setup audio
		backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("audio/protofunk.ogg"));
		
		// Force re-size update
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	/**
	 * Dispose of loaded variables
	 */
	@Override
	public void dispose() {
		backgroundMusic.dispose();
		backgroundMusic = null;
	}
	
	/**
	 * Calls dispose()
	 */
	@Override
	public void hide() {
		dispose();
	}

	/**
	 * Bottom left align after resize
	 */
	@Override
	public void resize(int width, int height) {
		label.setBounds(15, 15, label.getWidth(), label.getHeight());
	}

	/**
	 * Main render loop, draws text on screen
	 */
	@Override
	public void render(float delta) {
        if(backgroundMusic != null) {
        	backgroundMusic.loop();
        }
	}

	// Not used
	@Override
	public void pause() { }

	// Not used
	@Override
	public void resume() { }
	
}
// End of class.