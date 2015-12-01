package com.mcai8sh4.nemesis.entity.mob;

import com.mcai8sh4.nemesis.Game;
import com.mcai8sh4.nemesis.entity.Entity;
import com.mcai8sh4.nemesis.entity.projectile.PlayerProjectile;
import com.mcai8sh4.nemesis.entity.projectile.Projectile;
import com.mcai8sh4.nemesis.graphics.AnimatedSprite;
import com.mcai8sh4.nemesis.graphics.Screen;
import com.mcai8sh4.nemesis.graphics.Sprite;
import com.mcai8sh4.nemesis.graphics.SpriteSheet;
import com.mcai8sh4.nemesis.input.Keyboard;
import com.mcai8sh4.nemesis.input.Mouse;
import com.mcai8sh4.nemesis.level.tile.Tile;

import java.util.List;


public class Player extends Mob {

    private Keyboard input;
    private Sprite sprite;
    private int anim = 0;
    private int flip = 0;
    private boolean walking = false;
    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 2);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 2);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 2);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 2);

    private AnimatedSprite animSprite = down;
    Projectile p;
    private int fireRate = 0;

    public Player(Keyboard input) {
        this.input = input;
        sprite = Sprite.player_back;
    }

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
        sprite = Sprite.player_back;
        fireRate = PlayerProjectile.FIRE_RATE;
    }

    public void update() {

        if (walking) animSprite.update();
        else animSprite.setFrame(0);
        if (fireRate > 0) fireRate--;
        double xa = 0, ya = 0;
        double speed = 1.5; // Player movement speed
        int running; // value to increase speed - ie. running incrementation
        if (input.run) running = 1;
        else running = 0;


        if (input.up) {
            animSprite = up;
            ya -= speed + running;
            flip =0;
        } else if (input.down) {
            animSprite = down;
            ya += speed + running;
            flip=0;
        }
        if (input.left) {
            animSprite = left;
            xa -= speed + running;
            flip =1;
        } else if (input.right) {
            animSprite = right;
            xa += speed + running;
            flip =0;
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
        clear();
        updateShooting();
    }

    private void clear() {
        for (int i = 0; i < level.getProjectiles().size(); i++) {
            Projectile p = level.getProjectiles().get(i);
            if (p.isRemoved()) level.getProjectiles().remove(i);
        }
    }

    private void updateShooting() {
        if (Mouse.getButton() == 1 && fireRate <= 0) {
            double dx = Mouse.getX() - (Game.getWindowWidth() / 2);
            double dy = Mouse.getY() - (Game.getWindowHeight() / 2);
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);
            fireRate = PlayerProjectile.FIRE_RATE;
        }

    }

    public void render(Screen screen) {

        sprite = animSprite.getSprite();
        screen.renderMob((int)(x - 16),(int) (y - 16), sprite, flip);
        if (dir == Direction.UP) sprite = Sprite.player_forward;

    }


}
