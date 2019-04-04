package com.wdeath.game.evol02.mind.mindcs;

import com.wdeath.game.evol02.mind.MindCommand;
import com.wdeath.game.evol02.mind.MindEngine;
import com.wdeath.game.evol02.mind.IMind;

@IMind(id = 1)
public class Move implements MindCommand {

    @Override
    public void process(MindEngine mind) {
        int dir = mind.arguments[mind.getUTK()] % 8;
        int[] dirs = mind.bot.getDir(dir);
        mind.bot.move(dirs[0], dirs[1]);
        mind.stop();
    }
}
