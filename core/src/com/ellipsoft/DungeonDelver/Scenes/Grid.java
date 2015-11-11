package com.ellipsoft.DungeonDelver.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.ellipsoft.DungeonDelver.Engine.Game;
import com.ellipsoft.DungeonDelver.Entity.Entity;
import com.ellipsoft.DungeonDelver.Entity.Enemies.Ogre;
import com.ellipsoft.DungeonDelver.Entity.Player;
import com.ellipsoft.DungeonDelver.Entity.Stairs;
import com.ellipsoft.DungeonDelver.UI.Statistics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid extends BaseScene {
	private List<EventListener> eventListeners = new ArrayList<EventListener>();

	public interface EventListener {
		enum EventType {
			RETURN_HOME, MAIN_MENU
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

	//Sizing initialization
	protected int width = 10;
	protected int height = 10;
	public static int xOffSet = 5;
	public static int yOffSet = 5;
	protected int area = width*height;

	//Gamespace Initialization
	protected Cell[] cells = new Cell[area];
	List<Entity> enemies = new ArrayList<Entity>();
	List<Entity> objects = new ArrayList<Entity>();
	//List<Table> ui_elements = new ArrayList<Table>();

	// Player Initialization
	Player player = new Player();
	int player_index = 0;

	Statistics stats = new Statistics(player);

	Texture background = new Texture("background.jpg");

	public Grid() {
		for (int i = 0; i < area; i++) {
			cells[i] = new Cell((i % width * 100) + xOffSet,
								(i / width * 100) + yOffSet);
		}
		for (int i = 0; i < 5; i++) {
			Entity Ogre = new Ogre(cells, area);
			enemies.add(Ogre);
		}
		Entity Stair = new Stairs(cells, area);
		objects.add(Stair);
	}

	public synchronized void reset() {
		enemies.clear();
		objects.clear();

		for (int i = 0; i < area; i++) {
			Entity actor = cells[i].getActor();
			if (actor != null && actor.type != "player"){
				cells[i].removeActor();
			}
		}

		for (int i = 0; i < 5; i++) {
			Entity Ogre = new Ogre(cells, area);
			enemies.add(Ogre);
		}
		Entity Stair = new Stairs(cells, area);
		objects.add(Stair);
	}

		public boolean checkAdjacency(int index) {
		List<Integer> indices = getAdjacent();
		for (int i : indices) {
			if (index == i) {
				return true;
			}
		}
		return false;
	}

	public List<Integer> getAdjacent() {
		player_index = player.getIndex();
		List<Integer> adj = new ArrayList<Integer>();
		if ((player_index + 1) / width == player_index / width){
			adj.add(player_index + 1);
		}
		if ((player_index - 1) / width == player_index / width){
			adj.add(player_index - 1);
		}
		if ((player_index + width <= area)){
			adj.add(player_index + width);
		}
		if ((player_index - width) > 0){
			adj.add(player_index - width);
		}
		return adj;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

	public boolean spaceOccupied(int index) {
		// False implies empty space and the actor will traverse to it
		Entity actor = cells[index].getActor();
		if (actor == null){
			return false;
		}
		Gdx.app.log("GRID", "enemy encountered! " + actor.type);

		if (actor.type.equals("stairs")){
			reset();
			return false;
		}

		if (actor.type.equals("townportal")){
			triggerEvent(EventListener.EventType.RETURN_HOME);
			return false;
		}

		return true;
	}

	public void moveActor(Entity a, int index){
		int current_index = a.getIndex();
		float[] newPlayerPos = cells[index].getPos();

		cells[current_index].removeActor();
		cells[index].setActor(a);
		a.setPos(newPlayerPos);
		a.setIndex(index);
	}

	public void removeActor(Entity a){
		cells[a.getIndex()].removeActor();
		for (Iterator<Entity> iter = enemies.iterator(); iter.hasNext(); ) {
			Entity e = iter.next();
			if (e.getIndex() == a.getIndex()) {
				iter.remove();
			}
		}
	}

	public boolean touchDown(float screenX, float screenY, int pointer, int button) {
		int x = (int) Math.floor(screenX);
		int y = (int) Math.floor(screenY);
		int index = (x / 100) + width * (y / 100);

		/* dummy signal to end game */
		if (player.hp <= 0) {
			return false;
		}

		/* Verification */
		if (index >= area) {
			return false;
		}
		if (!checkAdjacency(index)) {
			return false;
		}

		/* Interaction with Game entities */
		if (spaceOccupied(index)) {
			Entity other = cells[index].getActor();
			if (player.interactWith(other)) {
				// following should be done in interactWith()
				if (other.hp <= 0){
					removeActor(other); // Victory!
					Game.levelUp(player);
					stats.update(player);
				}
				if (player.hp <= 0){
					triggerEvent(EventListener.EventType.MAIN_MENU);
				}
			}
			return false;
		}

		/* Space is valid & empty */
		moveActor(player, index);
		return true;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(background, 0.0f, 0.0f, 1920.0f, 1080.0f);

		for (Cell c: cells) {
			c.draw(batch, parentAlpha);
		}

		for (Entity e: enemies) {
			e.draw(batch, parentAlpha);
		}

		for (Entity o: objects){
			o.draw(batch, parentAlpha);
		}

		stats.draw(batch, parentAlpha);
		/*
		for (Actor ui: ui_elements){
			ui.draw(batch, parentAlpha);
		}
		*/

		player.draw(batch, parentAlpha);
	}

}
