package com.wdeath.game.evol02.mind.mindcs;

import com.wdeath.game.evol02.mind.IMind;
import com.wdeath.game.evol02.mind.MindCommand;
import com.wdeath.game.evol02.mind.MindCommands;
import com.wdeath.game.evol02.mind.MindEngine;

@IMind(id = 2)
public class Photosynthesis implements MindCommand {

    @Override
    public void process(MindEngine mind) {
        int p = mind.arguments[mind.getUTK()];
        int a = (int)(30f * (p / (float)MindEngine.MAX_LENGTH_MIND));
        mind.bot.food += a;
        mind.bot.toGreen();
    }
}
