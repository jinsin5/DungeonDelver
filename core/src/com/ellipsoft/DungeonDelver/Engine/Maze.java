package com.ellipsoft.DungeonDelver.Engine;

import com.badlogic.gdx.Gdx;
import com.ellipsoft.DungeonDelver.Scenes.Grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Maze {
	public static final int N = 1;
	public static final int S = 2;
	public static final int E = 4;
	public static final int W = 8;
	public static final int[] DIR = { N, E, S, W };

	public static int DX(int dir) {
		switch (dir) {
			case N:
			case S:
				return 0;
			case E:
				return 1;
			case W:
				return -1;
		}
		return -1;
	}

	public static int DY(int dir) {
		switch (dir) {
			case E:
			case W:
				return 0;
			case S:
				return -1;
			case N:
				return 1;
		}
		return -1;
	}

	public static int OPPOSITE(int dir) {
		switch (dir) {
			case E:
				return W;
			case W:
				return E;
			case S:
				return N;
			case N:
				return S;
		}
		return -1;
	}

	public static void generateMaze(Grid grid) {
		List<Coord> edges = new ArrayList<Coord>();
		edges.add(new Coord(Game.randInt(grid._getWidth()-1), Game.randInt(grid._getHeight()-1)));

		while (!edges.isEmpty()){
			int index = next_index(edges.size());
			Coord coord = edges.get(index);

			List<Integer> shuffle_dir = new ArrayList<Integer>();
			for (int d: DIR){
				shuffle_dir.add(d);
			}
			Collections.shuffle(shuffle_dir);

			for (int d: shuffle_dir){
				int nx = coord.x + DX(d);
				int ny = coord.y + DY(d);
				if (nx >= 0 && ny >= 0 && nx < grid._getWidth() && ny < grid._getHeight() &&
						grid.getCellWall(nx, ny) == 0 ){
					grid.setCellWall(coord.x, coord.y, d);
					grid.setCellWall(nx, ny, OPPOSITE(d));
					edges.add(new Coord(nx, ny));
					index = -1;
					break;
				}
			}
			if (index != -1) {
				edges.remove(index);
			}
		}
	}

	private static int next_index(int length){
		int rand = Game.randInt(100);
		if (rand <= 75) {
			return length - 1;
		}
		else {
			return Game.randInt(0, length-1);
		}
	}
}

class Coord {
	public final int x, y;

	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
}