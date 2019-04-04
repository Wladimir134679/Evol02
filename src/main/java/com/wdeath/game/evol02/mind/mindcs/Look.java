package com.wdeath.game.evol02.mind.mindcs;

import com.wdeath.game.evol02.mind.IMind;
import com.wdeath.game.evol02.mind.MindCommand;
import com.wdeath.game.evol02.mind.MindEngine;
import com.wdeath.game.evol02.world.World;

@IMind(id = 4)
public class Look implements MindCommand {

    @Override
    public void process(MindEngine mind) {
        int dir = mind.arguments[mind.getUTK()] % 8;
        World world = mind.bot.world;
        int[] dirs = mind.bot.getDir(dir);
        int x = world.getXW(mind.bot.x + dirs[0]);
        int y = world.getYW(mind.bot.y + dirs[1]);

        if(mind.bot.world.isFree(x, y)){
            mind.step(mind.arguments[mind.getUTKW(mind.getUTK() + 1)]);
            return;
        }
        if(mind.bot.world.getBot(x, y) != null){
            mind.step(mind.arguments[mind.getUTKW(mind.getUTK() + 2)]);
            return;
        }
    }
}
