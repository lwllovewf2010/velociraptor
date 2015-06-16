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

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * 
 * 
 * @author Michael
 */
public class FadeScreen implements ApplicationListener {

	// Name of sprite texture file
	private final String spriteName;
	
	// Batch for drawing
	private SpriteBatch batch;
	
	// Main sprite for fade
	private Sprite sprite;
	
	// Texture for main sprite
	private Texture texture;
	
	// 
	private TweenManager tweenManager;
	
	private long startTime;
	
	private long deltaTime;
	
	/**
	 * Initialise a new fade screen with the input sprite
	 */
	public FadeScreen(String spriteName) {
		this.spriteName = spriteName;
		this.tweenManager = new TweenManager();
	}
	
	/**
	 * Initialise sprites etc
	 */
	@Override
	public void create() {
		texture   = new Texture(Gdx.files.internal("images/" + spriteName));
		sprite    = new Sprite(texture);
		batch     = new SpriteBatch();
		startTime = TimeUtils.millis();
		
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		
		Tween.to(sprite, SpriteAccessor.FADE, 2500.0f)
			.delay(2500.0f)
			.target(0.0f)
			.start(tweenManager);
	}

	/**
	 * Dispose loaded variables
	 */
	@Override
	public void dispose() {
		texture.dispose();
		batch.dispose();
	}
	
	/**
	 * Re-center after resize
	 */
	@Override
	public void resize(int width, int height) { 
		float xPos = ( width - sprite.getWidth() ) / 2;
		float yPos = (height - sprite.getHeight()) / 2;
		sprite.setBounds(xPos, yPos, sprite.getWidth(), sprite.getHeight());
	}

	/**
	 * Main render loop, draws sprite on screen
	 */
	@Override
	public void render() {
		deltaTime = (TimeUtils.millis() - startTime);
		tweenManager.update(deltaTime);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        sprite.draw(batch);
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