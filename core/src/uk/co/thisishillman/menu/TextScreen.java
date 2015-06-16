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

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Displays a single screen with centre aligned text.
 * 
 * @author Michael Hillman
 */
public class TextScreen implements ApplicationListener {

	// For layout work
	private final GlyphLayout layout;
	
	// Text to draw
	private final String text;
	
	// Sprite batch for drawing
	private SpriteBatch batch;
	
	// Font to use for text
	private BitmapFont font;

	// Positions of text on screen
	private float xPos, yPos;
	
	/**
	 * Initialise a new TextScreen with the input text
	 */
	public TextScreen(String text) {
		this.text   = text;
		this.layout = new GlyphLayout();
	}
	
	/** 
	 * Create sprite batches and initialise fonts
	 */
	@Override
	public void create() {
		font  = FontStore.getFont("minecraftia", 16); 
		batch = new SpriteBatch();
		
		layout.setText(font, text);
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	/**
	 * Dispose of loaded variables
	 */
	@Override
	public void dispose() {
		font.dispose();
		batch.dispose();
	}
	
	/**
	 * Re-center after resize
	 */
	@Override
	public void resize(int width, int height) {
		xPos = ( width - layout.width ) / 2;
		yPos = (height - layout.height) / 2;
	}

	/**
	 * Main render loop, draws text on screen
	 */
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        font.draw(batch, text, xPos, yPos);
        batch.end();
	}

	// Not used
	@Override
	public void pause() { }

	// Not used
	@Override
	public void resume() { }
	
}
// End of class.