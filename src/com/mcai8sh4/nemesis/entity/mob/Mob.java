package com.mcai8sh4.nemesis.entity.mob;

import com.apple.laf.ScreenMenuBar;
import com.mcai8sh4.nemesis.entity.Entity;
import com.mcai8sh4.nemesis.graphics.Screen;
import com.mcai8sh4.nemesis.graphics.Sprite;
import com.mcai8sh4.nemesis.level.tile.Tile;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = 2;
    protected boolean moving = false;

    public void move(int xa, int ya) {

        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            return;
        }

        if (xa > 0) dir = 1;
        if (xa < 0) dir = 3;
        if (ya > 0) dir = 2;
        if (ya < 0) dir = 0;

        if (!collision(xa, ya)) {

            x += xa;
            y += ya;
        }
    }

    public void update() {

    }

    protected void shoot(int x, int y, double dir){
//        System.out.println("Angle : "+ Math.toDegrees(dir));
    }

    private boolean collision(int xa, int ya) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
            int yt = ((y + ya) + c / 2 * 12 ) / 16;
            if (level.getTile(xt, yt).solid()) solid = true;

           //debug info
            if (level.getTile(xt, yt) == Tile.voidTile) Screen.msg = "oops! Player out of bounds - Freedom!!";
            if (level.getTile(xt, yt) == Tile.spawnFlower) Screen.msg = "Flower";
            if (level.getTile(xt, yt) == Tile.spawnFloor) Screen.msg = "Floor";
            if (level.getTile(xt, yt) == Tile.spawnGrass) Screen.msg = "Grass";
            if (level.getTile(xt, yt) == Tile.spawnWater) Screen.msg = "Water";
        }
        if(solid) Screen.msg = "Collision";
        return solid;
    }

    public void render() {
    }


}
