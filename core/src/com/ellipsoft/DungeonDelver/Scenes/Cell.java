package com.ellipsoft.DungeonDelver.Scenes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ellipsoft.DungeonDelver.Entity.Entity;

public class Cell extends Actor {
	Entity actor = null;
	private float[] size = { 100.0f, 100.0f };
	private float[] position = { 0.0f, 0.0f };
	Texture texture = new Texture("stone.png");

	public Cell(float x, float y) {
		position[0] = x;
		position[1] = y;
	}

	public float[] getPos() {
		return position;
	}

	public void setActor(Entity a){
		actor = a;
	}

	public void removeActor(){
		actor = null;
	}

	public Entity getActor(){
		return actor;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, position[0], position[1], size[0], size[1]);
	}

}
