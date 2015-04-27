package com.mcai8sh4.nemesis.entity.projectile;

import com.mcai8sh4.nemesis.graphics.Screen;
import com.mcai8sh4.nemesis.graphics.Sprite;

public class PlayerProjectile extends Projectile {

    public static final int FIRE_RATE = 10;

    public PlayerProjectile(int x, int y, double dir) {
        super(x, y, dir);
        range = 200; //random.nextInt(100) + 50;
        speed = 4;
        damage = 20;
        sprite = Sprite.projectile_player;
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);

    }

    public void update() {
        if(level.tileCollision(x, y, nx, ny, 7)) remove();
            move();
    }

    protected void move() {
            x += nx;
            y += ny;
            if (distance() > range) remove();

    }

    private double distance() {
        double dist = 0;
        dist = Math.sqrt(Math.abs(((xOrigin - x) * (xOrigin - x)) + (yOrigin - y) * (yOrigin - y)));
        return dist;
    }


    public void render(Screen screen) {
        screen.renderProjectile((int) x - 8, (int) y - 4, this);
    }

}
