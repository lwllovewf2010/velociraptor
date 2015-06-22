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

import uk.co.thisishillman.MainGame;
import uk.co.thisishillman.text.FontStore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Displays a single screen with centre aligned text.
 * 
 * @author Michael Hillman
 */
public class MainMenu implements Screen {

	// Main stage
	private final Stage stage;
	
	// Game object
	private final MainGame game;
	
	// Looping background music
	private Sound backgroundMusic;
	
	// UI Labels
	private Label newGameLabel, optionsLabel, exitLabel;
	
	// UI Label styles
	private LabelStyle labelStyle, hoverStyle;
	
	/**
	 * Initialise a new TextScreen with the input text
	 * 
	 * @param stage
	 * @param game
	 */
	public MainMenu(Stage stage, MainGame game) {
		this.stage = stage;
		this.game = game;
	}
	
	/** 
	 * Create sprite batches and initialise fonts
	 */
	@Override
	public void show() {
		// Setup audio
		backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("audio/protofunk.ogg"));
		
		// Force re-size update
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		// Init labels
		Gdx.input.setInputProcessor(stage);
		initialiseLabels();
	}
	
	/**
	 * 
	 */
	private void initialiseLabels() {
		labelStyle = new LabelStyle(FontStore.getFont("minecraftia", 16), Color.WHITE);
		hoverStyle = new LabelStyle(FontStore.getFont("minecraftia", 14), Color.GRAY);
		
		exitLabel = new Label("Exit", labelStyle);
		exitLabel.addListener(new ClickListener() {
			@Override
			public void enter(InputEvent ev, float x, float y, int pt, Actor from) {
				exitLabel.setStyle(hoverStyle);
			}
			
			@Override
			public void exit(InputEvent ev, float x, float y, int pt, Actor to) {
				exitLabel.setStyle(labelStyle);
			}
		});
		stage.addActor(exitLabel);
		
		optionsLabel = new Label("Options", labelStyle);
		optionsLabel.addListener(new ClickListener() {
			@Override
			public void enter(InputEvent ev, float x, float y, int pt, Actor from) {
				optionsLabel.setStyle(hoverStyle);
			}
			
			@Override
			public void exit(InputEvent ev, float x, float y, int pt, Actor to) {
				optionsLabel.setStyle(labelStyle);
			}
		});
		stage.addActor(optionsLabel);
		
		newGameLabel = new Label("New Game", labelStyle);
		newGameLabel.addListener(new ClickListener() {
			@Override
			public void enter(InputEvent ev, float x, float y, int pt, Actor from) {
				newGameLabel.setStyle(hoverStyle);
			}
			
			@Override
			public void exit(InputEvent ev, float x, float y, int pt, Actor to) {
				newGameLabel.setStyle(labelStyle);
			}
		});
		stage.addActor(newGameLabel);
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
		if(exitLabel != null)    exitLabel.setBounds(   30, 15, 100, 30);
		if(optionsLabel != null) optionsLabel.setBounds(30, 45, 100, 30);
		if(newGameLabel != null) newGameLabel.setBounds(30, 75, 100, 30);
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