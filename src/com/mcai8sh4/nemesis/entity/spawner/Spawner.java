package com.mcai8sh4.nemesis.entity.spawner;

import com.mcai8sh4.nemesis.entity.Entity;
import com.mcai8sh4.nemesis.level.Level;

public class Spawner extends Entity {

    //   private List<Entity> entities = new ArrayList<Entity>();

    public enum Type {
        MOB, PARTICLE;
    }

    private Type type;

    public Spawner(int x, int y, Type type, int amount, Level level) {
        init(level);
        this.x = x;
        this.y = y;
        this.type = type;
    }

}
