package com.ellipsoft.DungeonDelver.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.SnapshotArray;

public class CombatLog extends VerticalGroup {
	Skin skin;

	public CombatLog() {
		skin = new Skin(Gdx.files.internal("uiskin/uiskin.json"));

		setPosition(1100.0f, 100.0f);
		setSize(700.0f, 600.0f);
		setDebug(true);
		reverse();
	}

	public void publish(String message){
		Label messageLabel = new Label(message, skin);
		messageLabel.setFontScale(2.0f);
		addActor(messageLabel);
		SnapshotArray<Actor> entries = getChildren();
		if (entries.size >= 10){
			removeActor(entries.removeIndex(0));
		}
	}
}
