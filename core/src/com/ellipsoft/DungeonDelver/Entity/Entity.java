package com.ellipsoft.DungeonDelver.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ellipsoft.DungeonDelver.Scenes.Grid;

import java.util.HashMap;
import java.util.Map;

public class Entity extends Actor {
	public float[] size = { 100.0f, 100.0f };
	public float[] position = { (float) Grid.xOffSet, (float) Grid.yOffSet };
	public static final String[] attributes = {"Str", "Int", "Con", "Dex", "Wis", "Luck"};
	public static final String[] parameters = {"Def", "Atk"};
	public Map<String, Integer> stats = new HashMap<String, Integer>();
	int current_index = 0;
	public String type = "";
	public Texture texture;
	public int hp = 0;
	public int max_hp = 0;

	public void setPos(float x, float y) {
		position[0] = x;
		position[1] = y;
	}

	public void setPos(float[] pos) { position = pos; }

	public void setIndex(int index) {
		current_index = index;
	}


	public void _setSize(int x, int y){
		size[0] = (float)x;
		size[1] = (float)y;
	}

	public void _setSize(float x, float y){
		size[0] = x;
		size[1] = y;
	}

	public int getStat(String stat){
		return stats.get(stat);
	}

	public int getIndex(){
		return current_index;
	}

	public boolean interactWith(Entity e){
		return true;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, position[0], position[1], size[0], size[1]);
	}
}
