package com.ellipsoft.DungeonDelver.UI;

import com.badlogic.gdx.graphics.Texture;
import com.ellipsoft.DungeonDelver.Entity.Entity;
import com.ellipsoft.DungeonDelver.SceneManager;

public class Button extends Entity{
	private Action action = null;

	public interface Action {
		void action();
	}

	public Button(Texture new_texture) {
		texture = new_texture;
		_setSize(100.0f, 100.0f);
		setPos(SceneManager.GAME_WIDTH / 2, SceneManager.GAME_HEIGHT / 2);
	}

	public boolean isPressed(float x, float y){
		if ((x >= position[0] && x <= position[0] + size[0]) &&
				(y >= position[1] && y <= position[1] + size[1])){
			return true;
		}
		return false;
	}

	public void setAction(Action a) {
		action = a;
	}

	public void up() {
		if (action != null){
			action.action();
		}
	}
}
