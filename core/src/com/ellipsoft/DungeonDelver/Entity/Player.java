package com.ellipsoft.DungeonDelver.Entity;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.ellipsoft.DungeonDelver.Engine.Game;

public class Player extends Entity {

	public Player() {
		type = "player";
		texture = new Texture("hero.png");
		for (String s : attributes) {
			stats.put(s, Game.randInt(12, 15));
		}
		for (String s : parameters) {
			if (s.equals("Def")) {stats.put(s, 5);}
			if (s.equals("Atk")) {stats.put(s, 90);}
		}
		max_hp = hp = 10;
	}


	@Override
	public boolean interactWith(Entity e) {
		if (e.type == "enemy") {
			/* attacker goes first */
			Game.fight(e, this);
			if (e.hp <= 0) {
				return true;
			}
			/* defender retaliates */
			Game.fight(this, e);
			if (this.hp <=0) {
				return true;
			}
			return false;
		}
		if (e.type == "stairs") {
			return false;
		}
		return true;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		BitmapFont font = new BitmapFont();
		font.draw(batch, "HP: " + hp, position[0], position[1]);
		super.draw(batch, parentAlpha);
	}
}
