package com.ellipsoft.DungeonDelver.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.ellipsoft.DungeonDelver.Scenes.Grid;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Entity extends Actor {
	float[] size = { 100.0f, 100.0f };
	public float[] position = { (float) Grid.xOffSet, (float) Grid.yOffSet };
	public String[] base_stats = {"Str", "Int", "Con", "Dex", "Wis", "Luck"};
	public Map<String, Integer> stats = new HashMap<String, Integer>();
	int current_index = 0;
	public String type = "";
	public Texture texture;

	public void setPos(float x, float y) {
		position[0] = x;
		position[1] = y;
	}

	public void setPos(float[] pos) {
		position = pos;
	}

	public float[] getPos() {
		return position;
	}

	public void setIndex(int index) {
		current_index = index;
	}

	public int getIndex(){
		return current_index;
	}

	public boolean interactWith(Entity e){
		return true;
	}

	public void reset() {
		position[0] = (float) Grid.xOffSet;
		position[1] = (float) Grid.yOffSet;
	}

	public static int randInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, position[0], position[1], size[0], size[1]);
	}
}
