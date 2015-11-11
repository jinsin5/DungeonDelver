package com.ellipsoft.DungeonDelver.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.ellipsoft.DungeonDelver.Engine.Game;
import com.ellipsoft.DungeonDelver.Scenes.Cell;
import com.ellipsoft.DungeonDelver.Scenes.Grid;

public class Stairs extends Entity {

	public Stairs(Cell[] cells, int area) {
		int rand = Game.randInt(1, area - 1);
		cells[rand].setActor(this);
		setIndex(rand);
		position = cells[rand].getPos();

		type = "stairs";
		texture = new Texture("stairs.png");
	}

}
