package com.mcai8sh4.nemesis.entity.mob;

import com.mcai8sh4.nemesis.graphics.AnimatedSprite;
import com.mcai8sh4.nemesis.graphics.Screen;
import com.mcai8sh4.nemesis.graphics.SpriteSheet;
import com.mcai8sh4.nemesis.level.Node;
import com.mcai8sh4.nemesis.util.Vector2i;

import java.util.List;

public class Star extends Mob {


    private boolean walking = false;
    private int flip = 0;
    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 2);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 2);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 2);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 2);
    private AnimatedSprite animSprite = down;

    private double xa = 0;
    private double ya = 0;
    private double speed = 1;

    private List<Node> path = null;
    private int time = 0;

    public Star(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        //  this.sprite = animSprite;
    }


    private void move() {
        xa = 0;
        ya = 0;
        int px = (int) level.getPlayerAt(0).getX(); // pixel precision
        int py = (int) level.getPlayerAt(0).getY();
        Vector2i start = new Vector2i((int) getX() >> 4, (int) getY() >> 4);
        Vector2i destination = new Vector2i(px >> 4, py >> 4);
        if (time % 3 == 0) path = level.findPath(start, destination);
        if (path != null) {
            if (path.size() > 0) {
                Vector2i vec = path.get(path.size() - 1).tile;
                if (x < vec.getX() << 4) xa += speed;
                if (x > vec.getX() << 4) xa -= speed;
                if (y < vec.getY() << 4) ya += speed;
                if (y > vec.getY() << 4) ya -= speed;
            }
        }


        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else

        {
            walking = false;
        }

    }

    public void update() {
        time++;
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
