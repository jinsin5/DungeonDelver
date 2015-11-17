package com.ellipsoft.DungeonDelver.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ellipsoft.DungeonDelver.Engine.Maze;
import com.ellipsoft.DungeonDelver.Entity.Entity;

public class Cell extends Actor {
	Entity actor = null;
	private float[] size = { 100.0f, 100.0f };
	private float[] position = { 0.0f, 0.0f };
	private int wall = 0;
	Texture texture = new Texture("stone.png");

	public Cell(float x, float y) {
		position[0] = x;
		position[1] = y;
	}

	public float[] getPos() { return position; }

	public void setActor(Entity a){
		actor = a;
	}

	public void removeActor(){
		actor = null;
	}

	public Entity getActor(){
		return actor;
	}

	public void setWall(int wall_){
		wall |= wall_;
	}

	public void resetWall(){ wall = 0; }

	public int getWall(){
		return wall;
	}

	private void draw_bottom(Batch batch){
		batch.draw(new Texture("horizontal.png"), position[0], position[1]);
	}

	private void draw_top(Batch batch){
		batch.draw(new Texture("horizontal.png"), position[0], position[1]+100.0f);
	}

	private void draw_left(Batch batch){
		batch.draw(new Texture("vertical.png"), position[0], position[1]);
	}

	private void draw_right(Batch batch){
		batch.draw(new Texture("vertical.png"), position[0]+100.0f, position[1]);
	}

	public void draw_bg(Batch batch, float parentAlpha){
		batch.draw(texture, position[0], position[1], size[0], size[1]);
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (((wall / Maze.W) % 2) == 0){
			draw_left(batch);
		}
		if (((wall / Maze.E) % 2) == 0){
			draw_right(batch);
		}
		if (((wall / Maze.N) % 2) == 0){
			draw_top(batch);
		}
		if (((wall / Maze.S) % 2) == 0){
			draw_bottom(batch);
		}
	}

}
