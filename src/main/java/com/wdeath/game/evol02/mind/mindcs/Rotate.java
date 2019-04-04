package com.wdeath.game.evol02.mind.mindcs;

import com.wdeath.game.evol02.mind.IMind;
import com.wdeath.game.evol02.mind.MindCommand;
import com.wdeath.game.evol02.mind.MindEngine;

@IMind(id = 5)
public class Rotate implements MindCommand {

    @Override
    public void process(MindEngine mind) {
        int dir = mind.arguments[mind.getUTK()] % 8;
        mind.bot.dir += dir;
        if(mind.bot.dir >= 8)
            mind.bot.dir = mind.bot.dir - 8;
    }
}
