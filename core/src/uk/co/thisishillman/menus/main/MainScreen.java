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
package uk.co.thisishillman.menus.main;

import uk.co.thisishillman.Settings;
import uk.co.thisishillman.text.FontStore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Displays a single main menu screen
 * 
 * @author Michael Hillman
 */
public class MainScreen implements Screen {

	// Menu handler
	private final MainMenuHandler handler;
	
	// UI Labels
	private Label newGameLabel, optionsLabel, creditsLabel, exitLabel;
	
	// UI Label styles
	private LabelStyle labelStyle, hoverStyle;
	
	// Background texture
	private Texture backingTex;
	
	// Background image
	private Image background;
	
	/**
	 * Initialise a new TextScreen with the input text
	 * 
	 * @param handler
	 */
	public MainScreen(MainMenuHandler handler) {
		this.handler = handler;
	}
	
	/** 
	 * Create sprite batches and initialise fonts
	 */
	@Override
	public void show() {
		// Init background texture
		this.backingTex = new Texture(Gdx.files.internal("images/background.jpg"));
		this.background = new Image(backingTex);
		this.handler.getStageObject().addActor(background);
				
		// Init labels
		initialiseLabels();
	}
	
	/**
	 * Initialise UI labels
	 */
	private void initialiseLabels() {
		labelStyle = new LabelStyle(FontStore.getFont("minecraftia", 16), Color.WHITE);
		hoverStyle = new LabelStyle(FontStore.getFont("minecraftia", 14), Color.GRAY);
		
		// Initialise exit label
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
			
			@Override
			public void clicked(InputEvent ev, float x, float y) {
				handler.getClickSound().play(Settings.EFFECT_VOLUME);
				handler.goToExitScreen();
			}
		});
		this.handler.getStageObject().addActor(exitLabel);
		
		// Initialise options label
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
			
			@Override
			public void clicked(InputEvent ev, float x, float y) {
				optionsLabel.remove();
				newGameLabel.remove();
				exitLabel.remove();
				creditsLabel.remove();
				
				handler.getClickSound().play(Settings.EFFECT_VOLUME);
				handler.goToOptionsScreen();
			}
		});
		this.handler.getStageObject().addActor(optionsLabel);
		
		// Initialise new game label
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
			
			@Override
			public void clicked(InputEvent ev, float x, float y) {
				handler.getClickSound().play(Settings.EFFECT_VOLUME);
			}
		});
		this.handler.getStageObject().addActor(newGameLabel);
		
		// Initialise credits label
		creditsLabel = new Label("Credits", labelStyle);
		creditsLabel.addListener(new ClickListener() {
			@Override
			public void enter(InputEvent ev, float x, float y, int pt, Actor from) {
				creditsLabel.setStyle(hoverStyle);
			}

			@Override
			public void exit(InputEvent ev, float x, float y, int pt, Actor to) {
				creditsLabel.setStyle(labelStyle);
			}

			@Override
			public void clicked(InputEvent ev, float x, float y) {
				handler.getClickSound().play(Settings.EFFECT_VOLUME);
				handler.goToCreditsScreen();
			}
		});
		this.handler.getStageObject().addActor(creditsLabel);
	}

	/**
	 * Dispose of loaded variables
	 */
	@Override
	public void dispose() {
		backingTex.dispose();
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
		if(creditsLabel != null) creditsLabel.setBounds(30, 45, 100, 30);
		if(optionsLabel != null) optionsLabel.setBounds(30, 75, 100, 30);
		if(newGameLabel != null) newGameLabel.setBounds(30, 105, 100, 30);
		
		if(background != null) background.setBounds(0, 0, width, height);
	}

	/**
	 * Main render loop, draws text on screen
	 */
	@Override
	public void render(float delta) { 
		// Nothing?
	}
	
	// Not used
	@Override
	public void pause() { }

	// Not used
	@Override
	public void resume() { }
	
}
// End of class.