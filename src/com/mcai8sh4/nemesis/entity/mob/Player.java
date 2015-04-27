package com.mcai8sh4.nemesis.entity.mob;

import com.mcai8sh4.nemesis.Game;
import com.mcai8sh4.nemesis.graphics.Screen;
import com.mcai8sh4.nemesis.graphics.Sprite;
import com.mcai8sh4.nemesis.input.Keyboard;
import com.mcai8sh4.nemesis.input.Mouse;
import com.mcai8sh4.nemesis.level.Level;
import com.mcai8sh4.nemesis.level.tile.Tile;


public class Player extends Mob {

    private Keyboard input;
    private Sprite sprite;
    private int anim = 0;
    private boolean walking = false;

    public Player(Keyboard input) {
        this.input = input;
        sprite = Sprite.player_back;
    }

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
        sprite = Sprite.player_back;
    }

    public void update() {
        int xa = 0, ya = 0;
        int speed = 2; // Player movement speed
        int running; // value to increase speed - ie. running incrementation
        if (input.run) running = 1;
        else running = 0;

        if (anim < 7500) anim += 1 + running;
        else anim = 0;

        if (input.up) ya -= speed + running;
        if (input.down) ya += speed + running;
        if (input.left) xa -= speed + running;
        if (input.right) xa += speed + running;
        
        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
        
        updateShooting();
    }

    private void updateShooting() {
        if (Mouse.getButton() == 1){
            double dx = Mouse.getX() -  (Game.getWindowWidth() / 2);
            double dy = Mouse.getY() - (Game.getWindowHeight() / 2);
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);
        }

    }

    public void render(Screen screen) {
        int flip = 0;
//        sprite = Sprite.player_forward;
        if (dir == 0) {
            if (walking) {
                if (anim % 20 > 10) {
                    sprite = Sprite.player_forward_1;
                } else {
                    flip = 1;
                    sprite = Sprite.player_forward_1;
                }

            }
        }
        if (dir == 2) {
            sprite = Sprite.player_back;
            if (walking) {
                if (anim % 20 > 10) {
                    sprite = Sprite.player_back_1;
                } else {
                    flip = 1;
                    sprite = Sprite.player_back_1;
                }
            }
        }
        if (dir == 1) {
            sprite = Sprite.player_side;
            if (walking) {
                if (anim % 20 > 10) {
                    sprite = Sprite.player_side_1;
                } else {
                    sprite = Sprite.player_side;
                }
            }
        }
        if (dir == 3) {
            sprite = Sprite.player_side;
            flip = 1;
            if (walking) {
                if (anim % 20 > 10) {
                    sprite = Sprite.player_side_1;
                } else {
                    sprite = Sprite.player_side;
                }
            }
        }
        screen.renderPlayer(x - 16, y - 16, sprite, flip);

        if (dir == 0) sprite = Sprite.player_forward;
    }


}
