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
import com.badlogic.gdx.utils.Align;

/**
 * Simple screen for changing user options
 * 
 * 
 * Resolution = 800x600, 1024x768, 1280x768, 1280x1024, 1366x768, 1920x1080 
 * Fullscreen = Yes, No
 * Music Volume = 0.0f - 1.0f
 * Effect Volume = 0.0f - 1.0f
 * Display Tips = Yes, No
 * 
 * 
 * @author Michael Hillman
 */
public class OptionsScreen implements Screen {

	// Menu handler
	private final MainMenuHandler handler;
	
	// Label styles
	private LabelStyle enabledStyle, disabledStyle, hoverStyle;
	
	// Labels for controllable components
	private Label resolutionLabel, fullscreenLabel, musicLabel, effectsLabel, tipsLabel;
	
	// Label to return to main menu
	private Label returnLabel;
	
	// Background texture
	private Texture backingTex;
	
	// Background image
	private Image background;
	
	/**
	 * Initialise a new TextScreen with the input text
	 * 
	 * @param handler
	 */
	public OptionsScreen(MainMenuHandler handler) {
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
		initialiseLabelStyles();
		initialiseLabels();
	}
	
	/**
	 * Initialise UI labels styles
	 */
	private void initialiseLabelStyles() {
		enabledStyle = new LabelStyle(FontStore.getFont("minecraftia", 16), Color.WHITE);
		disabledStyle = new LabelStyle(FontStore.getFont("minecraftia", 16), Color.GRAY);
		hoverStyle = new LabelStyle(FontStore.getFont("minecraftia", 14), Color.GRAY);
	}
	
	/**
	 * Initialise UI labels
	 */
	private void initialiseLabels() {
		// Initialise no label
		returnLabel = new Label("Return", enabledStyle);
		returnLabel.setAlignment(Align.right);
		returnLabel.addListener(new ClickListener() {
			@Override
			public void enter(InputEvent ev, float x, float y, int pt, Actor from) {
				returnLabel.setStyle(hoverStyle);
			}

			@Override
			public void exit(InputEvent ev, float x, float y, int pt, Actor to) {
				returnLabel.setStyle(enabledStyle);
			}

			@Override
			public void clicked(InputEvent ev, float x, float y) {
				handler.getClickSound().play(Settings.EFFECT_VOLUME);
				handler.goToMainScreen();
			}
		});
		this.handler.getStageObject().addActor(returnLabel);
	}

	/**
	 * Dispose of loaded variables
	 */
	@Override
	public void dispose() {
		if(backingTex != null) backingTex.dispose();
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
		if(returnLabel != null)  	returnLabel.setBounds(width - 130, 25, 100, 30);
		
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