package com.ellipsoft.DungeonDelver.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Entity extends Actor {
	float[] size = { 100.0f, 100.0f };
	float[] position = { 0.0f, 0.0f };
	public Texture texture;

	public void setPos(float x, float y) {
		position[0] = x;
		position[1] = y;
	}

	public void setPos(float[] pos) {
		position = pos;
	}

	public void reset() {
		position[0] = 0.0f;
		position[1] = 0.0f;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, position[0], position[1], size[0], size[1]);
	}
}
