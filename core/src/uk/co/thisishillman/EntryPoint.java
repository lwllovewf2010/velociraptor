/*
 * Copyright (c) 2015 M Hillman - All Rights Reserved
 * 
 * This application and all inherent data, source files, information and graphics are 
 * the copyright and sole property of M Hillman (thisishillman.co.uk).
 * 
 * Any unauthorised redistribution or reproduction of part, or all, of the contents of this 
 * application in any form is prohibited under UK Copyright Law. You may not, except with the 
 * express written permission of CMCL Innovations, distribute or commercially exploit this
 * application or it's content. All other rights reserved.
 * 
 * For more information please contact business<@>thisishillman.co.uk
 */
package uk.co.thisishillman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 
 * @author M Hillman
 */
public class EntryPoint extends ApplicationAdapter {
	
	private SpriteBatch batch;
	private Sprite sprite;
	
	
	    @Override
	    public void create() {  
	    	batch = new SpriteBatch();
	    	
	    	if(Settings.FULLSCREEN) {
	    		Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width, Gdx.graphics.getDesktopDisplayMode().height, true);
	    	}
	    	
	    	sprite = new Sprite(new Texture(Gdx.files.internal("images/background.jpg")));
	    }

	    @Override
	    public void dispose() {
	    	batch.dispose();
	    }

	    @Override
	    public void render() {        
	        Gdx.gl.glClearColor(1, 0, 0, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        
	        batch.begin();
	        sprite.draw(batch);
	        batch.end();
	    }

	    @Override
	    public void resize(int width, int height) {
	    	
	    }

	    @Override
	    public void pause() {
	    	
	    }

	    @Override
	    public void resume() {
	    	
	    }
	    
	}
//End of class