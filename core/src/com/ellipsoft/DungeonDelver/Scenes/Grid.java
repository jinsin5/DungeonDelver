package com.ellipsoft.DungeonDelver.Scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ellipsoft.DungeonDelver.SceneManager;
import com.ellipsoft.DungeonDelver.Entity.Player;

public class Grid extends Actor {
	int width = SceneManager.GAME_WIDTH / 100;
	int height = SceneManager.GAME_HEIGHT / 100;
	int xOffSet = (SceneManager.GAME_WIDTH % 100) / 2;
	int yOffSet = (SceneManager.GAME_HEIGHT % 100) / 2;
	int area = width*height;
	Cell[] cells = new Cell[area];
	Player player = new Player();
	Texture background = new Texture("background.jpg");

	public Grid() {
		for (int i = 0; i < area; i++) {
			cells[i] = new Cell((i % width * 100) + xOffSet,
								(i / width * 100) + yOffSet);
		}
		reset();
	}

	public boolean checkAdjacency(int index) {
		int[] indicies = getAdjacent(index);
		return false;
	}

	public int[] getAdjacent(int index) {
		int[] indices  = {index + 1, index - 1, index + width, index - width};
		return indices;
	}

	public synchronized void reset() {
		player.reset();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

	public boolean touchDown(float screenX, float screenY, int pointer, int button) {
		int x = (int) Math.floor(screenX);
		int y = (int) Math.floor(screenY);
		int index = (x / 100) + width * (y / 100);

		/* Verification */
		if (index >= area) {
			return false;
		}
		if (!checkAdjacency(index)) {
			return false;
		}
		float[] newPlayerPos = cells[index].getPos();
		player.setPos(newPlayerPos);
		return true;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(background, 0.0f, 0.0f, 1920.0f, 1080.0f);

		for (Cell c: cells) {
			c.draw(batch, parentAlpha);
		}

		player.draw(batch, parentAlpha);
	}

}
