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
public class TextScreen implements Screen {

	// Label text
	private final String text;
	
	// Main stage
	private final Stage stage;
	
	// For text display
	private Label label;
	
	/**
	 * Initialise a new TextScreen with the input text
	 * 
	 * @param stage
	 * @param text
	 */
	public TextScreen(Stage stage, String text) {
		this.stage = stage;
		this.text = text;
	}
	
	/** 
	 * Create sprite batches and initialise fonts
	 */
	@Override
	public void show() {
		BitmapFont font  = FontStore.getFont("minecraftia", 14); 
		this.label = new Label(text, new LabelStyle(font, Color.WHITE));
		
		stage.addActor(label);
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	/**
	 * Dispose of loaded variables
	 */
	@Override
	public void dispose() {
		// Nothing 
	}
	
	/**
	 * Calls dispose()
	 */
	@Override
	public void hide() {
		dispose();
	}

	/**
	 * Re-center after resize
	 */
	@Override
	public void resize(int width, int height) {
		float xPos = ( width - label.getWidth()  ) / 2;
		float yPos = (height - label.getHeight() ) / 2;
		label.setBounds(xPos, yPos, label.getWidth(), label.getHeight());
	}

	/**
	 * Main render loop, draws text on screen
	 */
	@Override
	public void render(float delta) {
        //font.draw(batch, text, xPos, yPos);
	}

	// Not used
	@Override
	public void pause() { }

	// Not used
	@Override
	public void resume() { }
	
}
// End of class.