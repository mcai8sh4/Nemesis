package com.mcai8sh4.nemesis.level;

import com.mcai8sh4.nemesis.graphics.Screen;
import com.mcai8sh4.nemesis.level.tile.Tile;

public class Level {

    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;
    public static Level spawn = new SpawnLevel("/Levels/spawn1.png");

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
        generateLevel();
    }

    protected void generateLevel() {

    }

    protected void loadLevel(String path) {

    }


    public void update() {

    }

    private void time() {

    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x0 = (xScroll >> 4); // div by 16
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0 - 16; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        if (tiles[x + y * width] == Tile.col_spawn_floor) return Tile.spawnFloor;
        if (tiles[x + y * width] == Tile.col_spawn_grass) return Tile.spawnGrass;
        if (tiles[x + y * width] == Tile.col_spawn_flower) return Tile.spawnFlower;
        if (tiles[x + y * width] == Tile.col_spawn_rock) return Tile.spawnRock;
        if (tiles[x + y * width] == Tile.col_spawn_wall) return Tile.spawnWall;
        if (tiles[x + y * width] == Tile.col_spawn_water) return Tile.spawnWater;
        return Tile.voidTile;
    }

}
