package com.mcai8sh4.nemesis.entity.mob;

import com.mcai8sh4.nemesis.graphics.Screen;
import com.mcai8sh4.nemesis.graphics.Sprite;
import com.mcai8sh4.nemesis.input.Keyboard;
import com.mcai8sh4.nemesis.level.Level;
import com.mcai8sh4.nemesis.level.tile.Tile;


public class Player extends Mob {

    private Keyboard input;
    private Sprite sprite;
    private int anim = 0;
    private boolean walking = false;
    private int speed = 2;

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
        if (anim < 7500) anim++;
        else anim = 0;
        if (input.up) ya -= speed;
        if (input.down) ya += speed;
        if (input.left) xa -= speed;
        if (input.right) xa += speed;

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
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
