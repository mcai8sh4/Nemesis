package com.mcai8sh4.nemesis.level.tile.spawnLevel;

import com.mcai8sh4.nemesis.graphics.Screen;
import com.mcai8sh4.nemesis.graphics.Sprite;
import com.mcai8sh4.nemesis.level.tile.Tile;

public class SpawnRockTile extends Tile {
    public SpawnRockTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }

    public boolean solid() {
        return true;
    }
}
