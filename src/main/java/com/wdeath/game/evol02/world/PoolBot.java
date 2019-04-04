package com.wdeath.game.evol02.world;

import com.badlogic.gdx.utils.Pool;
import com.wdeath.game.evol02.world.entity.Bot;

public class PoolBot extends Pool<Bot> {

    public static PoolBot pool;

    public static void init(){
        pool = new PoolBot();
    }

    public PoolBot() {
        super(1024);
    }

    @Override
    protected Bot newObject() {
        return new Bot();
    }
}
