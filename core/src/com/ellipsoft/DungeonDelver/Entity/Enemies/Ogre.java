package com.ellipsoft.DungeonDelver.Entity.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.ellipsoft.DungeonDelver.Entity.Entity;



public class Ogre extends Entity{

	public Ogre(float[] pos) {
		//texture = new Texture("Ogre.png");
		texture = new Texture("hero.png");
		type = "Enemy";
		position = pos;
		for (String s : base_stats) {
			if (s.equals("Str")){
				stats.put(s, randInt(12, 15));
			}
			else if (s.equals("Int")){
				stats.put(s, randInt(5, 8));
			}
			else{
				stats.put(s, randInt(8, 12));
			}
		}
	}
}
