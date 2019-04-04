package com.wdeath.game.evol02.mind.mindcs;

import com.wdeath.game.evol02.mind.IMind;
import com.wdeath.game.evol02.mind.MindCommand;
import com.wdeath.game.evol02.mind.MindEngine;

@IMind(id = 3)
public class EatFood implements MindCommand {

    @Override
    public void process(MindEngine mind) {
        int a = mind.arguments[mind.getUTK()];
        int p = Math.round(10f * (a / (float)MindEngine.MAX_LENGTH_MIND));
        if(p > mind.bot.food)
            p = mind.bot.food;
        mind.bot.food -= p;
        mind.bot.health += 10 * p;
    }
}
