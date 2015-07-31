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
package uk.co.thisishillman.menus.loading;

import uk.co.thisishillman.text.FontStore;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;

/**
 * Simple screen for displaying a logo on a black background that 
 * then fades to black (for splash screens etc).
 * 
 * @author Michael Hillman
 */
public class LoadingScreen implements Screen {

	// Fade duration in milliseconds
	public static final int DURATION = 3000;
	
	// Manager for fading tween
	private final TweenManager tweenManager;
	
	// Main stage holding screen
	private final Stage stage;
	
	// Label for snippet text
	private Label snippetLabel;
	
	// Time variables for updating the tween manager
	private long startTime = Long.MIN_VALUE, deltaTime = Long.MIN_VALUE;
	
	/**
	 * Initialise a new fade screen with the input sprite
	 * 
	 * @param stage
	 */
	public LoadingScreen(Stage stage) {
		this.stage = stage;
		this.tweenManager = new TweenManager();
	}
	
	public Label getSnippetLabel() {
		return snippetLabel;
	}
	
	public TweenManager getTweenManager() {
		return tweenManager;
	}
	
	public void resetTweenStart() {
		this.startTime = System.currentTimeMillis();
	}
	
	/**
	 * Initialise textures, sprites, tweens etc
	 */
	@Override
	public void show() {
		// Initialise label style
		LabelStyle labelStyle = new LabelStyle(FontStore.getFont("minecraftia", 12), Color.WHITE);
		
		String snippet = "\"" + SnippetPool.getRandomSnippet();
		snippet = snippet.replaceAll(" - ", "\" - ");
		
		snippetLabel = new Label(snippet, labelStyle);
		snippetLabel.setAlignment(Align.center);
		snippetLabel.setWrap(true);
		this.stage.addActor(snippetLabel);
		
	    Tween.registerAccessor(Label.class, new LabelAccessor());
	    SnippetCallBack snippetCallBack = new SnippetCallBack(this);
		
		Tween.to(snippetLabel, LabelAccessor.FADE, DURATION)
			.delay(15_000.0f)
			.target(0.0f)
			.setCallbackTriggers(TweenCallback.END)
			.setCallback(snippetCallBack)
			.start(tweenManager);
	}

	/**
	 * Dispose of loaded variables
	 */
	@Override
	public void dispose() {
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
		if(snippetLabel != null) snippetLabel.setBounds(width/4, height/4, width/2, height/2);
	}

	/**
	 * Main render loop, draws sprite on screen
	 * 
	 * @param delta
	 */
	@Override
	public void render(float delta) {
		if(startTime < 0) startTime = System.currentTimeMillis();
		
		deltaTime = (System.currentTimeMillis() - startTime) / 200;
		tweenManager.update(deltaTime);	// Fudged as Tween's don't seem to actually work in milliseconds
	}

	// Not used
	@Override
	public void pause() { }

	// Not used
	@Override
	public void resume() { }

}
// End of class.