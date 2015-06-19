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

import uk.co.thisishillman.sprites.ImageAccessor;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Simple screen for displaying a logo on a black background that 
 * then fades to black (for splash screens etc).
 * 
 * @author Michael Hillman
 */
public class SplashScreen implements Screen {

	// Initial delay in milliseconds
	public static final int DELAY    = 1500;
	
	// Fade duration in milliseconds
	public static final int DURATION = 2500;
	
	// Manager for fading tween
	private final TweenManager tweenManager;
	
	// File name of sprite texture file
	private final String spriteName;
	
	// Main stage holding screen
	private final Stage stage;
	
	// Image for texture;
	private Image image;
	
	// Texture for main sprite
	private Texture texture;
	
	// Time variables for updating the tween manager
	private int startTime, deltaTime;
	
	/**
	 * Initialise a new fade screen with the input sprite
	 * 
	 * @param stage
	 * @param spriteName
	 */
	public SplashScreen(Stage stage, String spriteName) {
		this.stage = stage;
		this.spriteName = spriteName;
		this.tweenManager = new TweenManager();
	}
	
	/**
	 * Initialise textures, sprites, tweens etc
	 */
	@Override
	public void show() {
		texture = new Texture(Gdx.files.internal("images/" + spriteName));
		image   = new Image(texture);
		
		stage.addActor(image);
		
		Tween.registerAccessor(Image.class, new ImageAccessor());
		
		Tween.to(image, ImageAccessor.FADE, DURATION)
			.delay(DELAY)
			.target(0.0f)
			.start(tweenManager);
	}

	/**
	 * Dispose of loaded variables
	 */
	@Override
	public void dispose() {
		texture.dispose();
		tweenManager.killAll();
	}
	
	/**
	 * Calls dispose()
	 */
	@Override
	public void hide() {
		dispose();
	}

	
	/**
	 * Re-center the sprite after window resize
	 */
	@Override
	public void resize(int width, int height) { 
		float xPos = ( width - image.getWidth() ) / 2;
		float yPos = (height - image.getHeight()) / 2;
		
		image.setX(xPos);
		image.setY(yPos);
	}

	/**
	 * Main render loop, draws sprite on screen
	 * 
	 * @param delta
	 */
	@Override
	public void render(float delta) {
		if(startTime <= 0) startTime = (int) System.currentTimeMillis();
		
		deltaTime = ((int) System.currentTimeMillis() - startTime);
		tweenManager.update(deltaTime / 150);	// Fudged as Tween's don't seem to actually work in milliseconds
	}

	// Not used
	@Override
	public void pause() { }

	// Not used
	@Override
	public void resume() { }

}
// End of class.