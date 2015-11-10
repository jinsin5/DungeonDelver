package com.ellipsoft.DungeonDelver.Entity.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.ellipsoft.DungeonDelver.Engine.Game;
import com.ellipsoft.DungeonDelver.Scenes.Grid;


public class Ogre extends Enemy{

	public Ogre() {
		super();
		int rand = Game.randInt(1, Grid.area - 1);
		Grid.cells[rand].setActor(this);
		setIndex(rand);
		position = Grid.cells[rand].getPos();

		texture = new Texture("ogre.png");
		type = "Enemy";
		sub_type = "Ogre";
		max_hp = hp = 25;
		for (String s : attributes) {
			if (s.equals("Str")){
				stats.put(s, Game.randInt(12, 15));
			}
			else if (s.equals("Int")){
				stats.put(s, Game.randInt(5, 8));
			}
			else{
				stats.put(s, Game.randInt(8, 12));
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
