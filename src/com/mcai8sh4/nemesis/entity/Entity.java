package com.mcai8sh4.nemesis.entity;

import com.mcai8sh4.nemesis.graphics.Screen;
import com.mcai8sh4.nemesis.level.Level;

import java.util.Random;

public abstract class Entity {
    public int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random ramdom = new Random();

    public void update() {
    }

    public void render(Screen screen) {
    }

    public void remove() {
        // Remove from level
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void init(Level level){
        this.level = level;
    }
}
