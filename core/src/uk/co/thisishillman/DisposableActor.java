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
package uk.co.thisishillman;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Abstract class to add functionality to Scene2D Actors allowing create & dispose methods.
 * 
 * @author Michael Hillman
 */
public abstract class DisposableActor extends Actor {

	/**
	 * Initialise assets needed by actor.
	 */
	public abstract void create();
	
	/**
	 * Dispose of assets loaded by the actor.
	 */
	public abstract void dispose();
	
	/** 
	 * Resize the actor's assets after a window resize
	 * 
	 * @param width new width
	 * @param height new height
	 */
	public abstract void resize(int width, int height);
	
	/** 
	 * Return info on this actor for debugging.
	 * 
	 * @return debuggin info
	 */
	public abstract String getDebugString();
	
}
// End of class.