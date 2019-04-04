package com.wdeath.game.evol02.mind.mindcs;

import com.wdeath.game.evol02.mind.IMind;
import com.wdeath.game.evol02.mind.MindCommand;
import com.wdeath.game.evol02.mind.MindEngine;
import com.wdeath.game.evol02.world.PoolBot;
import com.wdeath.game.evol02.world.World;
import com.wdeath.game.evol02.world.entity.Bot;

@IMind(id = 9)
public class Spread implements MindCommand {

    @Override
    public void process(MindEngine mind) {
        if(mind.bot.food < 10)
            return;
        mind.bot.food -= 10;

        int dir = mind.arguments[mind.getUTK()] % 8;
        int[] dirs = mind.bot.getDir(dir);
        World world = mind.bot.world;
        int x = world.getXW(mind.bot.x + dirs[0]);
        int y = world.getXW(mind.bot.y + dirs[1]);
        if(!world.isFree(x, y))
            return;

        Bot bot = PoolBot.pool.obtain();
        mind.cloneMind(bot.mind, 0.25f);
        bot.x = x;
        bot.y = y;
        bot.health = 10;
        bot.food = 10;
        world.addBot(bot);
    }
}
