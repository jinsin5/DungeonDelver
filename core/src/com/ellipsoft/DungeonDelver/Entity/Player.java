package com.ellipsoft.DungeonDelver.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Entity {

	public Player() {
		type = "Player";
		texture = new Texture("hero.png");
		for (String s : base_stats) {
			stats.put(s, randInt(12, 15));
		}
	}

	@Override
	public boolean interactWith(Entity e) {
		super.interactWith(e);
		if (e.type == "Enemy") {
			Gdx.app.log("PLAYER", "player strength " + stats.get("Str"));
			Gdx.app.log("PLAYER", "enemy strength " + e.stats.get("Str"));
			if (stats.get("Str") <= e.stats.get("Str")){
				return false;
			}
		}
		return true;
	}

}
