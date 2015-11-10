package com.ellipsoft.DungeonDelver.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.ellipsoft.DungeonDelver.Engine.Game;
import com.ellipsoft.DungeonDelver.Scenes.Grid;

public class Stairs extends Entity {

	public Stairs() {
		int rand = Game.randInt(1, Grid.area - 1);
		Grid.cells[rand].setActor(this);
		setIndex(rand);
		position = Grid.cells[rand].getPos();

		type = "stairs";
		texture = new Texture("stairs.png");
	}

}
