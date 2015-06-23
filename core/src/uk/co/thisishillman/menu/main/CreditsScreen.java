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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

/**
 * Scrollable credits screen
 * 
 * @author Michael Hillman
 */
public class CreditsScreen implements Screen {

	// Main menu handler
	private final MainMenuHandler handler;
	
	// Scroll pane containing credits
	private ScrollPane scroll;
	
	// UI Labels
	private Label returnLabel, creditsLabel;
	
	// UI Label styles
	private LabelStyle labelStyle, hoverStyle;
	
	// Background texture
	private Texture backingTex;
	
	// Background image
	private Image background;
	
	/**
	 * Initialise a new exit screen
	 * 
	 * @param handler
	 */
	public CreditsScreen(MainMenuHandler handler) {
		this.handler = handler;
	}
	
	/** 
	 * Initialise assets and labels
	 */
	@Override
	public void show() {
		// Init background texture
		this.backingTex = new Texture(Gdx.files.internal("images/background.jpg"));
		this.background = new Image(backingTex);
		this.handler.getStageObject().addActor(background);
				
		// Init labels
		initialiseLabels();
		readCreditsFile();
		
		// Init scroll panel
		this.scroll = new ScrollPane(creditsLabel);
		this.scroll.setForceScroll(true, true);
		this.scroll.setScrollY(0);
		this.scroll.setScrollX(0);
		this.handler.getStageObject().addActor(scroll);
	}
	
	/**
	 * Initialise UI labels
	 */
	private void initialiseLabels() {
		labelStyle = new LabelStyle(FontStore.getFont("minecraftia", 16), Color.WHITE);
		hoverStyle = new LabelStyle(FontStore.getFont("minecraftia", 14), Color.GRAY);
		
		// Initialise return label
		returnLabel = new Label("Return", labelStyle);
		returnLabel.addListener(new ClickListener() {
			@Override
			public void enter(InputEvent ev, float x, float y, int pt, Actor from) {
				returnLabel.setStyle(hoverStyle);
			}
			
			@Override
			public void exit(InputEvent ev, float x, float y, int pt, Actor to) {
				returnLabel.setStyle(labelStyle);
			}
			
			@Override
			public void clicked(InputEvent ev, float x, float y) {
				handler.getClickSound().play(Settings.EFFECT_VOLUME);
				handler.goToMainScreen();
				
				returnLabel.remove();
				creditsLabel.remove();
				scroll.remove();
				background.remove();
			}
		});
		this.handler.getStageObject().addActor(returnLabel);
		
		// Initialise Credits label
		creditsLabel = new Label("", labelStyle);
		creditsLabel.setAlignment(Align.center);
		creditsLabel.setFillParent(true);
		
		this.handler.getStageObject().addActor(creditsLabel);
	}
	
	/**
	 * Read the credits.txt file
	 */
	private void readCreditsFile() {
		StringBuilder builder = new StringBuilder();
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) classLoader = Class.class.getClassLoader();
		
		BufferedReader buffReader = null;
		InputStreamReader inStreamReader = null;
		InputStream inStream = null;
		
		try {
			inStream = classLoader.getResourceAsStream("credits.txt");
			inStreamReader = new InputStreamReader(inStream);
			buffReader = new BufferedReader(inStreamReader);
			
			String line = null;
			
			while((line = buffReader.readLine()) != null) {
				builder.append(line + "\n");
			}
			
		} catch(IOException ioExcep) {
			System.err.println("ERROR: Could not read in credits.txt file.");
			
		} finally {
			try {
				inStream.close();
				inStreamReader.close();
				buffReader.close();
			} catch(IOException closeExcep) { }
		}
		
		creditsLabel.setText(builder.toString());
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
		if(returnLabel != null) returnLabel.setBounds(width - 130, 25, 100, 30);
		if(scroll != null) scroll.setBounds(100, 150, width - 200, height - 150);
		
		if(background != null) background.setBounds(0, 0, width, height);
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