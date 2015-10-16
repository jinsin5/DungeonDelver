package com.ellipsoft.DungeonDelver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.ellipsoft.DungeonDelver.Scenes.Grid;


public class SceneManager extends Stage implements InputProcessor {
	public static final int GAME_WIDTH = 1920;
	public static final int GAME_HEIGHT = 1080;
	private OrthographicCamera camera;
	private Group group;
	private Grid currentScene;
	Vector3 downPoint = new Vector3();
	long downTime = 0;


	@Override
	public void draw() {
		super.draw();
	}

	public SceneManager() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);
		camera.update();
		getViewport().setCamera(camera);
		group = new Group();
		loadGameScene();

	}

	public synchronized void loadGameScene() {
		clear();
		group.clear();

		Grid scene = new Grid();
		currentScene = scene;

		group.addActor(currentScene);
		addActor(group);
	}

	public synchronized void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);
		camera.update();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 clickPoint = camera.unproject(new Vector3(screenX, screenY, 0));

		downTime = Gdx.input.getCurrentEventTime();
		downPoint = clickPoint;

		return currentScene.touchDown(clickPoint.x, clickPoint.y, pointer, button);
	}

}
