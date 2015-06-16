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
package uk.co.thisishillman.terrain;

public abstract class Tile {

	// X co-ord in game world
	private int x;
	
	// Y co-ord in game world
	private int y;
	
	// TerrainType for tile
	private TerrainType terrain;
	
	/**
	 * Initialise a new tile outside the game world
	 */
	public Tile() {
		x = -1; y = -1;
		terrain = TerrainType.SALT_WATER;
	}
	
	/**
	 * Returns Tile instance's X co-ordinate in game world
	 * 
	 * @return x co-ord
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns Tile instance's Y co-ordinate in game world
	 * 
	 * @return y co-ord
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Returns the Tile instance's current terrain type
	 * 
	 * @return terrain type
	 */
	public TerrainType getTerrain() {
		return terrain;
	}
	
	/**
	 * Returns true if the Tile instance is of an aquatic terrain type.
	 * 
	 * @return true if aquatic
	 * @see uk.co.thisishillman.terrain.TerrainType
	 */
	public boolean isAquatic() {
		if(terrain == null) return false;
		
		switch(terrain) {
			case SALT_WATER  :
			case FRESH_WATER :
			case SWAMP_WATER :
				return true;
				
			default:
				return false;
		}
	}
	
	// ========== ABSTRACT METHODS BELOW THIS POINT ========== //
	
	/**
	 * Returns true if the tile instance should be enterable
	 * 
	 * @return true if traversable
	 */
	public abstract boolean isTraversable();
	
	/**
	 * Returns true if the tile instance can be built upon
	 * 
	 * @return true if building possible
	 */
	public abstract boolean isBuildable();
	
	/**
	 * Returns true if the tile instance contains grazeable terrain
	 * 
	 * @return true if grazable
	 */
	public abstract boolean isGrazeable();
	
}
//End of class