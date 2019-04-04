package com.wdeath.game.evol02.mind.mindcs;

import com.wdeath.game.evol02.mind.IMind;
import com.wdeath.game.evol02.mind.MindCommand;
import com.wdeath.game.evol02.mind.MindEngine;
import com.wdeath.game.evol02.world.entity.Bot;

@IMind(id = 6)
public class CheckFood implements MindCommand {

    @Override
    public void process(MindEngine mind) {
        int p = Math.round(MindEngine.MAX_LENGTH_MIND * (mind.bot.food / (float)Bot.MAX_FOOD));
        mind.step(p);
    }
}
