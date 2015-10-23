package com.ellipsoft.DungeonDelver.Entity.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.ellipsoft.DungeonDelver.Entity.Entity;



public class Ogre extends Entity{

	public Ogre(float[] pos) {
		//texture = new Texture("Ogre.png");
		texture = new Texture("hero.png");
		type = "Enemy";
		position = pos;
		max_hp = hp = 25;
		for (String s : attributes) {
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
		for (String s : parameters) {
			if (s.equals("Def")) {stats.put(s, 1);}
			if (s.equals("Atk")) {stats.put(s, 75);}
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		BitmapFont font = new BitmapFont();
		font.draw(batch, "HP: " + hp, position[0], position[1]);
		super.draw(batch, parentAlpha);
	}
}
