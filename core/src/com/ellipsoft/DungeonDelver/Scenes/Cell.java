package com.ellipsoft.DungeonDelver.Scenes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Cell extends Actor {

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

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, position[0], position[1], size[0], size[1]);
	}

}