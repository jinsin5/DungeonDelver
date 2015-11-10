package com.ellipsoft.DungeonDelver.Scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.ellipsoft.DungeonDelver.UI.Button;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends BaseScene {
	public interface EventListener {
		enum EventType {
			START_GAME
		}

		void event(EventType eventType);
	}

	private void triggerEvent(EventListener.EventType eventType) {
		for (EventListener listener : eventListeners) {
			listener.event(eventType);
		}
	}

	public void setEventListener(EventListener listener) {
		eventListeners.add(listener);
	}

	private List<EventListener> eventListeners = new ArrayList<EventListener>();

	private Button[] buttons = new Button[1];
	Texture background = new Texture("background.jpg");

	public MainMenu(){
		Texture button_texture = new Texture("hero.png");
		Button button = new Button(button_texture);
		button.setAction(new Button.Action(){
			@Override
			public void action() {
				triggerEvent(EventListener.EventType.START_GAME);
			}
		});
		buttons[0] = button;
	}


	@Override
	public void draw(Batch batch, float parentAlpha) {
		for (Button b : buttons) {
			b.draw(batch, parentAlpha);
		}
		batch.draw(background, 0.0f, 0.0f, 1920.0f, 1080.0f);

	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		for (Button b : buttons) {
			if (b.isPressed(x, y)) {
				b.up();
			}
		}
		return true;
	}
}
