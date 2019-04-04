package com.wdeath.game.evol02.mind.mindcs;

import com.wdeath.game.evol02.mind.IMind;
import com.wdeath.game.evol02.mind.MindCommand;
import com.wdeath.game.evol02.mind.MindEngine;
import com.wdeath.game.evol02.world.World;
import com.wdeath.game.evol02.world.entity.Bot;

@IMind(id = 8)
public class EatBot implements MindCommand {

    @Override
    public void process(MindEngine mind) {
        int dir = mind.arguments[mind.getUTK()] % 8;
        int[] dirs = mind.bot.getDir(dir);

        World world = mind.bot.world;
        int x = world.getXW(mind.bot.x + dirs[0]);
        int y = world.getYW(mind.bot.y + dirs[1]);

        Bot bot = world.getBot(x, y);
        if(bot == null)
            return;
        float p = mind.arguments[mind.getUTKW(mind.getUTK() + 1)] / (float)MindEngine.MAX_LENGTH_MIND;
        mind.bot.food += Math.round(10f * p);
        mind.bot.toRed();
        world.removeBot(bot);
    }
}
