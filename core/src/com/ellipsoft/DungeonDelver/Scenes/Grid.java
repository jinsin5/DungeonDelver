package com.ellipsoft.DungeonDelver.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ellipsoft.DungeonDelver.Engine.Game;
import com.ellipsoft.DungeonDelver.Entity.Entity;
import com.ellipsoft.DungeonDelver.Entity.Enemies.Ogre;
import com.ellipsoft.DungeonDelver.SceneManager;
import com.ellipsoft.DungeonDelver.Entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid extends Actor {
	int width = SceneManager.GAME_WIDTH / 100;
	int height = SceneManager.GAME_HEIGHT / 100;
	public static int xOffSet = (SceneManager.GAME_WIDTH % 100) / 2;
	public static int yOffSet = (SceneManager.GAME_HEIGHT % 100) / 2;
	int area = width*height;
	Cell[] cells = new Cell[area];
	Player player = new Player();
	List<Entity> enemies = new ArrayList<Entity>();
	int player_index = 0;
	Texture background = new Texture("background.jpg");

	public Grid() {
		for (int i = 0; i < area; i++) {
			cells[i] = new Cell((i % width * 100) + xOffSet,
								(i / width * 100) + yOffSet);
		}
		for (int i = 0; i < 5; i++) {
			int rand = Entity.randInt(0, area);
			Entity enemy = new Ogre(cells[rand].getPos());
			enemies.add(enemy);
			enemy.setIndex(rand); //should all be done in init
			cells[rand].setActor(enemy);
		}
		reset();
	}

	public boolean checkAdjacency(int index) {
		int[] indices = getAdjacent();
		for (int i : indices) {
			if (index == i) {
				return true;
			}
		}
		return false;
	}

	public int[] getAdjacent() {
		player_index = player.getIndex();
		return new int[] {player_index + 1, player_index - 1, player_index + width, player_index - width};
	}

	public synchronized void reset() {
		player.reset();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

	public boolean spaceOccupied(int index) {
		if (cells[index].getActor() != null) {
			return true;
		}
		return false;
	}

	public void moveActor(Entity a, int index){
		int current_index = a.getIndex();
		float[] newPlayerPos = cells[index].getPos();

		cells[current_index].removeActor();
		cells[index].setActor(a);
		a.setPos(newPlayerPos);
		a.setIndex(index);
	}

	public void removeActor(Entity a){
		cells[a.getIndex()].removeActor();
		for (Iterator<Entity> iter = enemies.iterator(); iter.hasNext(); ) {
			Entity e = iter.next();
			if (e.getIndex() == a.getIndex()) {
				iter.remove();
			}
		}
	}

	public boolean touchDown(float screenX, float screenY, int pointer, int button) {
		int x = (int) Math.floor(screenX);
		int y = (int) Math.floor(screenY);
		int index = (x / 100) + width * (y / 100);

		/* dummy signal to end game */
		if (player.hp <= 0) {
			return false;
		}

		/* Verification */
		if (index >= area) {
			return false;
		}
		if (!checkAdjacency(index)) {
			return false;
		}

		/* Interaction with Game entities */
		if (spaceOccupied(index)) {
			Entity other  = cells[index].getActor();
			Gdx.app.log("GRID", "enemy encountered! " + other.type);
			if (player.interactWith(other)) {
				// following should be done in interactWith()
				if (other.hp <= 0){
					removeActor(other); // Victory!
					Game.levelUp(player);
				}
				if (player.hp <= 0){
					removeActor(player); // game over
				}
			}
			return false;
		}

		/* Space is valid & empty */
		moveActor(player, index);
		return true;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(background, 0.0f, 0.0f, 1920.0f, 1080.0f);

		for (Cell c: cells) {
			c.draw(batch, parentAlpha);
		}

		for (Entity e: enemies) {
			e.draw(batch, parentAlpha);
		}

		player.draw(batch, parentAlpha);
	}

}
