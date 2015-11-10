package com.ellipsoft.DungeonDelver.Scenes;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class BaseScene extends Actor {
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	public boolean touchUp(float x, float y, int pointer, int button) {
		return false;
	}

	public void reset(){
		return;
	}
}
