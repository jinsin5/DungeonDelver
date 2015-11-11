package com.ellipsoft.DungeonDelver.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ellipsoft.DungeonDelver.Entity.Entity;

public class Statistics extends Table{
	Skin skin;

	public Statistics(Entity actor) {
		//super(new Skin(new TextureAtlas(Gdx.files.internal("uiskin.pack"))));
		skin = new Skin(Gdx.files.internal("uiskin/uiskin.json"));

		setPosition(1010.0f, 5.0f);
		setSize(900.0f, 1000.0f);

		for (String attr : Entity.attributes){
			Label attrLabel = new Label(attr, skin);
			add(attrLabel);
			Label statLabel = new Label(Integer.toString(actor.getStat(attr)), skin);
			add(statLabel);
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
