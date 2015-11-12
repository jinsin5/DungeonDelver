package com.ellipsoft.DungeonDelver.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ellipsoft.DungeonDelver.Entity.Entity;

public class Statistics extends Table{
	Skin skin;

	public Statistics(Entity actor) {
		skin = new Skin(Gdx.files.internal("uiskin/uiskin.json"));

		setPosition(1100.0f, 600.0f);
		setSize(700.0f, 600.0f);
		setDebug(true);
		for (String attr : Entity.attributes){
			Label attrLabel = new Label(attr, skin);
			attrLabel.setFontScale(2.5f);
			add(attrLabel).width(300);
			Label statLabel = new Label(Integer.toString(actor.getStat(attr)), skin);
			statLabel.setFontScale(2.5f);
			add(statLabel).width(200);
			row();
		}


	}

	public void update(Entity actor) {
		reset();
		for (String attr : Entity.attributes){
			Label attrLabel = new Label(attr, skin);
			add(attrLabel);
			Label statLabel = new Label(Integer.toString(actor.getStat(attr)), skin);
			add(statLabel);
			row();
		}
	}
}
