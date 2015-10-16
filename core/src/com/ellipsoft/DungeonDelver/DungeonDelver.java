package com.ellipsoft.DungeonDelver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;


public class DungeonDelver extends ApplicationAdapter {
	SceneManager sceneManager;

	@Override
	public void create () {
		sceneManager = new SceneManager();
		Gdx.input.setInputProcessor(sceneManager);
		Gdx.graphics.setDisplayMode(1920, 1080, false);
	}

	@Override
	public void pause () {
        /* statically held textures screw up over pause() / resume()
           force reload by removing the static instance */
		Gdx.input.setInputProcessor(null);
		sceneManager = null;
	}

	@Override
	public void resume () {
		SceneManager newSceneManager = new SceneManager();
		Gdx.input.setInputProcessor(newSceneManager);
		Gdx.graphics.setDisplayMode(1920, 1080, false);

		sceneManager = newSceneManager;
	}

	@Override
	public void render () {
		SceneManager thisManager = sceneManager;
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		thisManager.draw();
	}

	@Override
	public void resize(int width, int height) {
		SceneManager thisManager = sceneManager;

		if (thisManager != null) {
			thisManager.resize(width, height);
		}
	}
}
