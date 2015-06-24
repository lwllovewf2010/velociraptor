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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

/**
 * Displays a single screen for game exit confirmation
 * 
 * @author Michael Hillman
 */
public class ExitScreen implements Screen {

	// Main menu handler
	private final MainMenuHandler handler;
	
	// UI Labels
	private Label descLabel, yesLabel, noLabel;
	
	// UI Label styles
	private LabelStyle labelStyle, hoverStyle;
	
	/**
	 * Initialise a new exit screen
	 * 
	 * @param handler
	 */
	public ExitScreen(MainMenuHandler handler) {
		this.handler = handler;
	}
	
	/** 
	 * Initialise assets and labels
	 */
	@Override
	public void show() {
		// Init labels
		initialiseLabels();
	}
	
	/**
	 * Initialise UI labels
	 */
	private void initialiseLabels() {
		labelStyle = new LabelStyle(FontStore.getFont("minecraftia", 16), Color.WHITE);
		hoverStyle = new LabelStyle(FontStore.getFont("minecraftia", 14), Color.GRAY);
		
		// Initialise description label
		descLabel = new Label("Exit the game and return to your desktop?", labelStyle);
		descLabel.setAlignment(Align.center);
		this.handler.getStageObject().addActor(descLabel);
		
		// Initialise yes label
		yesLabel = new Label("Exit Game", labelStyle);
		yesLabel.setAlignment(Align.right);
		yesLabel.addListener(new ClickListener() {
			@Override
			public void enter(InputEvent ev, float x, float y, int pt, Actor from) {
				yesLabel.setStyle(hoverStyle);
			}
			
			@Override
			public void exit(InputEvent ev, float x, float y, int pt, Actor to) {
				yesLabel.setStyle(labelStyle);
			}
			
			@Override
			public void clicked(InputEvent ev, float x, float y) {
				Settings.saveSettings();
				handler.getGameObject().dispose();
				System.exit(0);
			}
		});
		this.handler.getStageObject().addActor(yesLabel);
		
		// Initialise no label
		noLabel = new Label("Continue Playing", labelStyle);
		noLabel.addListener(new ClickListener() {
			@Override
			public void enter(InputEvent ev, float x, float y, int pt, Actor from) {
				noLabel.setStyle(hoverStyle);
			}
			
			@Override
			public void exit(InputEvent ev, float x, float y, int pt, Actor to) {
				noLabel.setStyle(labelStyle);
			}
			
			@Override
			public void clicked(InputEvent ev, float x, float y) {
				handler.getClickSound().play(Settings.EFFECT_VOLUME);
				handler.goToMainScreen();
			}
		});
		this.handler.getStageObject().addActor(noLabel);
	}

	/**
	 * Dispose of loaded variables
	 */
	@Override
	public void dispose() {
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
		if(descLabel != null)   descLabel.setBounds(15, (height / 2) - 10, width - 30, 20);
		if(yesLabel != null)  	yesLabel.setBounds(width - 130, 25, 100, 30);
		if(noLabel != null) 	noLabel.setBounds(30, 25, 200, 30);
	}

	/**
	 * Main render loop, draws text on screen
	 */
	@Override
	public void render(float delta) {
		// Nothing!
	}

	// Not used
	@Override
	public void pause() { }

	// Not used
	@Override
	public void resume() { }
	
}
// End of class.