package com.mcai8sh4.nemesis.entity.mob;

import com.mcai8sh4.nemesis.graphics.AnimatedSprite;
import com.mcai8sh4.nemesis.graphics.Screen;
import com.mcai8sh4.nemesis.graphics.SpriteSheet;

import java.util.List;

public class Chaser extends Mob {


    private boolean walking = false;
    private int flip = 0;
    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 2);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 2);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 2);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 2);
    private AnimatedSprite animSprite = down;

    private double xa = 0;
    private double ya = 0;
    private double speed = 0.6;

    public Chaser(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        //  this.sprite = animSprite;
    }


    private void move() {
        xa = 0;
        ya = 0;

        List<Player> players = level.getPlayers(this, 80);
        if (players.size() > 0) {
            Player player = players.get(0);
            if ((int)x < (int)player.getX()) xa += speed;
            if ((int)x > (int)player.getX()) xa -= speed;
            if ((int)y < (int)player.getY()) ya += speed;
            if ((int)y > (int)player.getY()) ya -= speed;
        }
        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
    }

    public void update() {
        move();
        if (walking) animSprite.update();
        else animSprite.setFrame(0);
        if (ya < 0) {
            animSprite = up;
            dir = Direction.UP;
            flip = 0;
        } else if (ya > 0) {
            animSprite = down;
            dir = Direction.DOWN;
            flip = 0;
        }
        if (xa < 0) {
            animSprite = left;
            dir = Direction.LEFT;
            flip = 1;
        } else if (xa > 0) {
            animSprite = right;
            dir = Direction.RIGHT;
            flip = 0;
        }


        //  sprite = animSprite.getSprite();
    }


    public void render(Screen screen) {
        sprite = animSprite.getSprite();
        screen.renderMob((int) (x - 16), (int) (y - 16), this);
    }
}
