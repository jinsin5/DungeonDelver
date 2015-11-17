package com.ellipsoft.DungeonDelver.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.ellipsoft.DungeonDelver.Entity.Entity;
import com.ellipsoft.DungeonDelver.SceneManager;

public class Button extends Entity{
	private Action action = null;

	public interface Action {
		void action();
	}

	public Button(Texture new_texture) {
		setScale(3.0f);
		texture = new_texture;
		setPos((SceneManager.GAME_WIDTH / 2) - ((size[0] * getScaleX()) / 2),
				(SceneManager.GAME_HEIGHT / 2) - ((size[1] * getScaleY()) / 2));
	}

	public boolean isPressed(float x, float y){
		return ((x >= position[0] && x <= position[0] + size[0]) &&
				(y >= position[1] && y <= position[1] + size[1]));
	}

	public void setAction(Action a) {
		action = a;
	}

	public void up() {
		if (action != null){
			action.action();
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		BitmapFont font = new BitmapFont();
		font.draw(batch, "Start Game!", position[0], position[1]);
		super.draw(batch, parentAlpha);
	}
}
