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

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

/**
 * 
 * @author Michael Hillman
 */
public class LabelStyleAccessor implements TweenAccessor<LabelStyle> {

	// Identifier for fade mode
	public static final int FADE = 0;
	
	/**
	 * 
	 */
	@Override
	public int getValues(LabelStyle target, int tweenType, float[] returnValues) {

		switch(tweenType) {
			case FADE :
				returnValues[0] = target.fontColor.a;
				return 1;
		}
		
		return -1;
	}

	/**
	 * 
	 */
	@Override
	public void setValues(LabelStyle target, int tweenType, float[] newValues) {
		
		switch(tweenType) {
			case FADE :
				target.fontColor.a = newValues[0];
				break;
		}
	}

}
// End of class.