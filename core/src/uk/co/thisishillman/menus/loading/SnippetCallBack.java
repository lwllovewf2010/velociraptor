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

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Tween callback that handles changing label text in between fading animations
 * 
 * @author Michael Hillman
 */
public class SnippetCallBack implements TweenCallback {

	//
	private LoadingScreen screen;
	
	//
	private int tweenCount;
	
	/**
	 * @param snippetLabel
	 */
	public SnippetCallBack(LoadingScreen screen) {
		this.screen = screen;
	}
	
	/**
	 * 
	 */
	@Override
	public void onEvent(int arg0, BaseTween<?> arg1) {
		screen.resetTweenStart();
		tweenCount++;
		
		if( (tweenCount % 2) == 0) {
			Tween.to(screen.getSnippetLabel(), LabelAccessor.FADE, LoadingScreen.DURATION)
				.delay(10_000.0f)
				.target(0.0f)
				.setCallbackTriggers(TweenCallback.END)
				.setCallback(this)
				.start(screen.getTweenManager());
			
		} else {
			String snippet = "\"" + SnippetPool.getRandomSnippet();
			snippet = snippet.replaceAll(" - ", "\" - ");
			screen.getSnippetLabel().setText(snippet);
			
			Tween.to(screen.getSnippetLabel(), LabelAccessor.FADE, LoadingScreen.DURATION)
				.target(1.0f)
				.setCallbackTriggers(TweenCallback.END)
				.setCallback(this)
				.start(screen.getTweenManager());
		}
	}
	
}
// End of class.