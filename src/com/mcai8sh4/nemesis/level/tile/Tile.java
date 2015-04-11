package com.mcai8sh4.nemesis.level.tile;

import com.mcai8sh4.nemesis.graphics.Screen;
import com.mcai8sh4.nemesis.graphics.Sprite;
import com.mcai8sh4.nemesis.level.tile.spawnLevel.*;

public class Tile {
    public int x, y;
    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile flower = new FlowerTile(Sprite.flower);
    public static Tile rock = new RockTile(Sprite.rock);
    public static Tile wall = new WallTile(Sprite.wall);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public static Tile spawnGrass = new SpawnGrassTile(Sprite.spawn_grass);
    public static Tile spawnFlower = new SpawnFlowerTile(Sprite.spawn_flower);
    public static Tile spawnRock = new SpawnRockTile(Sprite.spawn_rock);
    public static Tile spawnWall = new SpawnWallTile(Sprite.spawn_wall);
    public static Tile spawnFloor = new SpawnFloorTile(Sprite.spawn_floor);
    public static Tile spawnWater = new SpawnWaterTile(Sprite.spawn_water);


    public static final int col_spawn_grass = 0xff00ff00;
    public static final int col_spawn_flower = 0xffffff00;
    public static final int col_spawn_rock = 0xff7f7f00;
    public static final int col_spawn_wall = 0xff808080;
    public static final int col_spawn_floor = 0xff666600;
    public static final int col_spawn_water = 0xff0000ff;

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {
    }

    public boolean solid() {
        return false;
    }
}
